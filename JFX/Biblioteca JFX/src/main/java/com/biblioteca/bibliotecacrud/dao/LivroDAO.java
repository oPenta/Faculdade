package com.biblioteca.bibliotecacrud.dao;

import com.biblioteca.bibliotecacrud.model.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class LivroDAO {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecaPU");

    public void salvar(Livro livro) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizar(Livro livro) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(livro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void excluir(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Livro livro = em.find(Livro.class, id);
            if (livro != null) {
                em.remove(livro);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Livro> listar() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        } finally {
            em.close();
        }
    }
}
