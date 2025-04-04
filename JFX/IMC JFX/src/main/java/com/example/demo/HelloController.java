package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private TextField nomeField;

    @FXML
    private TextField alturaField;

    @FXML
    private TextField pesoField;

    @FXML
    private TableView<List<String>> tableView;

    @FXML
    private TableColumn<List<String>, String> nomeColumn;

    @FXML
    private TableColumn<List<String>, String> alturaColumn;

    @FXML
    private TableColumn<List<String>, String> pesoColumn;

    @FXML
    private TableColumn<List<String>, String> imcColumn;

    private ObservableList<List<String>> pessoas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        alturaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        pesoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        imcColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));

        alturaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pesoColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.setEditable(true);

        alturaColumn.setOnEditCommit(event -> {
            List<String> pessoa = event.getRowValue();
            pessoa.set(1, event.getNewValue());
            atualizarIMC(pessoa);
            tableView.refresh();
        });

        pesoColumn.setOnEditCommit(event -> {
            List<String> pessoa = event.getRowValue();
            pessoa.set(2, event.getNewValue());
            atualizarIMC(pessoa);
            tableView.refresh();
        });

        tableView.setItems(pessoas);
    }

    @FXML
    private void calcularIMC() {
        String nome = nomeField.getText();
        double altura = Double.parseDouble(alturaField.getText());
        double peso = Double.parseDouble(pesoField.getText());
        double imc = peso / (altura * altura);
        String classificacao = classificarIMC(imc);

        List<String> pessoa = new ArrayList<>();
        pessoa.add(nome);
        pessoa.add(String.valueOf(altura));
        pessoa.add(String.valueOf(peso));
        pessoa.add(classificacao);

        pessoas.add(pessoa);
    }

    private void atualizarIMC(List<String> pessoa) {
        double altura = Double.parseDouble(pessoa.get(1));
        double peso = Double.parseDouble(pessoa.get(2));
        double imc = peso / (altura * altura);
        String classificacao = classificarIMC(imc);
        pessoa.set(3, classificacao);
    }

    private String classificarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else {
            return "Sobrepeso";
        }
    }

    @FXML
    private void editarDados() {
        List<String> pessoa = pessoas.get(tableView.getSelectionModel().getSelectedIndex());

        pessoa.set(0, "Novo Nome");
        pessoa.set(1, "1.80");
        pessoa.set(2, "75");
        pessoa.set(3, "Peso normal");

        salvarDados();
    }

    @FXML
    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.txt"))) {
            for (List<String> pessoa : pessoas) {
                writer.write(String.join(",", pessoa));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void carregarDados() {
        pessoas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("dados.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                List<String> pessoa = new ArrayList<>();
                for (String dado : dados) {
                    pessoa.add(dado);
                }
                pessoas.add(pessoa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableView.refresh();
    }
}
