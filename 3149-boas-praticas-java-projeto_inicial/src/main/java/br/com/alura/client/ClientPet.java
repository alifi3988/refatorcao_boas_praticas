package br.com.alura.client;

import br.com.alura.model.Abrigo;
import br.com.alura.model.Pet;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientPet {

    public HttpResponse<String> cadastrarPet(String idOuNome, Pet pet) throws IOException, InterruptedException {
        return HttpRequestClient.getHttRequest(
                "/".concat(idOuNome.concat("/pets")),
                "POST",
                HttpRequest.BodyPublishers.ofString(new Gson().toJson(pet)),
                "application/json");
    }

    public HttpResponse<String> listarPets(String idOuNome) throws IOException, InterruptedException {
        return HttpRequestClient.getHttRequest(
                "/".concat(idOuNome.concat("/pets")),
                "GET",
                HttpRequest.BodyPublishers.noBody(),
                "");
    }
}
