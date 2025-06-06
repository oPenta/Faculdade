package org.sysimc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sysimc.model.Pessoa;
import org.sysimc.utils.ArquivoUtil;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private TextField txtNome;
    @FXML private TextField txtPeso;
    @FXML private TextField txtAltura;
    @FXML private Label lbIMC;
    @FXML private Label lbClassificacao;
    @FXML private TableView<Pessoa> tbPessoas;
    @FXML private TableColumn<Pessoa, Integer> colId;
    @FXML private TableColumn<Pessoa, String> colNome;
    @FXML private TableColumn<Pessoa, Double> colPeso;
    @FXML private TableColumn<Pessoa, Double> colAltura;
    @FXML private TableColumn<Pessoa, Double> colIMC;
    @FXML private TableColumn<Pessoa, String> colClassificacao; // Nova coluna
    @FXML private Button btnSalvarArquivo;

    private ObservableList<Pessoa> observableListPessoas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.observableListPessoas = FXCollections.observableArrayList();
        vincularTabelaComLista();
        formatarColunasTabela();
        // Carrega dados existentes ao iniciar a aplicação
        onClickCarregarArquivo();
    }

    private void vincularTabelaComLista() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        colIMC.setCellValueFactory(new PropertyValueFactory<>("imc"));
        colClassificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao")); // Vincula nova coluna
        tbPessoas.setItems(observableListPessoas);
    }

    private void formatarColunasTabela() {
        // Formata as colunas de double para exibir apenas 2 casas decimais
        formatarColunaDouble(colPeso, "%.2f");
        formatarColunaDouble(colAltura, "%.2f");
        formatarColunaDouble(colIMC, "%.2f");
    }

    @FXML
    void onClickAdicionar() {
        try {
            String nome = txtNome.getText();
            double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));
            double altura = Double.parseDouble(txtAltura.getText().replace(",", "."));

            if (nome.isEmpty() || peso <= 0 || altura <= 0) {
                mostrarAlerta("Erro de Validação", "Nome, peso e altura devem ser preenchidos com valores válidos.");
                return;
            }

            Pessoa novaPessoa = new Pessoa(nome, peso, altura);
            observableListPessoas.add(novaPessoa);

            exibirResultado(novaPessoa);
            limparFormulario();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Formato", "Peso e Altura devem ser números válidos.");
        }
    }

    @FXML
    void onClickSalvarArquivo() {
        try {
            ArquivoUtil.salvar(observableListPessoas);
            mostrarAlerta("Sucesso", "Dados salvos com sucesso em dados_pessoas.txt!");
        } catch (IOException e) {
            mostrarAlerta("Erro de Arquivo", "Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }

    @FXML
    void onClickCarregarArquivo() {
        List<Pessoa> pessoasCarregadas = ArquivoUtil.carregar();
        observableListPessoas.setAll(pessoasCarregadas);
    }

    private void limparFormulario() {
        txtNome.clear();
        txtPeso.clear();
        txtAltura.clear();
        txtNome.requestFocus();
    }

    private void exibirResultado(Pessoa pessoa) {
        DecimalFormat df = new DecimalFormat("#0.00");
        lbIMC.setText(df.format(pessoa.getImc()));
        lbClassificacao.setText(pessoa.getClassificacao());
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Método auxiliar para formatar colunas
    private <T> void formatarColunaDouble(TableColumn<T, Double> coluna, String formato) {
        coluna.setCellFactory(tc -> new TableCell<T, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format(formato, item));
                }
            }
        });
    }
}
