package br.com.corrida.service;

import br.com.corrida.model.*;
import br.com.corrida.enums.StatusCorrida;
import br.com.corrida.repository.*;

public class CorridaService {
    private CorridaRepository corridaRepository;
    private MotoqueiroRepository motoqueiroRepository;

    public CorridaService() {
        this.corridaRepository = new CorridaRepository();
        this.corridaRepository.carregarDados();
        this.motoqueiroRepository = new MotoqueiroRepository();
        this.motoqueiroRepository.carregarDados();
    }

    public void solicitarCorrida(Corrida corrida) {
        corrida.setStatus(StatusCorrida.SOLICITADA);
        corrida.setId(corridaRepository.gerarNovoId());

        if (motoqueiroRepository.listarDisponiveis().size() > 0) {
            Motoqueiro motoqueiro = motoqueiroRepository.listarDisponiveis().get(0);
            iniciarCorrida(corrida, motoqueiro);
        } else {
            finalizarCorrida(corrida, null);
        }
    }

    public void iniciarCorrida(Corrida corrida, Motoqueiro motoqueiro) {
        corrida.setMotoqueiro(motoqueiro);
        motoqueiro.setDisponivel(false);
        corrida.setStatus(StatusCorrida.EM_ANDAMENTO);
        System.out.println("Corrida iniciada com o motoqueiro: " + motoqueiro.getNome());
        // Depois de 3s, finalizar a corrida
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finalizarCorrida(corrida, motoqueiro);
    }

    public void finalizarCorrida(Corrida corrida, Motoqueiro motoqueiro) {
        if (motoqueiro != null) {
            corrida.setStatus(StatusCorrida.FINALIZADA);
            motoqueiro.setDisponivel(true);
            corridaRepository.salvar(corrida);
            System.out.println("Corrida finalizada. Motoqueiro " + motoqueiro.getNome() + " agora está disponível.");
        } else {
            corrida.setStatus(StatusCorrida.FINALIZADA);
            corridaRepository.salvar(corrida);
            System.out.println("Corrida finalizada sem motoqueiro disponível.");
        }
    }
}
