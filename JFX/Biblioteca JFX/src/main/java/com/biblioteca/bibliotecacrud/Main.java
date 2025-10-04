package com.biblioteca.bibliotecacrud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/biblioteca/bibliotecacrud/biblioteca-view.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Biblioteca - Gerenciamento de Livros");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}