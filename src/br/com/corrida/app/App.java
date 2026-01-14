package br.com.corrida.app;

import br.com.corrida.model.*;
import br.com.corrida.enums.*;
import br.com.corrida.repository.*;
import br.com.corrida.service.*;

public class App {
    public static void main(String[] args) throws Exception {
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.cadastrarUsuario(new Usuario("Monica", "99260346"));
        // usuarioService.deletarUsuario("99260346");

        System.out.println("Usuários cadastrados:");
        System.out.println(usuarioService);
    }
}
