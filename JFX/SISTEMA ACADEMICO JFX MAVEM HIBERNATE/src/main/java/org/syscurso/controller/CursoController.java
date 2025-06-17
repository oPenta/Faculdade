package org.syscurso.controller;

import org.syscurso.dao.CursoDAO;
import org.syscurso.model.Curso;
import org.syscurso.utils.AlertaUtil;
import org.syscurso.utils.Validador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CursoController {

    @FXML
    private TableView<Curso> tabelaCursos;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfCargaHoraria;

    private CursoDAO cursoDAO;
    private ObservableList<Curso> cursos;
    private Curso cursoSelecionado;

    @FXML
    public void initialize() {
        cursoDAO = new CursoDAO();
        cursos = FXCollections.observableArrayList();
        tabelaCursos.setItems(cursos);

        tabelaCursos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarCurso(newValue)
        );

        atualizarLista();
    }

    @FXML
    private void handleSalvar() {
        if (!validarCampos()) return;

        String nome = tfNome.getText();
        int cargaHoraria = Integer.parseInt(tfCargaHoraria.getText());

        if (cursoSelecionado == null) {
            Curso novoCurso = new Curso(null, nome, cargaHoraria, null);
            cursoDAO.create(novoCurso);
            AlertaUtil.showAlert("Sucesso", "Curso Criado", "O curso foi criado com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            cursoSelecionado.setNome(nome);
            cursoSelecionado.setCargaHoraria(cargaHoraria);
            cursoDAO.update(cursoSelecionado);
            AlertaUtil.showAlert("Sucesso", "Curso Atualizado", "O curso foi atualizado com sucesso!", Alert.AlertType.INFORMATION);
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
        if (cursoSelecionado == null) {
            AlertaUtil.showAlert("Erro", "Nenhum curso selecionado", "Por favor, selecione um curso na tabela para excluir.", Alert.AlertType.ERROR);
            return;
        }
        cursoDAO.delete(cursoSelecionado.getId());
        AlertaUtil.showAlert("Sucesso", "Curso Excluído", "O curso foi excluído com sucesso!", Alert.AlertType.INFORMATION);

        limparCampos();
        atualizarLista();
    }

    @FXML
    public void atualizarLista() {
        cursos.setAll(cursoDAO.findAll());
        tabelaCursos.refresh();
    }

    private void selecionarCurso(Curso curso) {
        cursoSelecionado = curso;
        if (curso != null) {
            tfNome.setText(curso.getNome());
            tfCargaHoraria.setText(String.valueOf(curso.getCargaHoraria()));
        } else {
            limparCampos();
        }
    }

    private void limparCampos() {
        tabelaCursos.getSelectionModel().clearSelection();
        tfNome.clear();
        tfCargaHoraria.clear();
        cursoSelecionado = null;
    }

    private boolean validarCampos() {
        if (Validador.isCampoVazio(tfNome.getText()) || Validador.isCampoVazio(tfCargaHoraria.getText())) {
            AlertaUtil.showAlert("Erro de Validação", "Campos obrigatórios", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
            return false;
        }
        if (!Validador.isNumeroValido(tfCargaHoraria.getText())) {
            AlertaUtil.showAlert("Erro de Validação", "Formato inválido", "A carga horária deve ser um número inteiro.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
}