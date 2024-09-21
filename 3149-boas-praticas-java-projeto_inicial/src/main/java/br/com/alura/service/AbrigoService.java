package br.com.alura.service;

import br.com.alura.client.HttpRequestClient;
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

    public static void cadastarNovoAbrigo() throws IOException, InterruptedException {

        Abrigo abrigo = new Abrigo();

        System.out.println("Digite o nome do abrigo:");
        abrigo.setNome(new Scanner(System.in).nextLine());
        System.out.println("Digite o telefone do abrigo:");
        abrigo.setTelefone(new Scanner(System.in).nextLine());
        System.out.println("Digite o email do abrigo:");
        abrigo.setEmail(new Scanner(System.in).nextLine());

        HttpResponse<String> response = HttpRequestClient.getHttRequest(
                "",
                "POST",
                HttpRequest.BodyPublishers.ofString(new Gson().toJson(abrigo)),
                "application/json");

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

    public static void listarAbrigosCadastrados() throws IOException, InterruptedException {

        HttpResponse<String> response = HttpRequestClient.getHttRequest(
                "",
                "GET",
                HttpRequest.BodyPublishers.noBody(),
                "");
        System.out.println("Abrigos cadastrados:");

        List<Abrigo> listAbrigo = Arrays.stream(
                new ObjectMapper().readValue(response.body(), Abrigo[].class)).toList();

        for (Abrigo abrigo : listAbrigo) {
            System.out.println(abrigo.getId() + " - " + abrigo.getNome());
        }
    }
}
