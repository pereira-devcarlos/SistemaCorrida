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
            // Exibir menu inicial
            MenuUtil.exibirMenuInicial();

            // Ler opção do usuário
            opcao = scanner.nextInt();
            switch (opcao) {
                // Opção 1: Usuário
                case 1:
                    // Exibir menu de login/cadastro de usuário
                    MenuUtil.exibirMenuLoginUsuario();
                    opcao = scanner.nextInt();
                    
                    // Opções de login ou cadastro
                    if (opcao == 1) {
                        System.out.println("Login de usuário selecionado.");
                        // Lógica de login aqui
                        System.out.print("Digite seu nome de usuario: ");
                        String nomeUsuario = scanner.next();
                        System.out.print("Digite seu telefone: ");

                        // Validação do telefone com 8 dígitos
                        String telefoneUsuario;
                        do {
                            telefoneUsuario = scanner.next();
                            if (telefoneUsuario.length() != 8) {
                                System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                            }
                        } while (telefoneUsuario.length() != 8);

                        // Verificar credenciais
                        Usuario usuario = usuarioService.buscarPorTelefone(telefoneUsuario);
                        String nomeMinusculo = nomeUsuario.toLowerCase();
                        if (usuario != null && usuario.getNome().toLowerCase().equals(nomeMinusculo)) {
                            System.out.println("Login bem-sucedido. Bem-vindo, " + usuario.getNome() + "!");

                            // Menu do usuário após login
                            int opcaoUsuario = 0;
                            do {
                                MenuUtil.exibirMenuUsuario();
                                opcaoUsuario = scanner.nextInt();
                                
                                if (opcaoUsuario == 1) {
                                    // Opção de solicitar corrida
                                    System.out.println("Solicitação de corrida selecionada.");
                                    System.out.print("Digite a distância da corrida em km: ");
                                    double distancia = scanner.nextDouble();
    
                                    // Criar e solicitar uma nova corrida
                                    Corrida novaCorrida = new Corrida(usuario, distancia);
    
                                    MenuUtil.exibirFormasDePagamento();
                                    int formaPagamentoOpcao = scanner.nextInt();
                                    switch (formaPagamentoOpcao) {
                                        case 1:
                                            novaCorrida.getUsuario().setFormaDePagamento(FormaDePagamento.DINHEIRO);
                                            break;
                                        case 2:
                                            novaCorrida.getUsuario().setFormaDePagamento(FormaDePagamento.CARTAO_CREDITO);
                                            break;
                                        case 3:
                                            novaCorrida.getUsuario().setFormaDePagamento(FormaDePagamento.CARTAO_DEBITO);
                                            break;
                                        case 4:
                                            novaCorrida.getUsuario().setFormaDePagamento(FormaDePagamento.PIX);
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Definindo forma de pagamento como Dinheiro.");
                                            novaCorrida.getUsuario().setFormaDePagamento(FormaDePagamento.DINHEIRO);
                                            break;
                                    }
                                    corridaService.solicitarCorrida(novaCorrida);
                                } else if (opcaoUsuario == 2) {
                                    System.out.println("Voltando ao Menu Inicial.");
                                } else {
                                    System.out.println("Opção inválida. Voltando ao Menu Inicial.");
                                }
                            } while (opcaoUsuario != 2);
                        } else {
                            System.out.println("\nErro: Nome de usuário ou telefone inválido.");
                        }
                    } else
                    if (opcao == 2) {
                        // Opção de cadastro de usuário
                        System.out.println("Cadastro de usuário selecionado.");
                        System.out.print("Digite o nome do usuário: ");
                        String nomeUsuario = scanner.next();
                        System.out.print("Digite o telefone do usuário: ");
                        String telefoneUsuario;
                        // Validação do telefone com 8 dígitos
                        do {
                            telefoneUsuario = scanner.next();
                            if (telefoneUsuario.length() != 8) {
                                System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                            }
                        } while (telefoneUsuario.length() != 8);

                        // Criar e cadastrar um novo usuário
                        Usuario novoUsuario = new Usuario(nomeUsuario, telefoneUsuario);
                        usuarioService.cadastrarUsuario(novoUsuario);
                    } else if (opcao == 3) {
                        System.out.println("Voltando ao Menu Inicial.");
                    }
                    break;
                case 2:
                    MenuUtil.exibirMenuLoginMotoqueiro();
                    int opcaoMotoqueiro = scanner.nextInt();

                    if (opcaoMotoqueiro == 1) {
                        System.out.println("Login de motoqueiro selecionado.");
                        System.out.print("Digite seu nome de usuário: ");
                        String nomeMotoqueiro = scanner.next();
                        System.out.print("Digite seu telefone: ");
                        String telefoneMotoqueiro;
                        // Validação do telefone com 8 dígitos
                        do {
                            telefoneMotoqueiro = scanner.next();
                            if (telefoneMotoqueiro.length() != 8) {
                                System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                            }
                        } while (telefoneMotoqueiro.length() != 8);

                        // Verificar credenciais
                        Motoqueiro motoqueiro = motoqueiroService.buscarPorTelefone(telefoneMotoqueiro);
                        String nomeMinusculo = nomeMotoqueiro.toLowerCase();
                        if (motoqueiro != null && motoqueiro.getNome().toLowerCase().equals(nomeMinusculo)) {
                            System.out.println("Login bem-sucedido. Bem-vindo, " + motoqueiro.getNome() + "!");
                            // Menu do motoqueiro após login
                            int opcaoMotoqueiroMenu = 0;
                            do {
                                MenuUtil.exibirMenuMotoqueiro();
                                opcaoMotoqueiroMenu = scanner.nextInt();
                                
                                if (opcaoMotoqueiroMenu == 1) {
                                    System.out.println("Atualizando status de disponibilidade.");
                                    
                                } else if (opcaoMotoqueiroMenu == 2) {
                                    System.out.println("Voltando ao Menu Inicial.");
                                } else {
                                    System.out.println("Opção inválida. Voltando ao Menu Inicial.");
                                }
                            } while (opcaoMotoqueiroMenu != 2);
                        } else {
                            System.out.println("\nErro: Nome de usuário ou telefone inválido.");
                        }
                    }

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
        scanner.close();
    }
}
