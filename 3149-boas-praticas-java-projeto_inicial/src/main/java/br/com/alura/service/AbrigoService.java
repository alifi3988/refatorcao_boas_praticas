package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.model.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    private static ClientHttpConfiguration client;
    private static final int HTTP_RESPONSE_STATUS_ERRO_400 = 400;
    private static final int HTTP_RESPONSE_STATUS_ERRO_500 = 500;
    private static final int HTTP_RESPONSE_STATUS_SUCCESS_200 = 200;

    public void cadastarNovoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Abrigo abrigo = new Abrigo(nome, telefone, email);

        HttpResponse<String> response = client.getHttpResponse(
                "",
                "POST",
                HttpRequest.BodyPublishers.ofString(new Gson().toJson(abrigo)),
                "application/json");

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == HTTP_RESPONSE_STATUS_SUCCESS_200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == HTTP_RESPONSE_STATUS_ERRO_400 || statusCode == HTTP_RESPONSE_STATUS_ERRO_500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

    public void listarAbrigosCadastrados() throws IOException, InterruptedException {
        HttpResponse<String> response = client.getHttpResponse("", "GET", HttpRequest.BodyPublishers.noBody(), "");

        String responseBody = response.body();
        List<Abrigo> resultListAbrigo = Arrays.stream(new ObjectMapper().readValue(responseBody, Abrigo[].class)).toList();

        System.out.println("Abrigos cadastrados:");
        for (Abrigo abrigo : resultListAbrigo) {
            long id = abrigo.getId();
            String nome = abrigo.getNome();
            System.out.println(id + " - " + nome);
        }
    }
}
