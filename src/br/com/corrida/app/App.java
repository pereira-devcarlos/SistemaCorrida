package br.com.corrida.app;

import br.com.corrida.model.*;

import java.util.Scanner;

import br.com.corrida.enums.*;
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
                                    motoqueiroService.atualizarDisponibilidade(motoqueiro);
                                } else if (opcaoMotoqueiroMenu == 2) {
                                    System.out.println("Voltando ao Menu Inicial.");
                                } else {
                                    System.out.println("Opção inválida. Voltando ao Menu Inicial.");
                                }
                            } while (opcaoMotoqueiroMenu != 2);
                        } else {
                            System.out.println("\nErro: Nome de usuário ou telefone inválido.");
                        }
                    } else
                        // Caso de cadastro de motoqueiro
                    if (opcaoMotoqueiro == 2) {
                        System.out.println("Cadastro de motoqueiro selecionado.");
                        System.out.print("Digite o nome do motoqueiro: ");
                        String nomeMotoqueiro = scanner.next();
                        System.out.print("Digite o telefone do motoqueiro: ");
                        String telefoneMotoqueiro;
                        // Validação do telefone com 8 dígitos
                        do {
                            telefoneMotoqueiro = scanner.next();
                            if (telefoneMotoqueiro.length() != 8) {
                                System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                            }
                        } while (telefoneMotoqueiro.length() != 8);
                        System.out.print("Digite a placa da moto: ");
                        String placaMoto = scanner.next();

                        // Criar e cadastrar um novo motoqueiro
                        Motoqueiro novoMotoqueiro = new Motoqueiro(nomeMotoqueiro, telefoneMotoqueiro, placaMoto);
                        motoqueiroService.cadastrarMotoqueiro(novoMotoqueiro);
                    } else if (opcaoMotoqueiro == 3) {
                        System.out.println("Voltando ao Menu Inicial.");
                    } else {
                        System.out.println("Opção inválida. Voltando ao Menu Inicial.");
                    }

                    break;
                case 3:
                    // Validar senha de desenvolvedor
                    System.out.print("Digite a senha de desenvolvedor: ");
                    String senhaDesenvolvedor = scanner.next();
                    if (!senhaDesenvolvedor.equals("devcarlos321")) {
                        System.out.println("Senha incorreta. Acesso negado.");
                        break;
                    }
                    System.out.println("Acesso de desenvolvedor concedido.");

                    int opcaoDesenvolvedor = 0;
                    while (opcaoDesenvolvedor != 4) {
                        // Exibir menu de desenvolvedor
                        MenuUtil.exibirMenuDesenvolvedor();
                        opcaoDesenvolvedor = scanner.nextInt();

                        switch (opcaoDesenvolvedor) {
                            case 1:
                                // Gerenciar Usuários
                                MenuUtil.exibirGerenciarUsuario();
                                int opcaoGerenciarUsuario = scanner.nextInt();

                                // Ações de gerenciar usuários
                                if (opcaoGerenciarUsuario == 1) {
                                    // Listar usuários
                                    System.out.println("Lista de Usuários:");
                                    System.out.println(usuarioService.toString());
                                } else 
                                if (opcaoGerenciarUsuario == 2) {
                                    // Adicionar usuário
                                    System.out.print("Digite o nome do novo usuário: ");
                                    String nomeNovoUsuario = scanner.next();
                                    System.out.print("Digite o telefone do novo usuário: ");
                                    String telefoneNovoUsuario;

                                    // Validação do telefone com 8 dígitos
                                    do {
                                        telefoneNovoUsuario = scanner.next();
                                        if (telefoneNovoUsuario.length() != 8) {
                                            System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                                        }
                                    } while (telefoneNovoUsuario.length() != 8);

                                    // Criar e cadastrar o novo usuário
                                    Usuario usuarioParaAdicionar = new Usuario(nomeNovoUsuario, telefoneNovoUsuario);
                                    usuarioService.cadastrarUsuario(usuarioParaAdicionar);
                                } else 
                                if (opcaoGerenciarUsuario == 3) {
                                    // Remover usuário por telefone
                                    System.out.print("Digite o telefone do usuário a ser removido: ");
                                    String telefoneRemover = scanner.next();
                                    usuarioService.deletarUsuario(telefoneRemover);
                                } else 
                                if (opcaoGerenciarUsuario == 4) {
                                    // Voltar ao menu desenvolvedor
                                    System.out.println("Voltando ao Menu Desenvolvedor.");
                                } else {
                                    System.out.println("Opção inválida. Voltando ao Menu Desenvolvedor.");
                                }
                                break;
                            case 2:
                                // Gerenciar Motoqueiros
                                int opcaoGerenciarMotoqueiro = 0;
                                while (opcaoGerenciarMotoqueiro != 4) {
                                    // Exibir menu de gerenciar motoqueiros
                                    MenuUtil.exibirGerenciarMotoqueiro();
                                    opcaoGerenciarMotoqueiro = scanner.nextInt();

                                    switch (opcaoGerenciarMotoqueiro) {
                                        // Listar motoqueiros
                                        case 1:
                                            System.out.println("Lista de Motoqueiros:");
                                            System.out.println(motoqueiroService.toString());
                                            break;
                                        // Adicionar motoqueiro
                                        case 2:
                                            System.out.print("Digite o nome do novo motoqueiro: ");
                                            String nomeNovoMotoqueiro = scanner.next();
                                            System.out.print("Digite o telefone do novo motoqueiro: ");
                                            String telefoneNovoMotoqueiro;

                                            // Validação do telefone com 8 dígitos
                                            do {
                                                telefoneNovoMotoqueiro = scanner.next();
                                                if (telefoneNovoMotoqueiro.length() != 8) {
                                                    System.out.print("Telefone inválido. Digite um telefone com 8 dígitos: ");
                                                }
                                            } while (telefoneNovoMotoqueiro.length() != 8);

                                            // Solicitar a placa da moto
                                            System.out.print("Digite a placa da moto: ");
                                            String placaNovaMoto = scanner.next();
            
                                            // Criar e cadastrar o novo motoqueiro
                                            Motoqueiro motoqueiroParaAdicionar = new Motoqueiro(nomeNovoMotoqueiro, telefoneNovoMotoqueiro, placaNovaMoto);
                                            motoqueiroService.cadastrarMotoqueiro(motoqueiroParaAdicionar);
                                            break;
                                        case 3:
                                            // Remover motoqueiro por telefone
                                            System.out.print("Digite o telefone do motoqueiro a ser removido: ");
                                            String telefoneRemoverMotoqueiro = scanner.next();
                                            motoqueiroService.deletarMotoqueiro(telefoneRemoverMotoqueiro);
                                            break;
                                        case 4:
                                            // Voltar ao menu desenvolvedor
                                            System.out.println("Voltando ao Menu Desenvolvedor.");
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Tente novamente.");
                                            break;
                                    }
                                }
                                break;
                            case 3:
                                int opcaoRelatorios = 0;
                                while (opcaoRelatorios != 4) {
                                    // Exibir menu de relatórios de corridas
                                    MenuUtil.exibirRelatoriosCorridas();
                                    opcaoRelatorios = scanner.nextInt();

                                    switch (opcaoRelatorios) {
                                        case 1:
                                            // Ver todas as corridas
                                            System.out.println("Relatório de Todas as Corridas:");
                                            corridaService.listarTodasCorridas();
                                            break;
                                        case 2:
                                            // Ver corridas por usuário
                                            System.out.print("Digite o telefone do usuário para ver suas corridas: ");
                                            String telefoneUsuarioRelatorio = scanner.next();
                                            Usuario usuarioRelatorio = usuarioService.buscarPorTelefone(telefoneUsuarioRelatorio);

                                            // Verificar se o usuário existe
                                            if (usuarioRelatorio != null) {
                                                System.out.println("Relatório de Corridas do Usuário " + usuarioRelatorio.getNome() + ":");
                                                corridaService.listarCorridasPorUsuario(usuarioRelatorio);
                                            } else {
                                                System.out.println("Usuário não encontrado.");
                                            }
                                            break;
                                        case 3:
                                            // Ver corridas por motoqueiro
                                            System.out.print("Digite o telefone do motoqueiro para ver suas corridas: ");
                                            String telefoneMotoqueiroRelatorio = scanner.next();
                                            Motoqueiro motoqueiroRelatorio = motoqueiroService.buscarPorTelefone(telefoneMotoqueiroRelatorio);

                                            // Verificar se o motoqueiro existe
                                            if (motoqueiroRelatorio != null) {
                                                System.out.println("Relatório de Corridas do Motoqueiro " + motoqueiroRelatorio.getNome() + ":");
                                                corridaService.listarCorridasPorMotoqueiro(motoqueiroRelatorio);
                                            } else {
                                                System.out.println("Motoqueiro não encontrado.");
                                            }
                                            break;
                                        case 4:
                                            // Voltar ao menu desenvolvedor
                                            System.out.println("Voltando ao Menu Desenvolvedor.");
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Tente novamente.");
                                            break;
                                    }
                                }
                                break;
                            case 4:
                                // Voltar ao menu inicial
                                System.out.println("Voltando ao Menu Inicial.");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                            }
                    }
                    break;
                case 4:
                    // Sair do sistema
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        // Fechar o scanner
        scanner.close();
    }
}
