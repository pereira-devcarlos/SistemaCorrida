package br.com.corrida.model;

public class Motoqueiro extends UsuarioBase {
    // Atributos
    private String placa;
    private boolean disponivel;

    // Construtor
    public Motoqueiro(int id, String nome, String telefone, String placa, boolean disponivel) {
        super(id, nome, telefone);
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
