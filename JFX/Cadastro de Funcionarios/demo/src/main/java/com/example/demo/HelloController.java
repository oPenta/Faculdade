package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

public class HelloController {
    @FXML private TextField txtMatricula, txtNome, txtCpf, txtCargo, txtSalario,txtMatriculaConsulta;
    @FXML private DatePicker dpNascimento, dpContratacao;
    @FXML private TextField txtLogradouro, txtNumero, txtComplemento, txtBairro, txtCidade, txtEstado, txtCep;
    @FXML private TextArea txtResultado;

    private final List<Funcionario> funcionarios = new ArrayList<>();
    private final File arquivo = new File("funcionarios.csv");

    @FXML
    public void initialize() {
        if (arquivo.exists()) carregar();
    }

    @FXML
    protected void onCadastrarClick() {
        try {
            Funcionario f = criarFuncionario();
            validarFuncionario(f);
            if (funcionarios.stream().anyMatch(x -> x.getMatricula().equals(f.getMatricula())))
                throw new RuntimeException("Matrícula já existente.");
            funcionarios.add(f);
            salvar();
            txtResultado.setText("Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            txtResultado.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    protected void onExcluirClick() {
        try {
            String mat = txtMatriculaConsulta.getText(); // agora usa o campo de consulta
            boolean removido = funcionarios.removeIf(f -> f.getMatricula().equals(mat));
            if (!removido) throw new RuntimeException("Funcionário não encontrado.");
            salvar();
            txtResultado.setText("Funcionário excluído com sucesso!");
        } catch (Exception e) {
            txtResultado.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onConsultarClick() {
        String matricula = txtMatriculaConsulta.getText();
        Optional<Funcionario> funcionario = funcionarios.stream()
                .filter(f -> f.getMatricula().equals(matricula))
                .findFirst();

        if (funcionario.isPresent()) {
            txtResultado.setText(funcionario.get().toString());
        } else {
            txtResultado.setText("Funcionário com matrícula " + matricula + " não encontrado.");
        }
    }

    @FXML
    protected void onListarTodosClick() {
        if (funcionarios.isEmpty()) {
            txtResultado.setText("Nenhum funcionário cadastrado.");
            return;
        }
        StringBuilder sb = new StringBuilder("Matrículas:\n");
        funcionarios.forEach(f -> sb.append(f.getMatricula()).append("\n"));
        txtResultado.setText(sb.toString());
    }


    private Funcionario criarFuncionario() {
        return new Funcionario(
                txtMatricula.getText(), txtNome.getText(), txtCpf.getText(), dpNascimento.getValue(),
                txtCargo.getText(), new BigDecimal(txtSalario.getText()), dpContratacao.getValue(),
                new Endereco(
                        txtLogradouro.getText(), txtNumero.getText(), txtComplemento.getText(),
                        txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtCep.getText()
                )
        );
    }

    private void validarFuncionario(Funcionario f) {
        if (!f.getMatricula().matches("\\d{6}"))
            throw new IllegalArgumentException("Matrícula deve conter 6 dígitos.");

        if (f.getNome() == null || f.getNome().length() < 3)
            throw new IllegalArgumentException("Nome deve ter no mínimo 3 caracteres.");

        if (!validarCPF(f.getCpf()))
            throw new IllegalArgumentException("CPF inválido.");

        if (f.getDataNascimento().isAfter(LocalDate.now().minusYears(18)))
            throw new IllegalArgumentException("Deve ter 18 anos ou mais.");

        if (f.getSalario().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Salário inválido.");

        if (!f.getEndereco().getCep().matches("\\d{5}-?\\d{3}"))
            throw new IllegalArgumentException("CEP inválido.");
    }

    private boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;
        try {
            int d1 = 0, d2 = 0;
            for (int i = 0; i < 9; i++) {
                int dig = cpf.charAt(i) - '0';
                d1 += dig * (10 - i);
                d2 += dig * (11 - i);
            }
            d1 = 11 - (d1 % 11); if (d1 > 9) d1 = 0;
            d2 += d1 * 2;
            d2 = 11 - (d2 % 11); if (d2 > 9) d2 = 0;
            return d1 == cpf.charAt(9) - '0' && d2 == cpf.charAt(10) - '0';
        } catch (Exception e) {
            return false;
        }
    }

    private void salvar() {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(arquivo), StandardCharsets.UTF_8))) {
            for (Funcionario f : funcionarios) {
                Endereco e = f.getEndereco();
                String[] linha = {
                        f.getMatricula(), f.getNome(), f.getCpf(), f.getDataNascimento().toString(),
                        f.getCargo(), f.getSalario().toString(), f.getDataContratacao().toString(),
                        e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(),
                        e.getCidade(), e.getEstado(), e.getCep()
                };
                writer.println(String.join(";", linha));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar CSV.");
        }
    }

    private void carregar() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] l = Arrays.stream(linha.split(";")).map(String::trim).toArray(String[]::new);

                Funcionario f = new Funcionario(
                        l[0], l[1], l[2], LocalDate.parse(l[3]), l[4],
                        new BigDecimal(l[5]), LocalDate.parse(l[6]),
                        new Endereco(l[7], l[8], l[9], l[10], l[11], l[12], l[13])
                );

                funcionarios.add(f);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar CSV.");
        }
    }

}
