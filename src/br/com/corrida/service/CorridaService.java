package br.com.corrida.service;

import br.com.corrida.model.*;
import br.com.corrida.enums.StatusCorrida;

public class CorridaService {
    public void solicitarCorrida(Corrida corrida) {
        corrida.setStatus(StatusCorrida.SOLICITADA);
    }

    public void iniciarCorrida(Corrida corrida) {
        corrida.setStatus(StatusCorrida.EM_ANDAMENTO);
    }

    public void finalizarCorrida(Corrida corrida) {
        corrida.setStatus(StatusCorrida.FINALIZADA);
    }
}
