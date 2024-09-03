package br.com.alura.service;

import br.com.alura.client.ClientHttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ServicePets {

    private static ClientHttpRequest client;
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
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Pets cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String tipo = jsonObject.get("tipo").getAsString();
            String nome = jsonObject.get("nome").getAsString();
            String raca = jsonObject.get("raca").getAsString();
            int idade = jsonObject.get("idade").getAsInt();
            System.out.println(id +" - " +tipo +" - " +nome +" - " +raca +" - " +idade +" ano(s)");
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

            JsonObject json = new JsonObject();
            json.addProperty("tipo", tipo.toUpperCase());
            json.addProperty("nome", nome);
            json.addProperty("raca", raca);
            json.addProperty("idade", idade);
            json.addProperty("cor", cor);
            json.addProperty("peso", peso);

            HttpResponse<String> response = client.getHttpResponse(
                    "/".concat(idOuNome.concat("/pets")),
                    "POST",
                    HttpRequest.BodyPublishers.ofString(json.toString()),
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
