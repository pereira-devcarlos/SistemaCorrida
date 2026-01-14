package br.com.corrida.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import br.com.corrida.model.Usuario;

public class UsuarioRepository {
    public void salvar(Usuario usuario) {
        // Lógica para salvar o usuário no banco de dados
        String filename = "C:\\Users\\monic\\OneDrive\\Documentos\\SistemaCorrida\\data\\usuarios.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(usuario.getId() + ";" + usuario.getNome() + ";" + usuario.getTelefone());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPorId(int id) {
        // Lógica para buscar o usuário pelo ID no banco de dados
        String filename = "C:\\Users\\monic\\OneDrive\\Documentos\\SistemaCorrida\\data\\usuarios.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int usuarioId = Integer.parseInt(parts[0]);
                if (usuarioId == id) {
                    String nome = parts[1];
                    String telefone = parts[2];
                    return new Usuario(usuarioId, nome, telefone);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o usuário não for encontrado
    }
}