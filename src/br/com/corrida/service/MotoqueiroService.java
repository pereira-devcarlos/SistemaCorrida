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
        motoqueiro.setId(this.motoqueiroRepository.gerarNovoId());
        this.motoqueiroRepository.salvar(motoqueiro);
    }
}
