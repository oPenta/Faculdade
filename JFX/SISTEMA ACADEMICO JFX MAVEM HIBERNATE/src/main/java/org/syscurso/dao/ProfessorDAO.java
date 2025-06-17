package org.syscurso.dao;

import org.syscurso.model.Professor;
import org.syscurso.utils.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProfessorDAO {

    public void create(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(professor);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void update(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(professor);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Professor professor = em.find(Professor.class, id);
            if (professor != null) {
                em.remove(professor);
            }
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Professor findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Professor.class, id);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Professor> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Professor> query = em.createQuery("SELECT p FROM Professor p", Professor.class);
            return query.getResultList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}