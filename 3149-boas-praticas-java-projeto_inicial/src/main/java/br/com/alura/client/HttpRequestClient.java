package br.com.alura.client;

import br.com.alura.model.Abrigo;
import br.com.alura.model.Pet;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestClient {

    private static final String HOST = "http://localhost:8080/abrigos";

    public static HttpResponse<String> getHttRequest(String diretorioURL, String typeRequest, HttpRequest.BodyPublisher bodyRequest, String header)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder request = HttpRequest.newBuilder()
                .uri(URI.create(HOST.concat(diretorioURL)))
                .method(typeRequest.toUpperCase(), bodyRequest);

        if(!header.isEmpty()) request.header("Content-Type", header);

        return client.send(request.build(), HttpResponse.BodyHandlers.ofString());
    }
}
