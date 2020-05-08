/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Consulta;
import Entidades.DetalleDiagnostico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Diagnostico;
import Entidades.EnfermedadesCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author franb
 */
public class DetalleDiagnosticoJpaController implements Serializable {

    public DetalleDiagnosticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleDiagnostico detalleDiagnostico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnostico idDiagnostico = detalleDiagnostico.getIdDiagnostico();
            if (idDiagnostico != null) {
                idDiagnostico = em.getReference(idDiagnostico.getClass(), idDiagnostico.getIdDiagnostico());
                detalleDiagnostico.setIdDiagnostico(idDiagnostico);
            }
            EnfermedadesCie10 idEnfermedad = detalleDiagnostico.getIdEnfermedad();
            if (idEnfermedad != null) {
                idEnfermedad = em.getReference(idEnfermedad.getClass(), idEnfermedad.getId());
                detalleDiagnostico.setIdEnfermedad(idEnfermedad);
            }
            em.persist(detalleDiagnostico);
            if (idDiagnostico != null) {
                idDiagnostico.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idDiagnostico = em.merge(idDiagnostico);
            }
            if (idEnfermedad != null) {
                idEnfermedad.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idEnfermedad = em.merge(idEnfermedad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleDiagnostico detalleDiagnostico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleDiagnostico persistentDetalleDiagnostico = em.find(DetalleDiagnostico.class, detalleDiagnostico.getIdDetalleDiagnostico());
            Diagnostico idDiagnosticoOld = persistentDetalleDiagnostico.getIdDiagnostico();
            Diagnostico idDiagnosticoNew = detalleDiagnostico.getIdDiagnostico();
            EnfermedadesCie10 idEnfermedadOld = persistentDetalleDiagnostico.getIdEnfermedad();
            EnfermedadesCie10 idEnfermedadNew = detalleDiagnostico.getIdEnfermedad();
            if (idDiagnosticoNew != null) {
                idDiagnosticoNew = em.getReference(idDiagnosticoNew.getClass(), idDiagnosticoNew.getIdDiagnostico());
                detalleDiagnostico.setIdDiagnostico(idDiagnosticoNew);
            }
            if (idEnfermedadNew != null) {
                idEnfermedadNew = em.getReference(idEnfermedadNew.getClass(), idEnfermedadNew.getId());
                detalleDiagnostico.setIdEnfermedad(idEnfermedadNew);
            }
            detalleDiagnostico = em.merge(detalleDiagnostico);
            if (idDiagnosticoOld != null && !idDiagnosticoOld.equals(idDiagnosticoNew)) {
                idDiagnosticoOld.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idDiagnosticoOld = em.merge(idDiagnosticoOld);
            }
            if (idDiagnosticoNew != null && !idDiagnosticoNew.equals(idDiagnosticoOld)) {
                idDiagnosticoNew.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idDiagnosticoNew = em.merge(idDiagnosticoNew);
            }
            if (idEnfermedadOld != null && !idEnfermedadOld.equals(idEnfermedadNew)) {
                idEnfermedadOld.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idEnfermedadOld = em.merge(idEnfermedadOld);
            }
            if (idEnfermedadNew != null && !idEnfermedadNew.equals(idEnfermedadOld)) {
                idEnfermedadNew.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idEnfermedadNew = em.merge(idEnfermedadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleDiagnostico.getIdDetalleDiagnostico();
                if (findDetalleDiagnostico(id) == null) {
                    throw new NonexistentEntityException("The detalleDiagnostico with id " + id + " no longer exists.");
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
            DetalleDiagnostico detalleDiagnostico;
            try {
                detalleDiagnostico = em.getReference(DetalleDiagnostico.class, id);
                detalleDiagnostico.getIdDetalleDiagnostico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleDiagnostico with id " + id + " no longer exists.", enfe);
            }
            Diagnostico idDiagnostico = detalleDiagnostico.getIdDiagnostico();
            if (idDiagnostico != null) {
                idDiagnostico.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idDiagnostico = em.merge(idDiagnostico);
            }
            EnfermedadesCie10 idEnfermedad = detalleDiagnostico.getIdEnfermedad();
            if (idEnfermedad != null) {
                idEnfermedad.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idEnfermedad = em.merge(idEnfermedad);
            }
            em.remove(detalleDiagnostico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleDiagnostico> findDetalleDiagnosticoEntities() {
        return findDetalleDiagnosticoEntities(true, -1, -1);
    }

    public List<DetalleDiagnostico> findDetalleDiagnosticoEntities(int maxResults, int firstResult) {
        return findDetalleDiagnosticoEntities(false, maxResults, firstResult);
    }

    private List<DetalleDiagnostico> findDetalleDiagnosticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleDiagnostico.class));
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

    public DetalleDiagnostico findDetalleDiagnostico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleDiagnostico.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleDiagnosticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleDiagnostico> rt = cq.from(DetalleDiagnostico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<DetalleDiagnostico> findbyDiagnostico (Diagnostico consulta) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<DetalleDiagnostico> query=em.createNamedQuery("DetalleDiagnostico.findbyIdConsulta",DetalleDiagnostico.class);
            query.setParameter("idconsulta",consulta);
            return query.getResultList();
        } finally {
            em.close();
}
    }
}
