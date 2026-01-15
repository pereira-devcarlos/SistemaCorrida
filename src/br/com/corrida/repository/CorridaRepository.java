package br.com.corrida.repository;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import br.com.corrida.model.Corrida;

public class CorridaRepository {
    // Atributos
    private ArrayList<Corrida> corridas;
    String fileName = "C:\\Users\\monic\\OneDrive\\Documentos\\SistemaCorrida\\data\\corrida.txt";

    // Construtor
    public CorridaRepository() {
        this.corridas = new ArrayList<>();
    }

    public void carregarDados() {
        // Carregar corridas do arquivo txt
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    Corrida corrida = new Corrida(null, Integer.parseInt(partes[3]));
                    corrida.setId(Integer.parseInt(partes[0]));
                    corrida.setMotoqueiro(null);
                    // Note: Usuario and Motoqueiro should be set by their respective services
                    corrida.setStatus(Enum.valueOf(br.com.corrida.enums.StatusCorrida.class, partes[4]));
                    corridas.add(corrida);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar corridas: " + e.getMessage());
        }
    }
    
    public void salvar(Corrida corrida) {
        // Salvar corrida no arquivo txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String linha = corrida.getId() + ";" + corrida.getUsuario().getTelefone() + ";" +
                           corrida.getMotoqueiro().getTelefone() + ";" + corrida.getValor() + ";" +
                           corrida.getStatus();
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar corrida: " + e.getMessage());
        }
        corridas.add(corrida);
    }

    public int gerarNovoId() {
        int maxId = 0;
        for (Corrida corrida : corridas) {
            if (corrida.getId() > maxId) {
                maxId = corrida.getId();
            }
        }
        return maxId + 1;
    }
}
