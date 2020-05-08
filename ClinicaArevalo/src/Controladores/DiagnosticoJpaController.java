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
import Entidades.Diagnostico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author franb
 */
public class DiagnosticoJpaController implements Serializable {

    public DiagnosticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diagnostico diagnostico) {
        if (diagnostico.getDetalleDiagnosticoList() == null) {
            diagnostico.setDetalleDiagnosticoList(new ArrayList<DetalleDiagnostico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consulta idConsultas = diagnostico.getIdConsultas();
            if (idConsultas != null) {
                idConsultas = em.getReference(idConsultas.getClass(), idConsultas.getIdConsulta());
                diagnostico.setIdConsultas(idConsultas);
            }
            List<DetalleDiagnostico> attachedDetalleDiagnosticoList = new ArrayList<DetalleDiagnostico>();
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnosticoToAttach : diagnostico.getDetalleDiagnosticoList()) {
                detalleDiagnosticoListDetalleDiagnosticoToAttach = em.getReference(detalleDiagnosticoListDetalleDiagnosticoToAttach.getClass(), detalleDiagnosticoListDetalleDiagnosticoToAttach.getIdDetalleDiagnostico());
                attachedDetalleDiagnosticoList.add(detalleDiagnosticoListDetalleDiagnosticoToAttach);
            }
            diagnostico.setDetalleDiagnosticoList(attachedDetalleDiagnosticoList);
            em.persist(diagnostico);
            if (idConsultas != null) {
                idConsultas.getDiagnosticoList().add(diagnostico);
                idConsultas = em.merge(idConsultas);
            }
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnostico : diagnostico.getDetalleDiagnosticoList()) {
                Diagnostico oldIdDiagnosticoOfDetalleDiagnosticoListDetalleDiagnostico = detalleDiagnosticoListDetalleDiagnostico.getIdDiagnostico();
                detalleDiagnosticoListDetalleDiagnostico.setIdDiagnostico(diagnostico);
                detalleDiagnosticoListDetalleDiagnostico = em.merge(detalleDiagnosticoListDetalleDiagnostico);
                if (oldIdDiagnosticoOfDetalleDiagnosticoListDetalleDiagnostico != null) {
                    oldIdDiagnosticoOfDetalleDiagnosticoListDetalleDiagnostico.getDetalleDiagnosticoList().remove(detalleDiagnosticoListDetalleDiagnostico);
                    oldIdDiagnosticoOfDetalleDiagnosticoListDetalleDiagnostico = em.merge(oldIdDiagnosticoOfDetalleDiagnosticoListDetalleDiagnostico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diagnostico diagnostico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnostico persistentDiagnostico = em.find(Diagnostico.class, diagnostico.getIdDiagnostico());
            Consulta idConsultasOld = persistentDiagnostico.getIdConsultas();
            Consulta idConsultasNew = diagnostico.getIdConsultas();
            List<DetalleDiagnostico> detalleDiagnosticoListOld = persistentDiagnostico.getDetalleDiagnosticoList();
            List<DetalleDiagnostico> detalleDiagnosticoListNew = diagnostico.getDetalleDiagnosticoList();
            if (idConsultasNew != null) {
                idConsultasNew = em.getReference(idConsultasNew.getClass(), idConsultasNew.getIdConsulta());
                diagnostico.setIdConsultas(idConsultasNew);
            }
            List<DetalleDiagnostico> attachedDetalleDiagnosticoListNew = new ArrayList<DetalleDiagnostico>();
            for (DetalleDiagnostico detalleDiagnosticoListNewDetalleDiagnosticoToAttach : detalleDiagnosticoListNew) {
                detalleDiagnosticoListNewDetalleDiagnosticoToAttach = em.getReference(detalleDiagnosticoListNewDetalleDiagnosticoToAttach.getClass(), detalleDiagnosticoListNewDetalleDiagnosticoToAttach.getIdDetalleDiagnostico());
                attachedDetalleDiagnosticoListNew.add(detalleDiagnosticoListNewDetalleDiagnosticoToAttach);
            }
            detalleDiagnosticoListNew = attachedDetalleDiagnosticoListNew;
            diagnostico.setDetalleDiagnosticoList(detalleDiagnosticoListNew);
            diagnostico = em.merge(diagnostico);
            if (idConsultasOld != null && !idConsultasOld.equals(idConsultasNew)) {
                idConsultasOld.getDiagnosticoList().remove(diagnostico);
                idConsultasOld = em.merge(idConsultasOld);
            }
            if (idConsultasNew != null && !idConsultasNew.equals(idConsultasOld)) {
                idConsultasNew.getDiagnosticoList().add(diagnostico);
                idConsultasNew = em.merge(idConsultasNew);
            }
            for (DetalleDiagnostico detalleDiagnosticoListOldDetalleDiagnostico : detalleDiagnosticoListOld) {
                if (!detalleDiagnosticoListNew.contains(detalleDiagnosticoListOldDetalleDiagnostico)) {
                    detalleDiagnosticoListOldDetalleDiagnostico.setIdDiagnostico(null);
                    detalleDiagnosticoListOldDetalleDiagnostico = em.merge(detalleDiagnosticoListOldDetalleDiagnostico);
                }
            }
            for (DetalleDiagnostico detalleDiagnosticoListNewDetalleDiagnostico : detalleDiagnosticoListNew) {
                if (!detalleDiagnosticoListOld.contains(detalleDiagnosticoListNewDetalleDiagnostico)) {
                    Diagnostico oldIdDiagnosticoOfDetalleDiagnosticoListNewDetalleDiagnostico = detalleDiagnosticoListNewDetalleDiagnostico.getIdDiagnostico();
                    detalleDiagnosticoListNewDetalleDiagnostico.setIdDiagnostico(diagnostico);
                    detalleDiagnosticoListNewDetalleDiagnostico = em.merge(detalleDiagnosticoListNewDetalleDiagnostico);
                    if (oldIdDiagnosticoOfDetalleDiagnosticoListNewDetalleDiagnostico != null && !oldIdDiagnosticoOfDetalleDiagnosticoListNewDetalleDiagnostico.equals(diagnostico)) {
                        oldIdDiagnosticoOfDetalleDiagnosticoListNewDetalleDiagnostico.getDetalleDiagnosticoList().remove(detalleDiagnosticoListNewDetalleDiagnostico);
                        oldIdDiagnosticoOfDetalleDiagnosticoListNewDetalleDiagnostico = em.merge(oldIdDiagnosticoOfDetalleDiagnosticoListNewDetalleDiagnostico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diagnostico.getIdDiagnostico();
                if (findDiagnostico(id) == null) {
                    throw new NonexistentEntityException("The diagnostico with id " + id + " no longer exists.");
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
            Diagnostico diagnostico;
            try {
                diagnostico = em.getReference(Diagnostico.class, id);
                diagnostico.getIdDiagnostico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diagnostico with id " + id + " no longer exists.", enfe);
            }
            Consulta idConsultas = diagnostico.getIdConsultas();
            if (idConsultas != null) {
                idConsultas.getDiagnosticoList().remove(diagnostico);
                idConsultas = em.merge(idConsultas);
            }
            List<DetalleDiagnostico> detalleDiagnosticoList = diagnostico.getDetalleDiagnosticoList();
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnostico : detalleDiagnosticoList) {
                detalleDiagnosticoListDetalleDiagnostico.setIdDiagnostico(null);
                detalleDiagnosticoListDetalleDiagnostico = em.merge(detalleDiagnosticoListDetalleDiagnostico);
            }
            em.remove(diagnostico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diagnostico> findDiagnosticoEntities() {
        return findDiagnosticoEntities(true, -1, -1);
    }

    public List<Diagnostico> findDiagnosticoEntities(int maxResults, int firstResult) {
        return findDiagnosticoEntities(false, maxResults, firstResult);
    }

    private List<Diagnostico> findDiagnosticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diagnostico.class));
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

    public Diagnostico findDiagnostico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diagnostico.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiagnosticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diagnostico> rt = cq.from(Diagnostico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Diagnostico> findbyIdDESC () {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Diagnostico> query=em.createNamedQuery("Diagnostico.findbyDESC",Diagnostico.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
