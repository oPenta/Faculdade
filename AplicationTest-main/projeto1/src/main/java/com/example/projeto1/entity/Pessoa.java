package com.example.projeto1.entity;


public class Pessoa extends SerVivo {

    private long id;
    private String nome;


    public Pessoa(long id, String nome , String Sangue) {
        super(Sangue);
        this.id = id;
        this.nome = nome;

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
}
