package br.com.alura.client;

import br.com.alura.model.Abrigo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientAbrigos {

    public HttpResponse<String> getAbrigos() throws IOException, InterruptedException {
       return HttpRequestClient.getHttRequest(
                "",
                "GET",
                HttpRequest.BodyPublishers.noBody(),
                "");
    }

    public HttpResponse<String> registerAbrigo(Abrigo abrigo) throws IOException, InterruptedException {
        return HttpRequestClient.getHttRequest(
                "",
                "POST",
                HttpRequest.BodyPublishers.ofString(new Gson().toJson(abrigo)),
                "application/json");
    }
}
