package br.com.corrida.model;

public class Motoqueiro extends UsuarioBase {
    // Atributos
    private String placa;
    private boolean disponivel;

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
}
