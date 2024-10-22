package br.com.alura.command;

import br.com.alura.client.ClientAbrigos;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class ListarAbrigosCommand implements Command{

    @Override
    public void execute() {
        ClientAbrigos clientAbrigos = new ClientAbrigos();
        AbrigoService abrigo = new AbrigoService(clientAbrigos);

        try {
            abrigo.listarAbrigosCadastrados();
        }catch (IOException | InterruptedException e) {
            System.out.println("Error [ListarAbrigosCommand]: " + e.getMessage());
        }
    }
}
