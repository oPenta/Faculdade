package org.sysimc.utils;

import org.sysimc.model.Pessoa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String NOME_ARQUIVO = "dados_pessoas.txt";

    public static void salvar(List<Pessoa> pessoas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Pessoa p : pessoas) {
                writer.write(p.paraFormatoCsv());
                writer.newLine();
            }
        }
    }

    public static List<Pessoa> carregar() {
        List<Pessoa> pessoas = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return pessoas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    try {
                        String nome = dados[0].trim();
                        double peso = Double.parseDouble(dados[1].trim());
                        double altura = Double.parseDouble(dados[2].trim());
                        pessoas.add(new Pessoa(nome, peso, altura));
                    } catch (NumberFormatException e) {
                        System.err.println("Linha mal formatada no arquivo: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
        }
        return pessoas;
    }
}
