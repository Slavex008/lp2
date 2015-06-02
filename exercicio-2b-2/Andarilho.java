/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cefet.controller;

import cefet.controller.exceptions.NonexistentEntityException;
import cefetmg.model.Andarilho;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author alunos
 */
public class AndarilhoJpaController implements Serializable {

    public AndarilhoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Andarilho andarilho) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(andarilho);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Andarilho andarilho) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            andarilho = em.merge(andarilho);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = andarilho.getIdAndarilho();
                if (findAndarilho(id) == null) {
                    throw new NonexistentEntityException("The andarilho with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Andarilho andarilho;
            try {
                andarilho = em.getReference(Andarilho.class, id);
                andarilho.getIdAndarilho();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The andarilho with id " + id + " no longer exists.", enfe);
            }
            em.remove(andarilho);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Andarilho> findAndarilhoEntities() {
        return findAndarilhoEntities(true, -1, -1);
    }

    public List<Andarilho> findAndarilhoEntities(int maxResults, int firstResult) {
        return findAndarilhoEntities(false, maxResults, firstResult);
    }

    private List<Andarilho> findAndarilhoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Andarilho.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Andarilho findAndarilho(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Andarilho.class, id);
        } finally {
            em.close();
        }
    }

    public int getAndarilhoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Andarilho> rt = cq.from(Andarilho.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
