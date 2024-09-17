package br.com.alura;

import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetsService;
import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {

        PetsService pets = new PetsService();
        AbrigoService abrigo = new AbrigoService();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    abrigo.listarAbrigosCadastrados();
                } else if (opcaoEscolhida == 2) {
                    abrigo.cadastarNovoAbrigo();
                } else if (opcaoEscolhida == 3) {
                    if (pets.listarPetsDoAbrigo()) continue;
                } else if (opcaoEscolhida == 4) {
                    if (pets.importarPetsDoAbrigo()) continue;
                } else if (opcaoEscolhida == 5) {
                    break;
                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
