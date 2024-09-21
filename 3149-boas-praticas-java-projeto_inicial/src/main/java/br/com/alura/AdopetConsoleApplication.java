package br.com.alura;

import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetsService;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        try {
            int opcaoEscolhida = 0;
            while (true) {

                opcaoEscolhida = mensagemMenuInicial();

                switch (opcaoEscolhida) {
                    case 1:
                        AbrigoService.listarAbrigosCadastrados();
                        break;
                    case 2:
                        AbrigoService.cadastarNovoAbrigo();
                        break;
                    case 3:
                        PetsService.listarPetsDoAbrigo();
                        break;
                    case 4:
                        PetsService.importarPetsDoAbrigo();
                        break;
                    case 5:
                        System.out.println("Finalizando o programa...");
                        return;
                    default:
                        System.out.println("NÚMERO INVÁLIDO!");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static int mensagemMenuInicial() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
        String textoDigitado = new Scanner(System.in).nextLine();

        return Integer.parseInt(textoDigitado);
    }
}
