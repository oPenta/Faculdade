package com.crudp2.gerenciamentodelivrosp2;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class Main extends Application {
    private GerenciadorLivros gerenciadorLivros = new GerenciadorLivros();
    private TableView<Livro> tabelaLivros = new TableView<>();
    private TextField txtTitulo, txtAutor, txtAno, txtGenero;
    private Button btnAdicionar, btnAtualizar, btnExcluir;

    @SuppressWarnings({ "exports", "unused" })
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciamento de Livros");

        // Digitar
        txtTitulo = new TextField();
        txtAutor = new TextField();
        txtAno = new TextField();
        txtGenero = new TextField();

        // Botões
        btnAdicionar = new Button("Adicionar");
        btnAtualizar = new Button("Atualizar");
        btnExcluir = new Button("Excluir");

        // Estilizando os botões
        btnAdicionar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnAtualizar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnExcluir.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        configurarTabela();

        // Tela
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color: #f0f0f0;");
        
        // Adicionando título
        Label tituloLabel = new Label("Gerenciamento de Livros");
        tituloLabel.setFont(new Font("Arial", 20));
        tituloLabel.setTextFill(Color.DARKBLUE);

        layout.getChildren().addAll(tituloLabel,
                new Label("Título:"), txtTitulo,
                new Label("Autor:"), txtAutor,
                new Label("Ano de Publicação:"), txtAno,
                new Label("Gênero:"), txtGenero,
                btnAdicionar, btnAtualizar, btnExcluir, tabelaLivros);

        // funções Botões
        btnAdicionar.setOnAction(e -> adicionarLivro());
        btnAtualizar.setOnAction(e -> atualizarLivro());
        btnExcluir.setOnAction(e -> excluirLivro());

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @SuppressWarnings("unchecked")
    private void configurarTabela() {
        TableColumn<Livro, Integer> colunaId = new TableColumn<>("ID");
        colunaId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        TableColumn<Livro, String> colunaTitulo = new TableColumn<>("Título");
        colunaTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));

        TableColumn<Livro, String> colunaAutor = new TableColumn<>("Autor");
        colunaAutor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor()));

        TableColumn<Livro, Integer> colunaAno = new TableColumn<>("Ano de Publicação");
        colunaAno.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnoPublicacao()).asObject());

        TableColumn<Livro, String> colunaGenero = new TableColumn<>("Gênero");
        colunaGenero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenero()));

        tabelaLivros.getColumns().addAll(colunaId, colunaTitulo, colunaAutor, colunaAno, colunaGenero);
        tabelaLivros.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px;");
    }

    private void adicionarLivro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int ano = Integer.parseInt(txtAno.getText());
        String genero = txtGenero.getText();
        Livro novoLivro = new Livro(titulo, autor, ano, genero);
        gerenciadorLivros.adicionarLivro(novoLivro);
        atualizarTabela();
        limparCampos();
    }

    private void atualizarLivro() {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            livroSelecionado.setTitulo(txtTitulo.getText());
            livroSelecionado.setAutor(txtAutor.getText());
            livroSelecionado.setAnoPublicacao(Integer.parseInt(txtAno.getText()));
            livroSelecionado.setGenero(txtGenero.getText());
            gerenciadorLivros.atualizarLivro(livroSelecionado.getId(), livroSelecionado);
            atualizarTabela();
            limparCampos();
        }
    }

    private void excluirLivro() {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            gerenciadorLivros.excluirLivro(livroSelecionado.getId());
            atualizarTabela();
            limparCampos();
        }
    }

    private void atualizarTabela() {
        tabelaLivros.getItems().clear();
        tabelaLivros.getItems().addAll(gerenciadorLivros.listarLivros());
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtAno.clear();
        txtGenero.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}