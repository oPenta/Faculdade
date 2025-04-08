package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario {
    private String matricula, nome, cpf, cargo;
    private LocalDate dataNascimento, dataContratacao;
    private BigDecimal salario;
    private Endereco endereco;

    public Funcionario(String matricula, String nome, String cpf, LocalDate dataNascimento, String cargo,
                       BigDecimal salario, LocalDate dataContratacao, Endereco endereco) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.endereco = endereco;
    }

    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getCargo() { return cargo; }
    public BigDecimal getSalario() { return salario; }
    public LocalDate getDataContratacao() { return dataContratacao; }
    public Endereco getEndereco() { return endereco; }

    @Override
    public String toString() {
        return "Matrícula: " + matricula +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nNascimento: " + dataNascimento +
                "\nCargo: " + cargo +
                "\nSalário: R$" + salario +
                "\nContratação: " + dataContratacao +
                "\nEndereço: " + endereco;
    }
}
