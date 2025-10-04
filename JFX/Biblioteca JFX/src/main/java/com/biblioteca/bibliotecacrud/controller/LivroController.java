package com.biblioteca.bibliotecacrud.controller;

import com.biblioteca.bibliotecacrud.dao.LivroDAO;
import com.biblioteca.bibliotecacrud.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class LivroController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtAnoPublicacao;

    @FXML
    private TextField txtGenero;

    @FXML
    private TableView<Livro> tabelaLivros;

    @FXML
    private TableColumn<Livro, Long> colunaId;

    @FXML
    private TableColumn<Livro, String> colunaTitulo;

    @FXML
    private TableColumn<Livro, String> colunaAutor;

    @FXML
    private TableColumn<Livro, Integer> colunaAnoPublicacao;

    @FXML
    private TableColumn<Livro, String> colunaGenero;

    private final LivroDAO livroDAO = new LivroDAO();

    private final ObservableList<Livro> listaLivros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colunaAnoPublicacao.setCellValueFactory(new PropertyValueFactory<>("anoPublicacao"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        carregarLivros();
    }

    @FXML
    private void cadastrarLivro(ActionEvent event) {
        try {
            Livro livro = new Livro();
            livro.setTitulo(txtTitulo.getText());
            livro.setAutor(txtAutor.getText());
            livro.setAnoPublicacao(Integer.parseInt(txtAnoPublicacao.getText()));
            livro.setGenero(txtGenero.getText());

            livroDAO.salvar(livro);
            carregarLivros();
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Ano de publicação deve ser um número válido!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void excluirLivro(ActionEvent event) {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            livroDAO.excluir(livroSelecionado.getId());
            carregarLivros();
        } else {
            mostrarAlerta("Atenção", "Selecione um livro para excluir.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void atualizarLivro(ActionEvent event) {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            try {
                livroSelecionado.setTitulo(txtTitulo.getText());
                livroSelecionado.setAutor(txtAutor.getText());
                livroSelecionado.setAnoPublicacao(Integer.parseInt(txtAnoPublicacao.getText()));
                livroSelecionado.setGenero(txtGenero.getText());

                livroDAO.atualizar(livroSelecionado);
                carregarLivros();
                limparCampos();

            } catch (NumberFormatException e) {
                mostrarAlerta("Erro", "Ano de publicação deve ser um número válido!", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Atenção", "Selecione um livro para atualizar.", Alert.AlertType.WARNING);
        }
    }

    private void carregarLivros() {
        listaLivros.clear();
        listaLivros.addAll(livroDAO.listar());
        tabelaLivros.setItems(listaLivros);
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtAnoPublicacao.clear();
        txtGenero.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}

