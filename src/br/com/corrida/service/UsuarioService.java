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
        // Listar todos os usuários do ArrayList
        StringBuilder sb = new StringBuilder();
        for (Usuario usuario : usuarioRepository.getUsuarios()) {
            sb.append("ID: ").append(usuario.getId())
              .append(", Nome: ").append(usuario.getNome())
              .append(", Telefone: ").append(usuario.getTelefone())
              .append("\n");
        }
        return sb.toString();
    }

    public void cadastrarUsuario(Usuario usuario) {
        // Lógica para cadastrar o usuário
        if (usuarioRepository.buscarPorTelefone(usuario.getTelefone()) != null) {
            System.out.println("Erro: Usuário já cadastrado.");
            return;
        } else {
            System.out.println("Usuário " + usuario.getNome() + " cadastrado com sucesso.");
            usuario.setId(usuarioRepository.gerarNovoId());
            usuarioRepository.salvar(usuario);
            usuarioRepository.getUsuarios().add(usuario);
        }
    }
}
