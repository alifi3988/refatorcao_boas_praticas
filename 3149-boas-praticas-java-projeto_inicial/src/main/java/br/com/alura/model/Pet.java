package br.com.alura.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private String tipo;
    private String nome;
    private String raca;
    private int idade;
    private String cor;
    private float peso;
}
