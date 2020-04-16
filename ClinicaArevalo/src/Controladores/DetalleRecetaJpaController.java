/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.DetalleReceta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Vademecum;
import Entidades.Receta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author babef
 */
public class DetalleRecetaJpaController implements Serializable {

    public DetalleRecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleReceta detalleReceta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vademecum idInsumo = detalleReceta.getIdInsumo();
            if (idInsumo != null) {
                idInsumo = em.getReference(idInsumo.getClass(), idInsumo.getIdInsumo());
                detalleReceta.setIdInsumo(idInsumo);
            }
            Receta numReceta = detalleReceta.getNumReceta();
            if (numReceta != null) {
                numReceta = em.getReference(numReceta.getClass(), numReceta.getNumReceta());
                detalleReceta.setNumReceta(numReceta);
            }
            em.persist(detalleReceta);
            if (idInsumo != null) {
                idInsumo.getDetalleRecetaList().add(detalleReceta);
                idInsumo = em.merge(idInsumo);
            }
            if (numReceta != null) {
                numReceta.getDetalleRecetaList().add(detalleReceta);
                numReceta = em.merge(numReceta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleReceta detalleReceta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleReceta persistentDetalleReceta = em.find(DetalleReceta.class, detalleReceta.getDetalleReceta());
            Vademecum idInsumoOld = persistentDetalleReceta.getIdInsumo();
            Vademecum idInsumoNew = detalleReceta.getIdInsumo();
            Receta numRecetaOld = persistentDetalleReceta.getNumReceta();
            Receta numRecetaNew = detalleReceta.getNumReceta();
            if (idInsumoNew != null) {
                idInsumoNew = em.getReference(idInsumoNew.getClass(), idInsumoNew.getIdInsumo());
                detalleReceta.setIdInsumo(idInsumoNew);
            }
            if (numRecetaNew != null) {
                numRecetaNew = em.getReference(numRecetaNew.getClass(), numRecetaNew.getNumReceta());
                detalleReceta.setNumReceta(numRecetaNew);
            }
            detalleReceta = em.merge(detalleReceta);
            if (idInsumoOld != null && !idInsumoOld.equals(idInsumoNew)) {
                idInsumoOld.getDetalleRecetaList().remove(detalleReceta);
                idInsumoOld = em.merge(idInsumoOld);
            }
            if (idInsumoNew != null && !idInsumoNew.equals(idInsumoOld)) {
                idInsumoNew.getDetalleRecetaList().add(detalleReceta);
                idInsumoNew = em.merge(idInsumoNew);
            }
            if (numRecetaOld != null && !numRecetaOld.equals(numRecetaNew)) {
                numRecetaOld.getDetalleRecetaList().remove(detalleReceta);
                numRecetaOld = em.merge(numRecetaOld);
            }
            if (numRecetaNew != null && !numRecetaNew.equals(numRecetaOld)) {
                numRecetaNew.getDetalleRecetaList().add(detalleReceta);
                numRecetaNew = em.merge(numRecetaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleReceta.getDetalleReceta();
                if (findDetalleReceta(id) == null) {
                    throw new NonexistentEntityException("The detalleReceta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleReceta detalleReceta;
            try {
                detalleReceta = em.getReference(DetalleReceta.class, id);
                detalleReceta.getDetalleReceta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleReceta with id " + id + " no longer exists.", enfe);
            }
            Vademecum idInsumo = detalleReceta.getIdInsumo();
            if (idInsumo != null) {
                idInsumo.getDetalleRecetaList().remove(detalleReceta);
                idInsumo = em.merge(idInsumo);
            }
            Receta numReceta = detalleReceta.getNumReceta();
            if (numReceta != null) {
                numReceta.getDetalleRecetaList().remove(detalleReceta);
                numReceta = em.merge(numReceta);
            }
            em.remove(detalleReceta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleReceta> findDetalleRecetaEntities() {
        return findDetalleRecetaEntities(true, -1, -1);
    }

    public List<DetalleReceta> findDetalleRecetaEntities(int maxResults, int firstResult) {
        return findDetalleRecetaEntities(false, maxResults, firstResult);
    }

    private List<DetalleReceta> findDetalleRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleReceta.class));
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

    public DetalleReceta findDetalleReceta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleReceta.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleReceta> rt = cq.from(DetalleReceta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
