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
import Entidades.Consulta;
import Entidades.DetalleReceta;
import Entidades.Receta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author franb
 */
public class RecetaJpaController implements Serializable {

    public RecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Receta receta) {
        if (receta.getDetalleRecetaList() == null) {
            receta.setDetalleRecetaList(new ArrayList<DetalleReceta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consulta idConsulta = receta.getIdConsulta();
            if (idConsulta != null) {
                idConsulta = em.getReference(idConsulta.getClass(), idConsulta.getIdConsulta());
                receta.setIdConsulta(idConsulta);
            }
            List<DetalleReceta> attachedDetalleRecetaList = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaListDetalleRecetaToAttach : receta.getDetalleRecetaList()) {
                detalleRecetaListDetalleRecetaToAttach = em.getReference(detalleRecetaListDetalleRecetaToAttach.getClass(), detalleRecetaListDetalleRecetaToAttach.getDetalleReceta());
                attachedDetalleRecetaList.add(detalleRecetaListDetalleRecetaToAttach);
            }
            receta.setDetalleRecetaList(attachedDetalleRecetaList);
            em.persist(receta);
            if (idConsulta != null) {
                idConsulta.getRecetaList().add(receta);
                idConsulta = em.merge(idConsulta);
            }
            for (DetalleReceta detalleRecetaListDetalleReceta : receta.getDetalleRecetaList()) {
                Receta oldNumRecetaOfDetalleRecetaListDetalleReceta = detalleRecetaListDetalleReceta.getNumReceta();
                detalleRecetaListDetalleReceta.setNumReceta(receta);
                detalleRecetaListDetalleReceta = em.merge(detalleRecetaListDetalleReceta);
                if (oldNumRecetaOfDetalleRecetaListDetalleReceta != null) {
                    oldNumRecetaOfDetalleRecetaListDetalleReceta.getDetalleRecetaList().remove(detalleRecetaListDetalleReceta);
                    oldNumRecetaOfDetalleRecetaListDetalleReceta = em.merge(oldNumRecetaOfDetalleRecetaListDetalleReceta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Receta receta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta persistentReceta = em.find(Receta.class, receta.getNumReceta());
            Consulta idConsultaOld = persistentReceta.getIdConsulta();
            Consulta idConsultaNew = receta.getIdConsulta();
            List<DetalleReceta> detalleRecetaListOld = persistentReceta.getDetalleRecetaList();
            List<DetalleReceta> detalleRecetaListNew = receta.getDetalleRecetaList();
            if (idConsultaNew != null) {
                idConsultaNew = em.getReference(idConsultaNew.getClass(), idConsultaNew.getIdConsulta());
                receta.setIdConsulta(idConsultaNew);
            }
            List<DetalleReceta> attachedDetalleRecetaListNew = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaListNewDetalleRecetaToAttach : detalleRecetaListNew) {
                detalleRecetaListNewDetalleRecetaToAttach = em.getReference(detalleRecetaListNewDetalleRecetaToAttach.getClass(), detalleRecetaListNewDetalleRecetaToAttach.getDetalleReceta());
                attachedDetalleRecetaListNew.add(detalleRecetaListNewDetalleRecetaToAttach);
            }
            detalleRecetaListNew = attachedDetalleRecetaListNew;
            receta.setDetalleRecetaList(detalleRecetaListNew);
            receta = em.merge(receta);
            if (idConsultaOld != null && !idConsultaOld.equals(idConsultaNew)) {
                idConsultaOld.getRecetaList().remove(receta);
                idConsultaOld = em.merge(idConsultaOld);
            }
            if (idConsultaNew != null && !idConsultaNew.equals(idConsultaOld)) {
                idConsultaNew.getRecetaList().add(receta);
                idConsultaNew = em.merge(idConsultaNew);
            }
            for (DetalleReceta detalleRecetaListOldDetalleReceta : detalleRecetaListOld) {
                if (!detalleRecetaListNew.contains(detalleRecetaListOldDetalleReceta)) {
                    detalleRecetaListOldDetalleReceta.setNumReceta(null);
                    detalleRecetaListOldDetalleReceta = em.merge(detalleRecetaListOldDetalleReceta);
                }
            }
            for (DetalleReceta detalleRecetaListNewDetalleReceta : detalleRecetaListNew) {
                if (!detalleRecetaListOld.contains(detalleRecetaListNewDetalleReceta)) {
                    Receta oldNumRecetaOfDetalleRecetaListNewDetalleReceta = detalleRecetaListNewDetalleReceta.getNumReceta();
                    detalleRecetaListNewDetalleReceta.setNumReceta(receta);
                    detalleRecetaListNewDetalleReceta = em.merge(detalleRecetaListNewDetalleReceta);
                    if (oldNumRecetaOfDetalleRecetaListNewDetalleReceta != null && !oldNumRecetaOfDetalleRecetaListNewDetalleReceta.equals(receta)) {
                        oldNumRecetaOfDetalleRecetaListNewDetalleReceta.getDetalleRecetaList().remove(detalleRecetaListNewDetalleReceta);
                        oldNumRecetaOfDetalleRecetaListNewDetalleReceta = em.merge(oldNumRecetaOfDetalleRecetaListNewDetalleReceta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = receta.getNumReceta();
                if (findReceta(id) == null) {
                    throw new NonexistentEntityException("The receta with id " + id + " no longer exists.");
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
            Receta receta;
            try {
                receta = em.getReference(Receta.class, id);
                receta.getNumReceta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receta with id " + id + " no longer exists.", enfe);
            }
            Consulta idConsulta = receta.getIdConsulta();
            if (idConsulta != null) {
                idConsulta.getRecetaList().remove(receta);
                idConsulta = em.merge(idConsulta);
            }
            List<DetalleReceta> detalleRecetaList = receta.getDetalleRecetaList();
            for (DetalleReceta detalleRecetaListDetalleReceta : detalleRecetaList) {
                detalleRecetaListDetalleReceta.setNumReceta(null);
                detalleRecetaListDetalleReceta = em.merge(detalleRecetaListDetalleReceta);
            }
            em.remove(receta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Receta> findRecetaEntities() {
        return findRecetaEntities(true, -1, -1);
    }

    public List<Receta> findRecetaEntities(int maxResults, int firstResult) {
        return findRecetaEntities(false, maxResults, firstResult);
    }

    private List<Receta> findRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receta.class));
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

    public Receta findReceta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receta> rt = cq.from(Receta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Receta> findbyIdConsultaDESC (Consulta id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Receta> query=em.createNamedQuery("Receta.findbyIdConsultaDESC",Receta.class);
            query.setParameter("idconsulta",id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Receta> findbyIdDESC () {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Receta> query=em.createNamedQuery("Receta.findbyIdDESC",Receta.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }  
}
