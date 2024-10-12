package br.com.alura.service;

import br.com.alura.client.ClientAbrigos;
import br.com.alura.model.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;
import java.util.Arrays;

import static br.com.alura.service.argumentsProvider.AbrigoArgumentTest.getPayloadAbrigo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbrigoServiceTest {

    private final ClientAbrigos client = mock(ClientAbrigos.class);
    private final HttpResponse response = mock(HttpResponse.class);
    AbrigoService service = new AbrigoService(client);
    private final Abrigo abrigo = getPayloadAbrigo();

    @Test
    public void should_get_abrigos_registry() throws IOException, InterruptedException {
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdNome = "0 - Abrigo Teste";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[" + new ObjectMapper().writeValueAsString(abrigo) + "]");
        when(client.getAbrigos()).thenReturn(response);

        service.listarAbrigosCadastrados();

        String[] lines = baos.toString().split(System.lineSeparator());
        System.out.println(Arrays.stream(lines).toList());
        String actualAbrigosCadastrados = lines[0];
        String aactualIdNome = lines[1];

        Assert.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assert.assertEquals(expectedIdNome, aactualIdNome);
    }

    @Test
    public void should_not_get_abrigos_registry_when_is_empty() throws IOException, InterruptedException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.getAbrigos()).thenReturn(response);

        service.listarAbrigosCadastrados();

        String[] lines = baos.toString().split(System.lineSeparator());
        System.out.println(Arrays.stream(lines).toList());
        String actualAbrigosCadastrados = lines[0];

        Assert.assertEquals("Não foi encontrado nenhum Abrigo cadastrado.", actualAbrigosCadastrados);
    }
}