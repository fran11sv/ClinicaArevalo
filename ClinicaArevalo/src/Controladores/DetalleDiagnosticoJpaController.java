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
import Entidades.DetalleDiagnostico;
import Entidades.EnfermedadesCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
            Consulta idConsulta = detalleDiagnostico.getIdConsulta();
            if (idConsulta != null) {
                idConsulta = em.getReference(idConsulta.getClass(), idConsulta.getIdConsulta());
                detalleDiagnostico.setIdConsulta(idConsulta);
            }
            EnfermedadesCie10 idEnfermedad = detalleDiagnostico.getIdEnfermedad();
            if (idEnfermedad != null) {
                idEnfermedad = em.getReference(idEnfermedad.getClass(), idEnfermedad.getId());
                detalleDiagnostico.setIdEnfermedad(idEnfermedad);
            }
            em.persist(detalleDiagnostico);
            if (idConsulta != null) {
                idConsulta.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idConsulta = em.merge(idConsulta);
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
            DetalleDiagnostico persistentDetalleDiagnostico = em.find(DetalleDiagnostico.class, detalleDiagnostico.getIdDiagnostico());
            Consulta idConsultaOld = persistentDetalleDiagnostico.getIdConsulta();
            Consulta idConsultaNew = detalleDiagnostico.getIdConsulta();
            EnfermedadesCie10 idEnfermedadOld = persistentDetalleDiagnostico.getIdEnfermedad();
            EnfermedadesCie10 idEnfermedadNew = detalleDiagnostico.getIdEnfermedad();
            if (idConsultaNew != null) {
                idConsultaNew = em.getReference(idConsultaNew.getClass(), idConsultaNew.getIdConsulta());
                detalleDiagnostico.setIdConsulta(idConsultaNew);
            }
            if (idEnfermedadNew != null) {
                idEnfermedadNew = em.getReference(idEnfermedadNew.getClass(), idEnfermedadNew.getId());
                detalleDiagnostico.setIdEnfermedad(idEnfermedadNew);
            }
            detalleDiagnostico = em.merge(detalleDiagnostico);
            if (idConsultaOld != null && !idConsultaOld.equals(idConsultaNew)) {
                idConsultaOld.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idConsultaOld = em.merge(idConsultaOld);
            }
            if (idConsultaNew != null && !idConsultaNew.equals(idConsultaOld)) {
                idConsultaNew.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idConsultaNew = em.merge(idConsultaNew);
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
                Integer id = detalleDiagnostico.getIdDiagnostico();
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
                detalleDiagnostico.getIdDiagnostico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleDiagnostico with id " + id + " no longer exists.", enfe);
            }
            Consulta idConsulta = detalleDiagnostico.getIdConsulta();
            if (idConsulta != null) {
                idConsulta.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idConsulta = em.merge(idConsulta);
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
    
}
