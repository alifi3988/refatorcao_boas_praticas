package br.com.alura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttpRequest {

    public HttpResponse<String> getHttpResponse(String diretorioURL, String typeRequest, HttpRequest.BodyPublisher bodyRequest, String header)
            throws IOException, InterruptedException {

        String host = "http://localhost:8080/abrigos";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder request = HttpRequest.newBuilder()
                .uri(URI.create(host.concat(diretorioURL)))
                .method(typeRequest.toUpperCase(), bodyRequest);

        if(!header.isEmpty()) request.header("Content-Type", header);

        return client.send(request.build(), HttpResponse.BodyHandlers.ofString());
    }
}
