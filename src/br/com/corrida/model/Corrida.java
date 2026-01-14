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

    // Getters
    public int getId() {
        return id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Motoqueiro getMotoqueiro() {
        return motoqueiro;
    }
    public int getValor() {
        return valor;
    }
    public StatusCorrida getStatus() {
        return status;
    }    
}
