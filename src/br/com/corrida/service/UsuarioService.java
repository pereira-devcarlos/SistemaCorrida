package br.com.corrida.service;

import br.com.corrida.model.Usuario;
import br.com.corrida.repository.UsuarioRepository;

public class UsuarioService {
    // Atributos
    private UsuarioRepository usuarioRepository;

    // Construtor
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
    
    public void deletarUsuario(String telefone) {
        Usuario usuario = null;
        for (Usuario u : usuarioRepository.getUsuarios()) {
            if (u.getTelefone().equals(telefone)) {
                usuario = u;
                break;
            }
        }
        if (usuario != null) {
            usuarioRepository.getUsuarios().remove(usuario);
            System.out.println("Usuário: Nome= " + usuario.getNome() + "  Telefone= " + usuario.getTelefone() + " deletado com sucesso.");
            usuarioRepository.deletar(usuario.getId());
        } else {
            System.out.println("Erro: Usuário não encontrado.");
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        // Lógica para atualizar o usuário
        Usuario usuarioExistente = usuarioRepository.buscarPorId(usuario.getId());
        if (usuarioExistente != null) {
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setTelefone(usuario.getTelefone());
            usuarioRepository.atualizar(usuarioExistente);
            System.out.println("Usuário atualizado com sucesso.");
        } else {
            System.out.println("Erro: Usuário não encontrado.");
        }
    }

    public Usuario buscarPorTelefone(String telefone) {
        return usuarioRepository.buscarPorTelefone(telefone);
    }
}
