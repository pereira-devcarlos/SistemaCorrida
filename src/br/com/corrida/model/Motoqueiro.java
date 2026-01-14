package br.com.corrida.model;

public class Motoqueiro extends UsuarioBase {
    // Atributos
    private String placa;
    private String modelo;

    // Construtor
    public Motoqueiro(int id, String nome, String telefone, String placa, String modelo) {
        super(id, nome, telefone);
        this.placa = placa;
        this.modelo = modelo;
    }

    // Getters e Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
