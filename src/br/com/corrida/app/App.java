package br.com.corrida.app;

import br.com.corrida.model.*;
import br.com.corrida.enums.*;
import br.com.corrida.repository.*;
import br.com.corrida.service.*;

public class App {
    public static void main(String[] args) throws Exception {
        UsuarioService usuarioService = new UsuarioService();
        MotoqueiroRepository motoqueiroRepository = new MotoqueiroRepository();
        CorridaService corridaService = new CorridaService();
        corridaService.solicitarCorrida(new Corrida(usuarioService.buscarPorTelefone("99260346"),25));
    }
}
