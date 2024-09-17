package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.model.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetsService {

    private static ClientHttpConfiguration client;
    private static final int HTTP_RESPONSE_STATUS_ERRO_404 = 404;
    private static final int HTTP_RESPONSE_STATUS_ERRO_400 = 400;
    private static final int HTTP_RESPONSE_STATUS_ERRO_500 = 500;
    private static final int HTTP_RESPONSE_STATUS_SUCCESS_200 = 200;

    public boolean listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        HttpResponse<String> response = client.getHttpResponse(
                "/".concat(idOuNome.concat("/pets")),
                "GET",
                HttpRequest.BodyPublishers.noBody(),
                "");

        int statusCode = response.statusCode();
        if (statusCode == HTTP_RESPONSE_STATUS_ERRO_404 || statusCode == HTTP_RESPONSE_STATUS_ERRO_500) {
            System.out.println("ID ou nome não cadastrado!");
            return true;
        }
        String responseBody = response.body();

        List<Pet> resultListPets = Arrays.stream(new ObjectMapper().readValue(responseBody, Pet[].class)).toList();

        System.out.println("Pets cadastrados:");
        for (Pet pet : resultListPets) {
            long id = pet.getId();
            String tipo = pet.getTipo();
            String nome = pet.getNome();
            String raca = pet.getRaca();
            int idade = pet.getIdade();
            System.out.println(id + " - " + tipo + " - " + nome + " - " + raca + " - " + idade + " ano(s)");
        }
        return false;
    }

    public boolean importarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " +nomeArquivo);
            return true;
        }
        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(",");
            String tipo = campos[0];
            String nome = campos[1];
            String raca = campos[2];
            int idade = Integer.parseInt(campos[3]);
            String cor = campos[4];
            Float peso = Float.parseFloat(campos[5]);

            Pet pet = new Pet(tipo.toUpperCase(), nome, raca, idade, cor, peso);

            HttpResponse<String> response = client.getHttpResponse(
                    "/".concat(idOuNome.concat("/pets")),
                    "POST",
                    HttpRequest.BodyPublishers.ofString(new Gson().toJson(pet)),
                    "application/json");

            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == HTTP_RESPONSE_STATUS_SUCCESS_200) {
                System.out.println("Pet cadastrado com sucesso: " + nome);
            } else if (statusCode == HTTP_RESPONSE_STATUS_ERRO_404) {
                System.out.println("Id ou nome do abrigo não encontado!");
                break;
            } else if (statusCode == HTTP_RESPONSE_STATUS_ERRO_400 || statusCode == HTTP_RESPONSE_STATUS_ERRO_500) {
                System.out.println("Erro ao cadastrar o pet: " + nome);
                System.out.println(responseBody);
                break;
            }
        }
        reader.close();
        return false;
    }
}
