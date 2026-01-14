package br.com.corrida.service;

import br.com.corrida.model.Usuario;
import br.com.corrida.repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
        this.usuarioRepository.carregarDados();
    }

    @Override
    public String toString() {
        return "UsuarioService{" +
                "usuarioRepository=" + usuarioRepository +
                '}';
    }

    public void cadastrarUsuario(Usuario usuario) {
        // Lógica para cadastrar o usuário
        usuarioRepository.salvar(usuario);
        usuarioRepository.getUsuarios().add(usuario);
    }
}
