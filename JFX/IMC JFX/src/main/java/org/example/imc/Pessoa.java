package org.example.imc;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pessoa {

    private final SimpleStringProperty nome;
    private final SimpleDoubleProperty altura;
    private final SimpleDoubleProperty peso;
    private final SimpleDoubleProperty imc;
    private final SimpleStringProperty classificacao;

    public Pessoa(String nome, double altura, double peso) {
        this.nome = new SimpleStringProperty(nome);
        this.altura = new SimpleDoubleProperty(altura);
        this.peso = new SimpleDoubleProperty(peso);
        this.imc = new SimpleDoubleProperty();
        this.classificacao = new SimpleStringProperty();
        calcularIMC();
    }

    private void calcularIMC() {
        double alturaVal = this.altura.get();
        double pesoVal = this.peso.get();

        if (alturaVal > 0) {
            double imcVal = pesoVal / (alturaVal * alturaVal);
            this.imc.set(Math.round(imcVal * 100.0) / 100.0); // Arredonda para 2 casas

            if (imcVal < 18.5) {
                this.classificacao.set("Abaixo do Peso");
            } else if (imcVal < 24.9) {
                this.classificacao.set("Peso Normal");
            } else if (imcVal < 29.9) {
                this.classificacao.set("Sobrepeso");
            } else if (imcVal < 34.9) {
                this.classificacao.set("Obesidade Grau I");
            } else if (imcVal < 39.9) {
                this.classificacao.set("Obesidade Grau II");
            } else {
                this.classificacao.set("Obesidade Grau III");
            }
        }
    }

// --- GETTERS E SETTERS ---

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public double getAltura() {
        return altura.get();
    }

    public void setAltura(double altura) {
        this.altura.set(altura);
    }

    public SimpleDoubleProperty alturaProperty() {
        return altura;
    }

    public double getPeso() {
        return peso.get();
    }

    public void setPeso(double peso) {
        this.peso.set(peso);
    }

    public SimpleDoubleProperty pesoProperty() {
        return peso;
    }

    public double getImc() {
        return imc.get();
    }

    public SimpleDoubleProperty imcProperty() {
        return imc;
    }

    public String getClassificacao() {
        return classificacao.get();
    }

    public SimpleStringProperty classificacaoProperty() {
        return classificacao;
    }
}