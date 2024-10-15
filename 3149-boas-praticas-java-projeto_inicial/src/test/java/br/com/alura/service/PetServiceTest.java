package br.com.alura.service;

import br.com.alura.client.ClientPet;
import br.com.alura.model.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static br.com.alura.service.argumentsProvider.PetArgumentTest.getPayloadPet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PetServiceTest {

    private final ClientPet client = mock(ClientPet.class);
    private final HttpResponse response = mock(HttpResponse.class);
    PetsService service = new PetsService(client);
    private final Pet pet = getPayloadPet();

    @Test
    public void should_get_pets_registered() throws IOException, InterruptedException {
        String expectedPetsCadastrados = "Pets cadastrados:";
        String expectedInformacoesPets = "0 - tipo teste - Pet Teste - raca teste - 0 ano(s)";
        String inputSimulado = "0";

        //simulando a inserção de uma informação através do System.out.print()
        ByteArrayInputStream baos = new ByteArrayInputStream(inputSimulado.getBytes());
        System.setIn(baos);

        //Pegando as informações que foram printadas na tela
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        when(response.body()).thenReturn("[" + new ObjectMapper().writeValueAsString(pet) + "]");
        when(client.listarPets("0")).thenReturn(response);

        service.listarPetsDoAbrigo();

        String[] lines = byteArrayOutputStream.toString().split(System.lineSeparator());
        String actualPetsCadastrados = lines[1];
        String atualInformacoesPets = lines[2];

        Assert.assertEquals(expectedPetsCadastrados, actualPetsCadastrados);
        Assert.assertEquals(expectedInformacoesPets, atualInformacoesPets);
    }
}
