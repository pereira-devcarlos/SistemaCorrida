package br.com.corrida.app;

import br.com.corrida.model.*;

import java.util.Scanner;

import br.com.corrida.enums.*;
import br.com.corrida.repository.*;
import br.com.corrida.service.*;
import br.com.corrida.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        // Instanciar serviços
        UsuarioService usuarioService = new UsuarioService();
        MotoqueiroService motoqueiroService = new MotoqueiroService();
        CorridaService corridaService = new CorridaService();
        
        // Inicialização do Scanner para entrada do usuário
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        while (opcao != 4) {
            MenuUtil.exibirMenuInicial();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    MenuUtil.exibirMenuLoginUsuario();
                    opcao = scanner.nextInt();
                    if (opcao == 1) {
                        System.out.println("Login de usuário selecionado.");
                        // Lógica de login aqui
                        System.out.print("Digite seu nome de usuario: ");
                        String nomeUsuario = scanner.next();
                        System.out.print("Digite seu telefone: ");
                        String telefoneUsuario;
                        do {
                            telefoneUsuario = scanner.next();
                            if (telefoneUsuario.length() != 8) {
                                System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                            }
                        } while (telefoneUsuario.length() != 8);

                        Usuario usuario = usuarioService.buscarPorTelefone(telefoneUsuario);
                        if (usuario != null && usuario.getNome().equals(nomeUsuario)) {
                            System.out.println("Login bem-sucedido. Bem-vindo, " + usuario.getNome() + "!");
                            // Menu do usuário após login
                            MenuUtil.exibirMenuUsuario();
                            int opcaoUsuario = scanner.nextInt();
                            if (opcaoUsuario == 1) {
                                System.out.println("Solicitação de corrida selecionada.");
                                System.out.print("Digite a distância da corrida em km: ");
                                double distancia = scanner.nextDouble();
                                Corrida novaCorrida = new Corrida();
                                novaCorrida.setUsuario(usuario);
                                corridaService.solicitarCorrida(novaCorrida);
                            } else if (opcaoUsuario == 2) {
                                System.out.println("Voltando ao Menu Inicial.");
                            } else {
                                System.out.println("Opção inválida. Voltando ao Menu Inicial.");
                            }
                        } else {
                            System.out.println("Erro: Nome de usuário ou telefone inválido.");
                        }
                    } else
                    if (opcao == 2) {
                        System.out.println("Cadastro de usuário selecionado.");
                        System.out.print("Digite o nome do usuário: ");
                        String nomeUsuario = scanner.next();
                        System.out.print("Digite o telefone do usuário: ");
                        String telefoneUsuario;
                        do {
                            telefoneUsuario = scanner.next();
                            if (telefoneUsuario.length() != 8) {
                                System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                            }
                        } while (telefoneUsuario.length() != 8);
                        Usuario novoUsuario = new Usuario(nomeUsuario, telefoneUsuario);
                        usuarioService.cadastrarUsuario(novoUsuario);
                    } else if (opcao == 3) {
                        System.out.println("Voltando ao Menu Inicial.");
                    }
                    break;
                case 2:
                    MenuUtil.exibirMenuLoginMotoqueiro();
                    break;
                case 3:
                    MenuUtil.exibirMenuDesenvolvedor();
                    break;
                case 4:
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
