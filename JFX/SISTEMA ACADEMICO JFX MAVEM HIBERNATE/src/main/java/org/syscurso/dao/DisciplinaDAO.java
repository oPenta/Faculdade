package org.syscurso.dao;

import org.syscurso.model.Disciplina;
import org.syscurso.utils.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;

public class DisciplinaDAO {

    public void create(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(disciplina);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void update(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(disciplina);
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
            Disciplina disciplina = em.find(Disciplina.class, id);
            if (disciplina != null) {
                em.remove(disciplina);
            }
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Disciplina> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Disciplina d", Disciplina.class).getResultList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}