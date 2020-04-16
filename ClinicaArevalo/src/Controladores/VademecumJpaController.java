/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.DetalleReceta;
import Entidades.Vademecum;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author babef
 */
public class VademecumJpaController implements Serializable {

    public VademecumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vademecum vademecum) {
        if (vademecum.getDetalleRecetaList() == null) {
            vademecum.setDetalleRecetaList(new ArrayList<DetalleReceta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleReceta> attachedDetalleRecetaList = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaListDetalleRecetaToAttach : vademecum.getDetalleRecetaList()) {
                detalleRecetaListDetalleRecetaToAttach = em.getReference(detalleRecetaListDetalleRecetaToAttach.getClass(), detalleRecetaListDetalleRecetaToAttach.getDetalleReceta());
                attachedDetalleRecetaList.add(detalleRecetaListDetalleRecetaToAttach);
            }
            vademecum.setDetalleRecetaList(attachedDetalleRecetaList);
            em.persist(vademecum);
            for (DetalleReceta detalleRecetaListDetalleReceta : vademecum.getDetalleRecetaList()) {
                Vademecum oldIdInsumoOfDetalleRecetaListDetalleReceta = detalleRecetaListDetalleReceta.getIdInsumo();
                detalleRecetaListDetalleReceta.setIdInsumo(vademecum);
                detalleRecetaListDetalleReceta = em.merge(detalleRecetaListDetalleReceta);
                if (oldIdInsumoOfDetalleRecetaListDetalleReceta != null) {
                    oldIdInsumoOfDetalleRecetaListDetalleReceta.getDetalleRecetaList().remove(detalleRecetaListDetalleReceta);
                    oldIdInsumoOfDetalleRecetaListDetalleReceta = em.merge(oldIdInsumoOfDetalleRecetaListDetalleReceta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vademecum vademecum) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vademecum persistentVademecum = em.find(Vademecum.class, vademecum.getIdInsumo());
            List<DetalleReceta> detalleRecetaListOld = persistentVademecum.getDetalleRecetaList();
            List<DetalleReceta> detalleRecetaListNew = vademecum.getDetalleRecetaList();
            List<DetalleReceta> attachedDetalleRecetaListNew = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaListNewDetalleRecetaToAttach : detalleRecetaListNew) {
                detalleRecetaListNewDetalleRecetaToAttach = em.getReference(detalleRecetaListNewDetalleRecetaToAttach.getClass(), detalleRecetaListNewDetalleRecetaToAttach.getDetalleReceta());
                attachedDetalleRecetaListNew.add(detalleRecetaListNewDetalleRecetaToAttach);
            }
            detalleRecetaListNew = attachedDetalleRecetaListNew;
            vademecum.setDetalleRecetaList(detalleRecetaListNew);
            vademecum = em.merge(vademecum);
            for (DetalleReceta detalleRecetaListOldDetalleReceta : detalleRecetaListOld) {
                if (!detalleRecetaListNew.contains(detalleRecetaListOldDetalleReceta)) {
                    detalleRecetaListOldDetalleReceta.setIdInsumo(null);
                    detalleRecetaListOldDetalleReceta = em.merge(detalleRecetaListOldDetalleReceta);
                }
            }
            for (DetalleReceta detalleRecetaListNewDetalleReceta : detalleRecetaListNew) {
                if (!detalleRecetaListOld.contains(detalleRecetaListNewDetalleReceta)) {
                    Vademecum oldIdInsumoOfDetalleRecetaListNewDetalleReceta = detalleRecetaListNewDetalleReceta.getIdInsumo();
                    detalleRecetaListNewDetalleReceta.setIdInsumo(vademecum);
                    detalleRecetaListNewDetalleReceta = em.merge(detalleRecetaListNewDetalleReceta);
                    if (oldIdInsumoOfDetalleRecetaListNewDetalleReceta != null && !oldIdInsumoOfDetalleRecetaListNewDetalleReceta.equals(vademecum)) {
                        oldIdInsumoOfDetalleRecetaListNewDetalleReceta.getDetalleRecetaList().remove(detalleRecetaListNewDetalleReceta);
                        oldIdInsumoOfDetalleRecetaListNewDetalleReceta = em.merge(oldIdInsumoOfDetalleRecetaListNewDetalleReceta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vademecum.getIdInsumo();
                if (findVademecum(id) == null) {
                    throw new NonexistentEntityException("The vademecum with id " + id + " no longer exists.");
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
            Vademecum vademecum;
            try {
                vademecum = em.getReference(Vademecum.class, id);
                vademecum.getIdInsumo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vademecum with id " + id + " no longer exists.", enfe);
            }
            List<DetalleReceta> detalleRecetaList = vademecum.getDetalleRecetaList();
            for (DetalleReceta detalleRecetaListDetalleReceta : detalleRecetaList) {
                detalleRecetaListDetalleReceta.setIdInsumo(null);
                detalleRecetaListDetalleReceta = em.merge(detalleRecetaListDetalleReceta);
            }
            em.remove(vademecum);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vademecum> findVademecumEntities() {
        return findVademecumEntities(true, -1, -1);
    }

    public List<Vademecum> findVademecumEntities(int maxResults, int firstResult) {
        return findVademecumEntities(false, maxResults, firstResult);
    }

    private List<Vademecum> findVademecumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vademecum.class));
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

    public Vademecum findVademecum(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vademecum.class, id);
        } finally {
            em.close();
        }
    }

    public int getVademecumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vademecum> rt = cq.from(Vademecum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
