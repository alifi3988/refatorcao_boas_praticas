package br.com.alura.command;

import br.com.alura.client.ClientAbrigos;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class CadastrarAbrigosCommand implements Command{

    @Override
    public void execute() {
        try {
            ClientAbrigos clientAbrigos = new ClientAbrigos();
            AbrigoService abrigo = new AbrigoService(clientAbrigos);
            abrigo.cadastarNovoAbrigo();
        }catch (IOException | InterruptedException e) {
            System.out.println("Error [CadastrarAbrigosCommand]: " + e.getMessage());
        }
    }
}
