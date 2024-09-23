package br.com.alura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abrigo {

    private long id;

    private String nome;

    private String telefone;

    private String email;

    @JsonIgnoreProperties
    private List<Pet> pets;

}
