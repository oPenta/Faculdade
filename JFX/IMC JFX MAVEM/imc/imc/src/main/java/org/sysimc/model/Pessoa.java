package org.sysimc.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Locale;

public class Pessoa {

    private static int contadorId = 0;

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;
    private final SimpleDoubleProperty peso;
    private final SimpleDoubleProperty altura;
    private final SimpleDoubleProperty imc;
    private final SimpleStringProperty classificacao;

    public Pessoa(String nome, double peso, double altura) {
        this.id = new SimpleIntegerProperty(++contadorId);
        this.nome = new SimpleStringProperty(nome);
        this.peso = new SimpleDoubleProperty(peso);
        this.altura = new SimpleDoubleProperty(altura);
        this.imc = new SimpleDoubleProperty();
        this.classificacao = new SimpleStringProperty();

        calcularValores();
    }

    private void calcularValores() {
        if (getAltura() > 0) {
            double imcValue = getPeso() / (getAltura() * getAltura());
            this.imc.set(imcValue);
            this.classificacao.set(definirClassificacao(imcValue));
        } else {
            this.imc.set(0);
            this.classificacao.set("Dados inválidos");
        }
    }

    private String definirClassificacao(double imcValue) {
        if (imcValue < 18.5) return "Abaixo do Peso";
        if (imcValue <= 24.9) return "Peso Normal";
        if (imcValue <= 29.9) return "Sobrepeso";
        if (imcValue <= 34.9) return "Obesidade Grau 1";
        if (imcValue <= 39.9) return "Obesidade Grau 2";
        return "Obesidade Grau 3";
    }

    public int getId() { return id.get(); }
    public String getNome() { return nome.get(); }
    public double getPeso() { return peso.get(); }
    public double getAltura() { return altura.get(); }
    public double getImc() { return imc.get(); }
    public String getClassificacao() { return classificacao.get(); }

    public SimpleIntegerProperty idProperty() { return id; }
    public SimpleStringProperty nomeProperty() { return nome; }
    public SimpleDoubleProperty pesoProperty() { return peso; }
    public SimpleDoubleProperty alturaProperty() { return altura; }
    public SimpleDoubleProperty imcProperty() { return imc; }
    public SimpleStringProperty classificacaoProperty() { return classificacao; }

    public String paraFormatoCsv() {
        return String.format(Locale.US, "%s,%.2f,%.2f", getNome(), getPeso(), getAltura());
    }
}