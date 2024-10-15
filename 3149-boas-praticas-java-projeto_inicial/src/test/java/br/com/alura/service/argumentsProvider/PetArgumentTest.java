package br.com.alura.service.argumentsProvider;

import br.com.alura.model.Pet;

public class PetArgumentTest {

    public static Pet getPayloadPet(){
        Pet pet = new Pet();
        pet.setId(0L);
        pet.setNome("Pet Teste");
        pet.setIdade(1);
        pet.setTipo("tipo teste");
        pet.setPeso(10F);
        pet.setCor("Preto");
        pet.setRaca("raca teste");
        return pet;
    }
}
