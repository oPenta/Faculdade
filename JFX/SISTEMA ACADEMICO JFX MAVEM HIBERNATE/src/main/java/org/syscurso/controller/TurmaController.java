package org.syscurso.controller;

import org.syscurso.dao.DisciplinaDAO;
import org.syscurso.dao.ProfessorDAO;
import org.syscurso.dao.TurmaDAO;
import org.syscurso.model.Disciplina;
import org.syscurso.model.Professor;
import org.syscurso.model.Turma;
import org.syscurso.utils.AlertaUtil;
import org.syscurso.utils.Validador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TurmaController {

    @FXML private TableView<Turma> tabelaTurmas;
    @FXML private TextField tfSemestre;
    @FXML private TextField tfHorario;
    @FXML private ComboBox<Disciplina> cbDisciplina;
    @FXML private ComboBox<Professor> cbProfessor;

    private TurmaDAO turmaDAO;
    private DisciplinaDAO disciplinaDAO;
    private ProfessorDAO professorDAO;

    private ObservableList<Turma> turmas;
    private ObservableList<Disciplina> disciplinas;
    private ObservableList<Professor> professores;

    private Turma turmaSelecionada;

    @FXML
    public void initialize() {
        turmaDAO = new TurmaDAO();
        disciplinaDAO = new DisciplinaDAO();
        professorDAO = new ProfessorDAO();

        turmas = FXCollections.observableArrayList();
        tabelaTurmas.setItems(turmas);

        disciplinas = FXCollections.observableArrayList();
        cbDisciplina.setItems(disciplinas);

        professores = FXCollections.observableArrayList();
        cbProfessor.setItems(professores);

        tabelaTurmas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarTurma(newValue)
        );

        carregarDisciplinas();
        carregarProfessores();
        atualizarLista();
    }

    private void carregarDisciplinas() {
        disciplinas.setAll(disciplinaDAO.findAll());
    }

    private void carregarProfessores() {
        professores.setAll(professorDAO.findAll());
    }

    @FXML
    private void handleSalvar() {
        if (Validador.isCampoVazio(tfSemestre.getText()) || cbDisciplina.getValue() == null || cbProfessor.getValue() == null) {
            AlertaUtil.showAlert("Erro de Validação", "Campos obrigatórios", "Semestre, disciplina e professor são obrigatórios.", Alert.AlertType.ERROR);
            return;
        }

        String semestre = tfSemestre.getText();
        String horario = tfHorario.getText();
        Disciplina disciplina = cbDisciplina.getValue();
        Professor professor = cbProfessor.getValue();

        if (turmaSelecionada == null) {
            Turma nova = new Turma(null, semestre, horario, disciplina, professor);
            turmaDAO.create(nova);
            AlertaUtil.showAlert("Sucesso", "Turma Criada", "Turma criada com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            turmaSelecionada.setSemestre(semestre);
            turmaSelecionada.setHorario(horario);
            turmaSelecionada.setDisciplina(disciplina);
            turmaSelecionada.setProfessor(professor);
            turmaDAO.update(turmaSelecionada);
            AlertaUtil.showAlert("Sucesso", "Turma Atualizada", "Turma atualizada com sucesso!", Alert.AlertType.INFORMATION);
        }

        limparCampos();
        atualizarLista();
    }

    @FXML
    public void atualizarLista() {
        turmas.setAll(turmaDAO.findAll());
        tabelaTurmas.refresh();
    }

    private void selecionarTurma(Turma turma) {
        turmaSelecionada = turma;
        if (turma != null) {
            tfSemestre.setText(turma.getSemestre());
            tfHorario.setText(turma.getHorario());
            cbDisciplina.setValue(turma.getDisciplina());
            cbProfessor.setValue(turma.getProfessor());
        } else {
            limparCampos();
        }
    }

    private void limparCampos() {
        tabelaTurmas.getSelectionModel().clearSelection();
        tfSemestre.clear();
        tfHorario.clear();
        cbDisciplina.setValue(null);
        cbProfessor.setValue(null);
        turmaSelecionada = null;
    }

    @FXML private void handleNovo() { limparCampos(); }
    @FXML private void handleExcluir() {
        if (turmaSelecionada == null) {
            AlertaUtil.showAlert("Erro", "Nenhuma turma selecionada", "Por favor, selecione uma turma para excluir.", Alert.AlertType.ERROR);
            return;
        }
        turmaDAO.delete(turmaSelecionada.getId());
        AlertaUtil.showAlert("Sucesso", "Turma Excluída", "Turma excluída com sucesso!", Alert.AlertType.INFORMATION);
        limparCampos();
        atualizarLista();
    }
}