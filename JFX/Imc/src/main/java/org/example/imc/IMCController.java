package org.example.imc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class IMCController {

    // --- Referências aos componentes FXML ---
    @FXML private TextField nomeTextField;
    @FXML private TextField alturaTextField;
    @FXML private TextField pesoTextField;
    @FXML private TableView<Pessoa> pessoasTableView;
    @FXML private TableColumn<Pessoa, String> nomeColumn;
    @FXML private TableColumn<Pessoa, Number> alturaColumn;
    @FXML private TableColumn<Pessoa, Number> pesoColumn;
    @FXML private TableColumn<Pessoa, Number> imcColumn;
    @FXML private TableColumn<Pessoa, String> classificacaoColumn;
    @FXML private Label imcResultLabel;

    private final ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configuração das colunas da tabela
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        alturaColumn.setCellValueFactory(cellData -> cellData.getValue().alturaProperty());
        pesoColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty());
        imcColumn.setCellValueFactory(cellData -> cellData.getValue().imcProperty());
        classificacaoColumn.setCellValueFactory(cellData -> cellData.getValue().classificacaoProperty());

        // Vincula a lista à tabela
        pessoasTableView.setItems(pessoas);

        // Listener para a seleção na tabela
        pessoasTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        imcResultLabel.setText(String.format("%.1f", newValue.getImc()));
                    }
                }
        );
    }

    @FXML
    protected void onCalcularButtonClick() {
        try {
            String nome = nomeTextField.getText();
            double peso = Double.parseDouble(pesoTextField.getText().replace(",", "."));

            // **INÍCIO DA LÓGICA CORRIGIDA PARA ALTURA (cm vs m)**
            double alturaInput = Double.parseDouble(alturaTextField.getText().replace(",", "."));
            double alturaEmMetros;

            // Se o número for maior que 3 (improvável para metros), assume que são centímetros e converte.
            if (alturaInput > 3) {
                alturaEmMetros = alturaInput / 100.0;
            } else {
                alturaEmMetros = alturaInput; // Caso contrário, assume que já está em metros.
            }
            // **FIM DA LÓGICA CORRIGIDA**

            if (nome.isEmpty() || alturaEmMetros <= 0 || peso <= 0) {
                mostrarAlerta("Erro de Entrada", "Nome não pode ser vazio e altura/peso devem ser positivos.");
                return;
            }

            // Usa a altura já convertida para o cálculo
            Pessoa novaPessoa = new Pessoa(nome, alturaEmMetros, peso);
            pessoas.add(novaPessoa);

            imcResultLabel.setText(String.format("%.1f", novaPessoa.getImc()));

            nomeTextField.clear();
            alturaTextField.clear();
            pesoTextField.clear();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Formato", "Por favor, insira valores numéricos válidos para altura e peso.");
        }
    }

    @FXML
    protected void onSalvarButtonClick() {
        try {
            ArquivoUtil.salvarDados(pessoas);
            mostrarAlerta("Sucesso", "Dados salvos com sucesso em " + "dados_imc.csv");
        } catch (IOException e) {
            mostrarAlerta("Erro de Arquivo", "Não foi possível salvar os dados. Erro: " + e.getMessage());
        }
    }

    @FXML
    protected void onCarregarButtonClick() {
        try {
            pessoas.clear();
            pessoas.addAll(ArquivoUtil.carregarDados());
            mostrarAlerta("Sucesso", "Dados carregados com sucesso.");
        } catch (IOException e) {
            mostrarAlerta("Erro de Arquivo", "Não foi possível carregar os dados. Erro: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}