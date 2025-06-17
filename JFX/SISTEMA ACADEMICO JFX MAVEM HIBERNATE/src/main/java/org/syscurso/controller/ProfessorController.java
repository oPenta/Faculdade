package org.syscurso.controller;

import org.syscurso.dao.ProfessorDAO;
import org.syscurso.model.Professor;
import org.syscurso.utils.AlertaUtil;
import org.syscurso.utils.Validador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProfessorController {

    @FXML private TableView<Professor> tabelaProfessores;
    @FXML private TextField tfNome;
    @FXML private TextField tfEmail;
    @FXML private TextField tfFormacao;

    private ProfessorDAO professorDAO;
    private ObservableList<Professor> professores;
    private Professor professorSelecionado;

    @FXML
    public void initialize() {
        professorDAO = new ProfessorDAO();
        professores = FXCollections.observableArrayList();
        tabelaProfessores.setItems(professores);

        tabelaProfessores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarProfessor(newValue)
        );

        atualizarLista();
    }

    @FXML
    private void handleSalvar() {
        if (Validador.isCampoVazio(tfNome.getText()) || Validador.isCampoVazio(tfEmail.getText()) || Validador.isCampoVazio(tfFormacao.getText())) {
            AlertaUtil.showAlert("Erro de Validação", "Campos obrigatórios", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
            return;
        }

        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String formacao = tfFormacao.getText();

        if (professorSelecionado == null) {
            Professor novoProfessor = new Professor(null, nome, email, formacao, null);
            professorDAO.create(novoProfessor);
            AlertaUtil.showAlert("Sucesso", "Professor Criado", "Professor criado com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            professorSelecionado.setNome(nome);
            professorSelecionado.setEmail(email);
            professorSelecionado.setFormacao(formacao);
            professorDAO.update(professorSelecionado);
            AlertaUtil.showAlert("Sucesso", "Professor Atualizado", "Professor atualizado com sucesso!", Alert.AlertType.INFORMATION);
        }

        limparCampos();
        atualizarLista();
    }

    @FXML
    private void handleNovo() {
        limparCampos();
    }

    @FXML
    private void handleExcluir() {
        if (professorSelecionado == null) {
            AlertaUtil.showAlert("Erro", "Nenhum professor selecionado", "Por favor, selecione um professor para excluir.", Alert.AlertType.ERROR);
            return;
        }
        professorDAO.delete(professorSelecionado.getId());
        AlertaUtil.showAlert("Sucesso", "Professor Excluído", "Professor excluído com sucesso!", Alert.AlertType.INFORMATION);

        limparCampos();
        atualizarLista();
    }

    @FXML
    public void atualizarLista() {
        professores.setAll(professorDAO.findAll());
        tabelaProfessores.refresh();
    }

    private void selecionarProfessor(Professor professor) {
        professorSelecionado = professor;
        if (professor != null) {
            tfNome.setText(professor.getNome());
            tfEmail.setText(professor.getEmail());
            tfFormacao.setText(professor.getFormacao());
        } else {
            limparCampos();
        }
    }

    private void limparCampos() {
        tabelaProfessores.getSelectionModel().clearSelection();
        tfNome.clear();
        tfEmail.clear();
        tfFormacao.clear();
        professorSelecionado = null;
    }
}