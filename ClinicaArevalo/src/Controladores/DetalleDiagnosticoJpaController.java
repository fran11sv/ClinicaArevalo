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
import Entidades.EnfermedadesCie10;
import Entidades.Consulta;
import Entidades.DetalleDiagnostico;
import java.util.ArrayList;
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
        if (detalleDiagnostico.getConsultaList() == null) {
            detalleDiagnostico.setConsultaList(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfermedadesCie10 idEnfermedad = detalleDiagnostico.getIdEnfermedad();
            if (idEnfermedad != null) {
                idEnfermedad = em.getReference(idEnfermedad.getClass(), idEnfermedad.getId());
                detalleDiagnostico.setIdEnfermedad(idEnfermedad);
            }
            List<Consulta> attachedConsultaList = new ArrayList<Consulta>();
            for (Consulta consultaListConsultaToAttach : detalleDiagnostico.getConsultaList()) {
                consultaListConsultaToAttach = em.getReference(consultaListConsultaToAttach.getClass(), consultaListConsultaToAttach.getIdConsulta());
                attachedConsultaList.add(consultaListConsultaToAttach);
            }
            detalleDiagnostico.setConsultaList(attachedConsultaList);
            em.persist(detalleDiagnostico);
            if (idEnfermedad != null) {
                idEnfermedad.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idEnfermedad = em.merge(idEnfermedad);
            }
            for (Consulta consultaListConsulta : detalleDiagnostico.getConsultaList()) {
                DetalleDiagnostico oldIdDiagnosticoOfConsultaListConsulta = consultaListConsulta.getIdDiagnostico();
                consultaListConsulta.setIdDiagnostico(detalleDiagnostico);
                consultaListConsulta = em.merge(consultaListConsulta);
                if (oldIdDiagnosticoOfConsultaListConsulta != null) {
                    oldIdDiagnosticoOfConsultaListConsulta.getConsultaList().remove(consultaListConsulta);
                    oldIdDiagnosticoOfConsultaListConsulta = em.merge(oldIdDiagnosticoOfConsultaListConsulta);
                }
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
            EnfermedadesCie10 idEnfermedadOld = persistentDetalleDiagnostico.getIdEnfermedad();
            EnfermedadesCie10 idEnfermedadNew = detalleDiagnostico.getIdEnfermedad();
            List<Consulta> consultaListOld = persistentDetalleDiagnostico.getConsultaList();
            List<Consulta> consultaListNew = detalleDiagnostico.getConsultaList();
            if (idEnfermedadNew != null) {
                idEnfermedadNew = em.getReference(idEnfermedadNew.getClass(), idEnfermedadNew.getId());
                detalleDiagnostico.setIdEnfermedad(idEnfermedadNew);
            }
            List<Consulta> attachedConsultaListNew = new ArrayList<Consulta>();
            for (Consulta consultaListNewConsultaToAttach : consultaListNew) {
                consultaListNewConsultaToAttach = em.getReference(consultaListNewConsultaToAttach.getClass(), consultaListNewConsultaToAttach.getIdConsulta());
                attachedConsultaListNew.add(consultaListNewConsultaToAttach);
            }
            consultaListNew = attachedConsultaListNew;
            detalleDiagnostico.setConsultaList(consultaListNew);
            detalleDiagnostico = em.merge(detalleDiagnostico);
            if (idEnfermedadOld != null && !idEnfermedadOld.equals(idEnfermedadNew)) {
                idEnfermedadOld.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idEnfermedadOld = em.merge(idEnfermedadOld);
            }
            if (idEnfermedadNew != null && !idEnfermedadNew.equals(idEnfermedadOld)) {
                idEnfermedadNew.getDetalleDiagnosticoList().add(detalleDiagnostico);
                idEnfermedadNew = em.merge(idEnfermedadNew);
            }
            for (Consulta consultaListOldConsulta : consultaListOld) {
                if (!consultaListNew.contains(consultaListOldConsulta)) {
                    consultaListOldConsulta.setIdDiagnostico(null);
                    consultaListOldConsulta = em.merge(consultaListOldConsulta);
                }
            }
            for (Consulta consultaListNewConsulta : consultaListNew) {
                if (!consultaListOld.contains(consultaListNewConsulta)) {
                    DetalleDiagnostico oldIdDiagnosticoOfConsultaListNewConsulta = consultaListNewConsulta.getIdDiagnostico();
                    consultaListNewConsulta.setIdDiagnostico(detalleDiagnostico);
                    consultaListNewConsulta = em.merge(consultaListNewConsulta);
                    if (oldIdDiagnosticoOfConsultaListNewConsulta != null && !oldIdDiagnosticoOfConsultaListNewConsulta.equals(detalleDiagnostico)) {
                        oldIdDiagnosticoOfConsultaListNewConsulta.getConsultaList().remove(consultaListNewConsulta);
                        oldIdDiagnosticoOfConsultaListNewConsulta = em.merge(oldIdDiagnosticoOfConsultaListNewConsulta);
                    }
                }
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
            EnfermedadesCie10 idEnfermedad = detalleDiagnostico.getIdEnfermedad();
            if (idEnfermedad != null) {
                idEnfermedad.getDetalleDiagnosticoList().remove(detalleDiagnostico);
                idEnfermedad = em.merge(idEnfermedad);
            }
            List<Consulta> consultaList = detalleDiagnostico.getConsultaList();
            for (Consulta consultaListConsulta : consultaList) {
                consultaListConsulta.setIdDiagnostico(null);
                consultaListConsulta = em.merge(consultaListConsulta);
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
