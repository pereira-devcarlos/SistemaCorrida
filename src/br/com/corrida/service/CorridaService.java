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
        corrida.setValor(calcularValorCorrida(corrida.getDistancia()));
        System.out.println("Corrida solicitada com sucesso. ID da corrida: " + corrida.getId());
        System.out.println("Valor estimado: R$ " + corrida.getValor());
        // Aguardar disponibilidade de motoqueiro
        try {
            System.out.println("Aguardando motoqueiro disponível...");
            Thread.sleep(2000); // Simula tempo de espera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
            System.out.println("A corrida está em andamento...");
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
            System.out.println("Corrida finalizada. valor total: R$ " + corrida.getValor());
        } else {
            corrida.setStatus(StatusCorrida.FINALIZADA);
            corridaRepository.salvar(corrida);
            System.out.println("Corrida finalizada sem motoqueiro disponível.");
        }
    }

    public double calcularValorCorrida(double distancia) {
        double valorPorKm = 5.0; // Exemplo de valor por km
        double valor = distancia * valorPorKm;
        return (valor < 10.0) ? 10.0 : valor; // Valor mínimo de 10.0
    }
}
