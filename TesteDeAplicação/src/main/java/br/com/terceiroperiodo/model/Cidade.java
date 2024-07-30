package br.com.terceiroperiodo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    Estado estado;

}
