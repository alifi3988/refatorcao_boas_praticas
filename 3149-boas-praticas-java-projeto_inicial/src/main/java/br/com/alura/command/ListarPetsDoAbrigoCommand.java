package br.com.alura.command;

import br.com.alura.client.ClientPet;
import br.com.alura.service.PetsService;

import java.io.IOException;

public class ListarPetsDoAbrigoCommand implements Command{

    @Override
    public void execute() {
        ClientPet clientPet = new ClientPet();
        PetsService pet = new PetsService(clientPet);

        try {
            pet.listarPetsDoAbrigo();
        }catch (IOException | InterruptedException e) {
            System.out.println("Error [ListarPetsDoAbrigoCommand]: " + e.getMessage());
        }
    }
}
