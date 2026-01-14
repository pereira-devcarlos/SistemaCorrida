package br.com.corrida.model;

import br.com.corrida.enums.StatusCorrida;

public class Corrida {
    // Atributos
    private int id = 0;
    private Usuario usuario;
    private Motoqueiro motoqueiro;
    private int valor;
    private StatusCorrida status;

    // Construtor
    public Corrida(Usuario usuario, Motoqueiro motoqueiro, int valor) {
        id++; // Incrementa o ID para cada nova corrida
        this.usuario = usuario;
        this.motoqueiro = motoqueiro;
        this.valor = valor;
        this.status = StatusCorrida.SOLICITADA; // Status inicial
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Motoqueiro getMotoqueiro() {
        return motoqueiro;
    }

    public void setMotoqueiro(Motoqueiro motoqueiro) {
        this.motoqueiro = motoqueiro;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public StatusCorrida getStatus() {
        return status;
    }

    public void setStatus(StatusCorrida status) {
        this.status = status;
    }

    
}
