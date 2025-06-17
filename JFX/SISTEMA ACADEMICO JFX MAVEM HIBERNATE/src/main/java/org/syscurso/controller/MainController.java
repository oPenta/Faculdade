package org.syscurso.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    public void initialize() {
        mostrarDashboard();
    }

    @FXML
    public void mostrarDashboard() {
        String titleStyle = "-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;";
        String buttonStyle = "-fx-pref-width: 280px; -fx-pref-height: 55px; -fx-font-size: 16px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 4);";

        Label title = new Label("Sistema de Gestão Acadêmica");
        title.setStyle(titleStyle);

        Button btnCursos = new Button("Gerenciar Cursos");
        btnCursos.setStyle(buttonStyle);
        btnCursos.setOnAction(event -> abrirTelaCursos());

        Button btnProfessores = new Button("Gerenciar Professores");
        btnProfessores.setStyle(buttonStyle);
        btnProfessores.setOnAction(event -> abrirTelaProfessores());

        Button btnDisciplinas = new Button("Gerenciar Disciplinas");
        btnDisciplinas.setStyle(buttonStyle);
        btnDisciplinas.setOnAction(event -> abrirTelaDisciplinas());

        Button btnTurmas = new Button("Gerenciar Turmas");
        btnTurmas.setStyle(buttonStyle);
        btnTurmas.setOnAction(event -> abrirTelaTurmas());

        VBox dashboardLayout = new VBox(title, btnCursos, btnProfessores, btnDisciplinas, btnTurmas);
        dashboardLayout.setStyle("-fx-alignment: center; -fx-spacing: 20px; -fx-background-color: #ecf0f1;");
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        contentPane.getChildren().clear();
        contentPane.getChildren().add(dashboardLayout);
    }

    @FXML
    public void abrirTelaCursos() {
        carregarTela("/org/syscurso/view/CursoView.fxml");
    }

    @FXML
    public void abrirTelaProfessores() {
        carregarTela("/org/syscurso/view/ProfessorView.fxml");
    }

    @FXML
    public void abrirTelaDisciplinas() {
        carregarTela("/org/syscurso/view/DisciplinaView.fxml");
    }

    @FXML
    public void abrirTelaTurmas() {
        carregarTela("/org/syscurso/view/TurmaView.fxml");
    }

    private void carregarTela(String fxmlPath) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}