package br.com.corrida.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import br.com.corrida.model.Usuario;

public class UsuarioRepository {
    private ArrayList<Usuario> usuarios;
    String filename = "C:\\Users\\monic\\OneDrive\\Documentos\\SistemaCorrida\\data\\usuarios.txt";

    public void carregarDados() {
        // Lógica para carregar os dados dos usuários do banco de dados para a memória usando um ArrayList
        usuarios = new ArrayList<Usuario>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                String telefone = parts[2];
                Usuario usuario = new Usuario(nome, telefone);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int gerarNovoId() {
        // Lógica para gerar um novo ID único para o usuário
        int maxId = 0;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() > maxId) {
                maxId = usuario.getId();
            }
        }
        return maxId + 1;
    }

    public void salvar(Usuario usuario) {
        // Lógica para salvar o usuário no banco de dados
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(usuario.getId() + ";" + usuario.getNome() + ";" + usuario.getTelefone());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPorId(int id) {
        // Lógica para buscar o usuário pelo ID no banco de dados
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int usuarioId = Integer.parseInt(parts[0]);
                if (usuarioId == id) {
                    String nome = parts[1];
                    String telefone = parts[2];
                    return new Usuario(nome, telefone);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public Usuario buscarPorTelefone(String telefone) {
        // Lógica para buscar o usuário pelo telefone no banco de dados
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String usuarioTelefone = parts[2];
                if (usuarioTelefone.equals(telefone)) {
                    String nome = parts[1];
                    return new Usuario(nome, usuarioTelefone);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    // Getters e Setters
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}