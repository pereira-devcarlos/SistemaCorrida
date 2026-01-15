package br.com.corrida.model;

public class Motoqueiro extends UsuarioBase {
    // Atributos
    private String placa;
    private boolean disponivel;
    private double contaBancaria;

    // Construtor
    public Motoqueiro(String nome, String telefone, String placa, boolean disponivel) {
        super(nome, telefone);
        this.placa = placa;
        this.disponivel = disponivel;
    }

    // Getters e Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(double contaBancaria) {
        this.contaBancaria = contaBancaria;
    }
}
