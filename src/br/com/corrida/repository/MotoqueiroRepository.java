package br.com.corrida.repository;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import br.com.corrida.model.Motoqueiro;

public class MotoqueiroRepository {
    // Atributos
    private ArrayList<Motoqueiro> motoqueiros;
    // Definindo o caminho do arquivo de dados
    String fileName = "C:\\Users\\monic\\OneDrive\\Documentos\\SistemaCorrida\\data\\motoqueiros.txt";

    // Construtor
    public MotoqueiroRepository() {
        this.motoqueiros = new ArrayList<Motoqueiro>();
    }

    public void carregarDados() {
        // Lógica para carregar os dados dos motoqueiros do banco de dados para a memória usando um ArrayList
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                String telefone = parts[2];
                String placa = parts[3];
                boolean disponivel = Boolean.parseBoolean(parts[4]);
                Motoqueiro motoqueiro = new Motoqueiro(nome, telefone, placa, disponivel);
                motoqueiro.setId(id);
                motoqueiros.add(motoqueiro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int gerarNovoId() {
        // Lógica para gerar um novo ID único para o motoqueiro
        int maxId = 0;
        for (Motoqueiro motoqueiro : motoqueiros) {
            if (motoqueiro.getId() > maxId) {
                maxId = motoqueiro.getId();
            }
        }
        return maxId + 1;
    }

    public void salvar(Motoqueiro motoqueiro) {
        // Lógica para salvar o motoqueiro no banco de dados
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(motoqueiro.getId() + ";" + motoqueiro.getNome() + ";" + motoqueiro.getTelefone() + ";" + motoqueiro.getPlaca() + ";" + motoqueiro.isDisponivel());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Adiciona o motoqueiro à lista em memória
        motoqueiros.add(motoqueiro);
    }

    public Motoqueiro buscarPorTelefone(String telefone) {
        // Lógica para buscar um motoqueiro pelo telefone
        for (Motoqueiro motoqueiro : motoqueiros) {
            if (motoqueiro.getTelefone().equals(telefone)) {
                return motoqueiro;
            }
        }
        return null;
    }

    public ArrayList<Motoqueiro> listarDisponiveis() {
        // Lógica para listar todos os motoqueiros disponíveis
        ArrayList<Motoqueiro> disponiveis = new ArrayList<>();
        for (Motoqueiro motoqueiro : motoqueiros) {
            if (motoqueiro.isDisponivel()) {
                disponiveis.add(motoqueiro);
            }
        }
        return disponiveis;
    }

    // Getters e Setters
    public ArrayList<Motoqueiro> getMotoqueiros() {
        return motoqueiros;
    }
}