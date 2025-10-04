package org.example.imc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String NOME_ARQUIVO = "dados_imc.txt";

    public static void salvarDados(List<Pessoa> pessoas) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            writer.println("Nome;Altura;Peso"); // Cabeçalho do CSV
            for (Pessoa p : pessoas) {
                // Usamos ponto como separador decimal para padronização
                writer.printf("%s;%.2f;%.2f\n", p.getNome(), p.getAltura(), p.getPeso())
                        .flush();
            }
        }
    }

    public static List<Pessoa> carregarDados() throws IOException {
        List<Pessoa> pessoas = new ArrayList<>();
        if (!Files.exists(Paths.get(NOME_ARQUIVO))) {
            return pessoas; // Retorna lista vazia se o arquivo não existe
        }

        List<String> linhas = Files.readAllLines(Paths.get(NOME_ARQUIVO));
        if (linhas.isEmpty()) {
            return pessoas;
        }

        linhas.remove(0); // Remove o cabeçalho

        for (String linha : linhas) {
            String[] campos = linha.split(";");
            if (campos.length == 3) {
                String nome = campos[0];
                double altura = Double.parseDouble(campos[1].replace(",", "."));
                double peso = Double.parseDouble(campos[2].replace(",", "."));
                pessoas.add(new Pessoa(nome, altura, peso));
            }
        }
        return pessoas;
    }
}