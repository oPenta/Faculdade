package org.imc.calculoimc.model;

public class Pessoa {
    private  long id;
    private String nome;
    private float altura;
    private  float peso;
    private float imc;
    private String classificacao;


    public Pessoa() {

    }

    public Pessoa(long id, String nome, float altura, float peso, float imc, String classificacao) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.imc = imc;
        this.classificacao = classificacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }



    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getClassificacao() {return classificacao;}

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", imc=" + imc +
                ", classificacao='" + classificacao + '\'' +
                '}';
    }
}

