package br.com.corrida.model;

import br.com.corrida.enums.StatusCorrida;

public class Corrida {
    // Atributos
    private int id = 0;
    private Usuario usuario;
    private Motoqueiro motoqueiro;
    private double distancia;
    private int valor;
    private StatusCorrida status;

    // Construtor
    public Corrida() {
    }

    public Corrida(Usuario usuario, int valor) {
        this.usuario = usuario;
        this.valor = valor;
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

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
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
