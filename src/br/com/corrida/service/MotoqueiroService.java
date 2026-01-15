package br.com.corrida.service;

import br.com.corrida.model.Motoqueiro;
import br.com.corrida.repository.MotoqueiroRepository;

import java.util.ArrayList;

public class MotoqueiroService {
    // Atributos
    private MotoqueiroRepository motoqueiroRepository;

    // Construtor
    public MotoqueiroService() {
        this.motoqueiroRepository = new MotoqueiroRepository();
        this.motoqueiroRepository.carregarDados();
    }

    public void cadastrarMotoqueiro(Motoqueiro motoqueiro) {
        // Lógica para cadastrar um novo motoqueiro
        if (motoqueiroRepository.buscarPorTelefone(motoqueiro.getTelefone()) != null) {
            System.out.println("Erro: Já existe um motoqueiro cadastrado com este telefone.");
        } else {
            System.out.println("Motoqueiro " + motoqueiro.getNome() + " cadastrado com sucesso!");
            motoqueiro.setId(this.motoqueiroRepository.gerarNovoId());
            this.motoqueiroRepository.salvar(motoqueiro);
        }
    }

    public void atualizarMotoqueiro(Motoqueiro motoqueiro){
        motoqueiroRepository.atualizar(motoqueiro);
    }

    public Motoqueiro buscarPorTelefone(String telefone){
        return motoqueiroRepository.buscarPorTelefone(telefone);
    }

    public ArrayList<Motoqueiro> listarDisponiveis(){
        return motoqueiroRepository.listarDisponiveis();
    }

    public void atualizarDisponibilidade(Motoqueiro motoqueiro){
        motoqueiro.setDisponivel(!motoqueiro.isDisponivel());
        System.out.println("\nDepuração: " + motoqueiro.isDisponivel());
        motoqueiroRepository.atualizar(motoqueiro);
    }

    public void salarioCorrida(Motoqueiro motoqueiro, double valor){
        double valorFormatado = Math.round(valor * 100.0) / 100.0;
        double novoSaldo = motoqueiro.getContaBancaria() + valorFormatado;
        novoSaldo = Math.round(novoSaldo * 100.0) / 100.0;
        motoqueiro.setContaBancaria(novoSaldo);
        atualizarMotoqueiro(motoqueiro);
    }
}
