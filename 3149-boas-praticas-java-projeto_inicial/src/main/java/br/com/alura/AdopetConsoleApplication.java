package br.com.alura;

import br.com.alura.command.CadastrarAbrigosCommand;
import br.com.alura.command.CommandExecute;
import br.com.alura.command.ImportarPetsDoAbrigoCommand;
import br.com.alura.command.ListarAbrigosCommand;
import br.com.alura.command.ListarPetsDoAbrigoCommand;
import br.com.alura.service.PetsService;

import java.util.Scanner;

public class AdopetConsoleApplication {

    private final static CommandExecute execute = new CommandExecute();

    public static void main(String[] args) {

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        try {

            while (true) {

                int opcaoEscolhida = mensagemMenuInicial();

                switch (opcaoEscolhida) {
                    case 1:
                        execute.executeCommand(new ListarAbrigosCommand());
                        break;
                    case 2:
                        execute.executeCommand(new CadastrarAbrigosCommand());
                        break;
                    case 3:
                        execute.executeCommand(new ListarPetsDoAbrigoCommand());
                        break;
                    case 4:
                        execute.executeCommand(new ImportarPetsDoAbrigoCommand());
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
