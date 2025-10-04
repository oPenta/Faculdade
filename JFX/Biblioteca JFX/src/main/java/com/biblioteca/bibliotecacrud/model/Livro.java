package com.biblioteca.bibliotecacrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "livros")
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;

    @Column(name = "ano_publicacao")
    private int anoPublicacao;

    @Column(name = "genero")
    private String genero;


}
