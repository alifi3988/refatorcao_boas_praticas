package br.com.alura.service;

import br.com.alura.client.ClientAbrigos;
import br.com.alura.model.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    private static ClientAbrigos client;
    private static int RESPONSE_NUMBER_SUCCESS_200 = 200;
    private static int RESPONSE_NUMBER_ERROR_400 = 400;
    private static int RESPONSE_NUMBER_ERROR_500 = 500;

    public AbrigoService(ClientAbrigos client) {
        this.client = client;
    }

    public static void cadastarNovoAbrigo() throws IOException, InterruptedException {

        Abrigo abrigo = new Abrigo();

        System.out.println("Digite o nome do abrigo:");
        abrigo.setNome(new Scanner(System.in).nextLine());
        System.out.println("Digite o telefone do abrigo:");
        abrigo.setTelefone(new Scanner(System.in).nextLine());
        System.out.println("Digite o email do abrigo:");
        abrigo.setEmail(new Scanner(System.in).nextLine());

        HttpResponse<String> response = client.registerAbrigo(abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == RESPONSE_NUMBER_SUCCESS_200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == RESPONSE_NUMBER_ERROR_400 || statusCode == RESPONSE_NUMBER_ERROR_500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

    public static void listarAbrigosCadastrados() throws IOException, InterruptedException {
        try {
            HttpResponse<String> response = client.getAbrigos();

            List<Abrigo> listAbrigo = Arrays.stream(
                    new ObjectMapper().readValue(response.body(), Abrigo[].class)).toList();

            if (listAbrigo.isEmpty()) {
                System.out.println("Não foi encontrado nenhum Abrigo cadastrado.");
            }

            mostrarAbrigosCadastrados(listAbrigo);

        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void mostrarAbrigosCadastrados(List<Abrigo> listAbrigos) {
        System.out.println("Abrigos cadastrados:");
        listAbrigos.forEach(abrigo -> {
            System.out.println(abrigo.getId() + " - " + abrigo.getNome());
        });
    }
}