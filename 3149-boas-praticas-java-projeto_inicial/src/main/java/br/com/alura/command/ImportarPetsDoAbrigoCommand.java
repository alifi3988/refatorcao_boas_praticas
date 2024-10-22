package br.com.alura.command;

import br.com.alura.client.ClientPet;
import br.com.alura.service.PetsService;

import java.io.IOException;

public class ImportarPetsDoAbrigoCommand implements Command{

    @Override
    public void execute() {
        ClientPet clientPet = new ClientPet();
        PetsService pet = new PetsService(clientPet);

        try {
            pet.importarPetsDoAbrigo();
        }catch (IOException | InterruptedException e) {
            System.out.println("Error [ImportarPetsDoAbrigoCommand]: " + e.getMessage());
        }
    }
}
