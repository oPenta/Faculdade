package org.syscurso.dao;

import org.syscurso.model.Turma;
import org.syscurso.utils.JPAUtil;
import javax.persistence.EntityManager;
import java.util.List;

public class TurmaDAO {

    public void create(Turma turma) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(turma);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void update(Turma turma) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(turma);
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
            Turma turma = em.find(Turma.class, id);
            if (turma != null) {
                em.remove(turma);
            }
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Turma> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Turma t", Turma.class).getResultList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}