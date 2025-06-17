package org.syscurso.controller;

import org.syscurso.dao.CursoDAO;
import org.syscurso.dao.DisciplinaDAO;
import org.syscurso.model.Curso;
import org.syscurso.model.Disciplina;
import org.syscurso.utils.AlertaUtil;
import org.syscurso.utils.Validador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DisciplinaController {

    @FXML private TableView<Disciplina> tabelaDisciplinas;
    @FXML private TextField tfNome;
    @FXML private TextField tfDescricao;
    @FXML private ComboBox<Curso> cbCurso;

    private DisciplinaDAO disciplinaDAO;
    private CursoDAO cursoDAO;
    private ObservableList<Disciplina> disciplinas;
    private ObservableList<Curso> cursos;
    private Disciplina disciplinaSelecionada;

    @FXML
    public void initialize() {
        disciplinaDAO = new DisciplinaDAO();
        cursoDAO = new CursoDAO();

        disciplinas = FXCollections.observableArrayList();
        tabelaDisciplinas.setItems(disciplinas);

        cursos = FXCollections.observableArrayList();
        cbCurso.setItems(cursos);

        tabelaDisciplinas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarDisciplina(newValue)
        );

        carregarCursos();
        atualizarLista();
    }

    private void carregarCursos() {
        cursos.setAll(cursoDAO.findAll());
    }

    @FXML
    private void handleSalvar() {
        if (Validador.isCampoVazio(tfNome.getText()) || cbCurso.getValue() == null) {
            AlertaUtil.showAlert("Erro de Validação", "Campos obrigatórios", "O nome e o curso são obrigatórios.", Alert.AlertType.ERROR);
            return;
        }

        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        Curso curso = cbCurso.getValue();

        if (disciplinaSelecionada == null) {
            Disciplina nova = new Disciplina(null, nome, descricao, curso, null);
            disciplinaDAO.create(nova);
            AlertaUtil.showAlert("Sucesso", "Disciplina Criada", "Disciplina criada com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            disciplinaSelecionada.setNome(nome);
            disciplinaSelecionada.setDescricao(descricao);
            disciplinaSelecionada.setCurso(curso);
            disciplinaDAO.update(disciplinaSelecionada);
            AlertaUtil.showAlert("Sucesso", "Disciplina Atualizada", "Disciplina atualizada com sucesso!", Alert.AlertType.INFORMATION);
        }

        limparCampos();
        atualizarLista();
    }

    @FXML
    public void atualizarLista() {
        disciplinas.setAll(disciplinaDAO.findAll());
        tabelaDisciplinas.refresh();
    }

    private void selecionarDisciplina(Disciplina disciplina) {
        disciplinaSelecionada = disciplina;
        if (disciplina != null) {
            tfNome.setText(disciplina.getNome());
            tfDescricao.setText(disciplina.getDescricao());
            cbCurso.setValue(disciplina.getCurso());
        } else {
            limparCampos();
        }
    }

    private void limparCampos() {
        tabelaDisciplinas.getSelectionModel().clearSelection();
        tfNome.clear();
        tfDescricao.clear();
        cbCurso.setValue(null);
        disciplinaSelecionada = null;
    }

    @FXML private void handleNovo() { limparCampos(); }
    @FXML private void handleExcluir() {
        if (disciplinaSelecionada == null) {
            AlertaUtil.showAlert("Erro", "Nenhuma disciplina selecionada", "Por favor, selecione uma disciplina para excluir.", Alert.AlertType.ERROR);
            return;
        }
        disciplinaDAO.delete(disciplinaSelecionada.getId());
        AlertaUtil.showAlert("Sucesso", "Disciplina Excluída", "Disciplina excluída com sucesso!", Alert.AlertType.INFORMATION);
        limparCampos();
        atualizarLista();
    }
}