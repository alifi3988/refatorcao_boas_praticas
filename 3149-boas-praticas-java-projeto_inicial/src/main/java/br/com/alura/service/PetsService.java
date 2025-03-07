package br.com.alura.service;

import br.com.alura.client.ClientPet;
import br.com.alura.model.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetsService {

    private static ClientPet client;

    public PetsService(ClientPet client) {
        this.client = client;
    }

    public static void importarPetsDoAbrigo() throws IOException, InterruptedException {

        Pet pet = new Pet();

        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + nomeArquivo);
            return;
        }

        String line;

        while ((line = reader.readLine()) != null) {

            String[] campos = line.split(",");
            pet.setTipo(campos[0].toUpperCase());
            pet.setNome(campos[1]);
            pet.setRaca(campos[2]);
            pet.setIdade(Integer.parseInt(campos[3]));
            pet.setCor(campos[4]);
            pet.setPeso(Float.parseFloat(campos[5]));

            HttpResponse<String> response = client.cadastrarPet(idOuNome, pet);

            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == 200) {
                System.out.println("Pet cadastrado com sucesso: " + pet.getNome());
            } else if (statusCode == 404) {
                System.out.println("Id ou nome do abrigo não encontado!");
                break;
            } else if (statusCode == 400 || statusCode == 500) {
                System.out.println("Erro ao cadastrar o pet: " + pet.getNome());
                System.out.println(responseBody);
                break;
            }
        }
        reader.close();

    }

    public static void listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        HttpResponse<String> response = client.listarPets(idOuNome);

        int statusCode = response.statusCode();
        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!");
            return;
        }

        List<Pet> petsList = Arrays.stream(new ObjectMapper().readValue(response.body(), Pet[].class)).toList();

        System.out.println("Pets cadastrados:");
        for (Pet pet : petsList) {
            System.out.println(pet.getId() + " - " +
                    pet.getTipo() + " - " +
                    pet.getNome() + " - " +
                    pet.getRaca() + " - " +
                    pet.getId() + " ano(s)");
        }
    }

}
