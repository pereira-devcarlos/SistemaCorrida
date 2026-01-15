package br.com.corrida.service;

import br.com.corrida.model.Motoqueiro;
import br.com.corrida.repository.MotoqueiroRepository;

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

    public void salarioCorrida(Motoqueiro motoqueiro, double valor){
        motoqueiro.setContaBancaria(motoqueiro.getContaBancaria() + valor);
    }
}
