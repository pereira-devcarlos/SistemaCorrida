package br.com.corrida.model;

import br.com.corrida.enums.FormaDePagamento;

public class Usuario extends UsuarioBase {
    private FormaDePagamento formaDePagamento;

    // Construtor
    public Usuario(String nome, String telefone) {
        super(nome, telefone);
    }

    // Getters e Setters
    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
}
