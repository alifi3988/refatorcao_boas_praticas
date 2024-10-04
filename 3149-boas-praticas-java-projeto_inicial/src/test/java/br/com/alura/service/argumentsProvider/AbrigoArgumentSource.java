package br.com.alura.service.argumentsProvider;

import br.com.alura.model.Abrigo;

public class AbrigoArgumentSource {

    public static Abrigo getPayloadAbrigo(){
        Abrigo abrigo = new Abrigo();
        abrigo.setId(0L);
        abrigo.setNome("Abrigo Teste");
        abrigo.setTelefone("19989786542");
        abrigo.setEmail("abrigo@teste.com.br");
        return abrigo;
    }
}
