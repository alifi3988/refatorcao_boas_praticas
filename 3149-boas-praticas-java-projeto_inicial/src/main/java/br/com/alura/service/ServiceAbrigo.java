package br.com.alura.service;

import br.com.alura.client.ClientHttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ServiceAbrigo {

    private static ClientHttpRequest client;
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

        JsonObject json = new JsonObject();
        json.addProperty("nome", nome);
        json.addProperty("telefone", telefone);
        json.addProperty("email", email);

        HttpResponse<String> response = client.getHttpResponse(
                "",
                "POST",
                HttpRequest.BodyPublishers.ofString(json.toString()),
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
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Abrigos cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String nome = jsonObject.get("nome").getAsString();
            System.out.println(id +" - " +nome);
        }
    }
}
