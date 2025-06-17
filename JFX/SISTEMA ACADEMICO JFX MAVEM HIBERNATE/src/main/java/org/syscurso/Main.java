package org.syscurso;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.syscurso.utils.JPAUtil;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/syscurso/view/MainView.fxml"));
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Sistema de Gestão Acadêmica");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> JPAUtil.close());
    }

    public static void main(String[] args) {
        launch();
    }
}