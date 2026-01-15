package br.com.corrida.service;

import br.com.corrida.model.*;
import br.com.corrida.service.MotoqueiroService;
import br.com.corrida.enums.StatusCorrida;
import br.com.corrida.enums.FormaDePagamento;
import br.com.corrida.repository.*;
import br.com.corrida.util.MenuUtil;

public class CorridaService {
    private CorridaRepository corridaRepository;
    private MotoqueiroRepository motoqueiroRepository;
    private MotoqueiroService motoqueiroService = new MotoqueiroService();

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

        // Verificar motoqueiros disponíveis
        if (motoqueiroService.listarDisponiveis().size() > 0) {
            Motoqueiro motoqueiro = motoqueiroService.listarDisponiveis().get(0);
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
            System.out.println("Corrida finalizada. Obrigado por usar nosso serviço!");

            if (corrida.getUsuario().getFormaDePagamento() == FormaDePagamento.DINHEIRO ||
                corrida.getUsuario().getFormaDePagamento() == FormaDePagamento.PIX) {
                motoqueiroService.salarioCorrida(motoqueiro, corrida.getValor() * 0.85);
            } else {
                motoqueiroService.salarioCorrida(motoqueiro, corrida.getValor() * 0.75);
            } 
            motoqueiroService.atualizarMotoqueiro(motoqueiro);
        } else {
            corrida.setStatus(StatusCorrida.FINALIZADA);
            corridaRepository.salvarCorridaCancelada(corrida);
            System.out.println("Corrida finalizada sem motoqueiro disponível.");
        }
    }

    public double calcularValorCorrida(double distancia) {
        double valorPorKm = 5.0; // Exemplo de valor por km
        double valor = distancia * valorPorKm;
        return (valor < 10.0) ? 10.0 : valor; // Valor mínimo de 10.0
    }
}
