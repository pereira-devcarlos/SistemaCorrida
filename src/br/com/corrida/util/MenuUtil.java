package br.com.corrida.util;

public class MenuUtil {
    public static void exibirMenuInicial(){
        System.out.println("\nSeja bem-vindo ao Sistema de Corrida!");
        System.out.println("Qual opcão você se encontra?");
        System.out.println("1. Usuário");
        System.out.println("2. Motoqueiro");
        System.out.println("3. Desenvolvedor");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuLoginUsuario(){
        System.out.println("\nMenu Login Usuário:");
        System.out.println("1. Login");
        System.out.println("2. Cadastra-se");
        System.out.println("3. Voltar ao Menu Inicial");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuUsuario(){
        System.out.println("\nMenu Usuário:");
        System.out.println("1. Solicitar Corrida");
        System.out.println("2. Voltar ao Menu Inicial");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuLoginMotoqueiro(){
        System.out.println("\nMenu Login Motoqueiro:");
        System.out.println("1. Login");
        System.out.println("2. Cadastra-se");
        System.out.println("3. Voltar ao Menu Inicial");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuMotoqueiro(){
        System.out.println("\nMenu Motoqueiro:");
        System.out.println("1. Ver Corridas Disponíveis");
        System.out.println("2. Atualizar Status de Disponibilidade");
        System.out.println("3. Voltar ao Menu Inicial");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuDesenvolvedor(){
        System.out.println("\nMenu Desenvolvedor:");
        System.out.println("1. Gerenciar Usuários");
        System.out.println("2. Gerenciar Motoqueiros");
        System.out.println("3. Ver Relatórios de Corridas");
        System.out.println("4. Voltar ao Menu Inicial");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirFormasDePagamento(){
        System.out.println("\nFormas de Pagamento:");
        System.out.println("1. Dinheiro");
        System.out.println("2. Cartão de Crédito");
        System.out.println("3. Cartão de Débito");
        System.out.println("4. Pix");
        System.out.print("Escolha uma forma de pagamento: ");
    }
}
