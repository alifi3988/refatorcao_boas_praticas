package br.com.alura.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abrigo {
    private String nome;
    private String telefone;
    private String email;
}
