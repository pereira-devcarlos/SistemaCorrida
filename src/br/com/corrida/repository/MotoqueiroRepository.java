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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Motoqueiro motoqueiro : motoqueiros) {
            sb.append("ID: ").append(motoqueiro.getId())
              .append(", Nome: ").append(motoqueiro.getNome())
              .append(", Telefone: ").append(motoqueiro.getTelefone())
              .append(", Placa: ").append(motoqueiro.getPlaca())
              .append(", Disponível: ").append(motoqueiro.isDisponivel())
              .append(", Conta Bancária: ").append(motoqueiro.getContaBancaria())
              .append("\n");
        }
        return sb.toString();
    }

    public void carregarDados() {
        // Lógica para carregar os dados dos motoqueiros do banco de dados para a memória usando um ArrayList
        motoqueiros.clear(); // Limpa a lista antes de carregar os dados

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                String telefone = parts[2];
                String placa = parts[3];
                boolean disponivel = Boolean.parseBoolean(parts[4]);
                double contaBancaria = Double.parseDouble(parts[5]);
                Motoqueiro motoqueiro = new Motoqueiro(nome, telefone, placa);
                motoqueiro.setContaBancaria(contaBancaria);
                motoqueiro.setId(id);
                motoqueiro.setDisponivel(disponivel);
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
            writer.write(motoqueiro.getId() + ";" + motoqueiro.getNome() + ";" + motoqueiro.getTelefone() + ";" + motoqueiro.getPlaca() + ";" + motoqueiro.isDisponivel() + ";" + motoqueiro.getContaBancaria());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Adiciona o motoqueiro à lista em memória
        motoqueiros.add(motoqueiro);
    }

    public void atualizar(Motoqueiro motoqueiro) {
        // Lógica para atualizar os dados do motoqueiro no banco de dados
        // Primeiro, atualiza a lista em memória
        for (int i = 0; i < motoqueiros.size(); i++) {
            if (motoqueiros.get(i).getId() == motoqueiro.getId()) {
                motoqueiros.set(i, motoqueiro);
                break;
            }
        }
        // Depois, reescreve todo o arquivo com os dados atualizados
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Motoqueiro m : motoqueiros) {
                writer.write(m.getId() + ";" + m.getNome() + ";" + m.getTelefone() + ";" + m.getPlaca() + ";" + m.isDisponivel() + ";" + m.getContaBancaria());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Motoqueiro motoqueiro) {
        // Lógica para deletar o motoqueiro do banco de dados
        // Primeiro, remove o motoqueiro da lista em memória
        motoqueiros.removeIf(m -> m.getId() == motoqueiro.getId());
        // Depois, reescreve todo o arquivo sem o motoqueiro deletado
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Motoqueiro m : motoqueiros) {
                writer.write(m.getId() + ";" + m.getNome() + ";" + m.getTelefone() + ";" + m.getPlaca() + ";" + m.isDisponivel() + ";" + m.getContaBancaria());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Motoqueiro buscarPorTelefone(String telefone) {
        // Lógica para buscar um motoqueiro pelo telefone no arquivo txt
        motoqueiros.clear();
        carregarDados();
        for (Motoqueiro motoqueiro : motoqueiros) {
            if (motoqueiro.getTelefone().equals(telefone)) {
                return motoqueiro;
            }
        }
        return null;
    }

    public ArrayList<Motoqueiro> listarDisponiveis() {
        // Lógica para listar todos os motoqueiros disponíveis do arquivo txt
        carregarDados();
        ArrayList<Motoqueiro> disponiveis = new ArrayList<Motoqueiro>();
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