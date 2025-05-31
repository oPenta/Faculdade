package org.imc.calculoimc.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.imc.calculoimc.model.Pessoa;
import org.imc.calculoimc.utils.IDGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Pessoa pessoa;
    private List<Pessoa> listPessoas;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtAltura;
    @FXML
    private TextField txtPeso;
    @FXML
    private Label lbIMC;
    @FXML
    private Label labelTipoDeImc;

    @FXML
    private Button btnNovo;
    @FXML
    private Button btnIMC;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCarregar;

    @FXML
    private TableView<Pessoa> tableView;

    @FXML
    private TableColumn<Pessoa, String> nomeColumn;
    @FXML
    private TableColumn<Pessoa, String> alturaColumn;
    @FXML
    private TableColumn<Pessoa, String> pesoColumn;
    @FXML
    private TableColumn<Pessoa, String> imcColumn;
    @FXML
    private TableColumn<Pessoa, String> classificacaoColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.btnNovo.setDisable(true);
        this.btnCarregar.setDisable(true);
        this.pessoa = new Pessoa();
        this.listPessoas = new ArrayList<>();

        // Configuração das colunas
        nomeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNome()));
        alturaColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getAltura())));
        pesoColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPeso())));
        imcColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.format("%.2f", cellData.getValue().getImc())));
        classificacaoColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClassificacao()));
    }

    @FXML
    public void onBtnCalcularImc() {
        System.out.println("Calcular IMC");
        lerFormulario();

        float imc = (float) (this.pessoa.getPeso() / Math.pow(this.pessoa.getAltura(), 2));
        this.pessoa.setImc(imc);
        this.lbIMC.setText(String.format("%.2f", imc));

        String classificacao = classificarIMC(imc);
        this.pessoa.setClassificacao(classificacao);
        this.labelTipoDeImc.setText(classificacao);

        // Adiciona à lista e à tabela
        listPessoas.add(this.pessoa);
        tableView.getItems().add(this.pessoa);

        // Habilita botões
        this.btnNovo.setDisable(false);
        this.btnSalvar.setDisable(false);
        this.btnCarregar.setDisable(false);
    }

    @FXML
    public void onBtnNovo() {
        this.pessoa = new Pessoa();
        this.pessoa.setId(IDGenerator.generateID());
        limparFormulario();
        System.out.println("Novo");
    }

    @FXML
    public void onBtnSalvar() {
        System.out.println("Salvar dados");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.txt", true))) {
            for (Pessoa pessoa : listPessoas) {
                String linha = String.join(",",
                        pessoa.getNome(),
                        String.valueOf(pessoa.getAltura()),
                        String.valueOf(pessoa.getPeso()),
                        String.format("%.2f", pessoa.getImc()),
                        pessoa.getClassificacao());
                writer.write(linha);
                writer.newLine();
            }
            listPessoas.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBtnCarregar() {
        System.out.println("Carregar dados");
        // Você pode implementar a leitura do arquivo aqui.
    }

    private Pessoa lerFormulario() {
        this.pessoa.setNome(this.txtNome.getText());
        this.pessoa.setAltura(Float.parseFloat(this.txtAltura.getText()));
        this.pessoa.setPeso(Float.parseFloat(this.txtPeso.getText()));
        return this.pessoa;
    }

    private void limparFormulario() {
        this.txtNome.clear();
        this.txtAltura.clear();
        this.txtPeso.clear();
        this.lbIMC.setText("");
        this.labelTipoDeImc.setText("");
    }

    private String classificarIMC(float imc) {
        if (imc < 18.5) {
            return "Abaixo do Peso";
        } else if (imc < 24.9) {
            return "Peso Normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade Grau 1";
        } else if (imc < 39.9) {
            return "Obesidade Grau 2";
        } else {
            return "Obesidade Grau 3";
        }
    }
}
