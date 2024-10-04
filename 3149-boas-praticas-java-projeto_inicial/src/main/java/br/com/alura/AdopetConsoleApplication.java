package br.com.alura;

import br.com.alura.client.ClientAbrigos;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetsService;

import java.util.Scanner;

public class AdopetConsoleApplication {

    static ClientAbrigos clientAbrigos = new ClientAbrigos();
    static AbrigoService abrigo = new AbrigoService(clientAbrigos);

    public static void main(String[] args) {

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        try {

            while (true) {

                int opcaoEscolhida = mensagemMenuInicial();

                switch (opcaoEscolhida) {
                    case 1:
                        abrigo.listarAbrigosCadastrados();
                        break;
                    case 2:
                        abrigo.cadastarNovoAbrigo();
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
        int opcaoEscolhida;
        while (true) {
            try {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");
                System.out.print("R: ");
                opcaoEscolhida = Integer.parseInt(new Scanner(System.in).nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Erro [MENU]: " + e.getMessage() + ". Por favor, informe um número válido entre 1 e 5. \nTente novamente!");
            }
        }

        return opcaoEscolhida;
    }
}
