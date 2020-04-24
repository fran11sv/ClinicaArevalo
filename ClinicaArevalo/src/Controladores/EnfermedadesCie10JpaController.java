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
import Entidades.CategoriasCie10;
import Entidades.DetalleDiagnostico;
import Entidades.EnfermedadesCie10;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author franb
 */
public class EnfermedadesCie10JpaController implements Serializable {

    public EnfermedadesCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfermedadesCie10 enfermedadesCie10) {
        if (enfermedadesCie10.getDetalleDiagnosticoList() == null) {
            enfermedadesCie10.setDetalleDiagnosticoList(new ArrayList<DetalleDiagnostico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriasCie10 idCategoria = enfermedadesCie10.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
                enfermedadesCie10.setIdCategoria(idCategoria);
            }
            List<DetalleDiagnostico> attachedDetalleDiagnosticoList = new ArrayList<DetalleDiagnostico>();
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnosticoToAttach : enfermedadesCie10.getDetalleDiagnosticoList()) {
                detalleDiagnosticoListDetalleDiagnosticoToAttach = em.getReference(detalleDiagnosticoListDetalleDiagnosticoToAttach.getClass(), detalleDiagnosticoListDetalleDiagnosticoToAttach.getIdDiagnostico());
                attachedDetalleDiagnosticoList.add(detalleDiagnosticoListDetalleDiagnosticoToAttach);
            }
            enfermedadesCie10.setDetalleDiagnosticoList(attachedDetalleDiagnosticoList);
            em.persist(enfermedadesCie10);
            if (idCategoria != null) {
                idCategoria.getEnfermedadesCie10List().add(enfermedadesCie10);
                idCategoria = em.merge(idCategoria);
            }
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnostico : enfermedadesCie10.getDetalleDiagnosticoList()) {
                EnfermedadesCie10 oldIdEnfermedadOfDetalleDiagnosticoListDetalleDiagnostico = detalleDiagnosticoListDetalleDiagnostico.getIdEnfermedad();
                detalleDiagnosticoListDetalleDiagnostico.setIdEnfermedad(enfermedadesCie10);
                detalleDiagnosticoListDetalleDiagnostico = em.merge(detalleDiagnosticoListDetalleDiagnostico);
                if (oldIdEnfermedadOfDetalleDiagnosticoListDetalleDiagnostico != null) {
                    oldIdEnfermedadOfDetalleDiagnosticoListDetalleDiagnostico.getDetalleDiagnosticoList().remove(detalleDiagnosticoListDetalleDiagnostico);
                    oldIdEnfermedadOfDetalleDiagnosticoListDetalleDiagnostico = em.merge(oldIdEnfermedadOfDetalleDiagnosticoListDetalleDiagnostico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfermedadesCie10 enfermedadesCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfermedadesCie10 persistentEnfermedadesCie10 = em.find(EnfermedadesCie10.class, enfermedadesCie10.getId());
            CategoriasCie10 idCategoriaOld = persistentEnfermedadesCie10.getIdCategoria();
            CategoriasCie10 idCategoriaNew = enfermedadesCie10.getIdCategoria();
            List<DetalleDiagnostico> detalleDiagnosticoListOld = persistentEnfermedadesCie10.getDetalleDiagnosticoList();
            List<DetalleDiagnostico> detalleDiagnosticoListNew = enfermedadesCie10.getDetalleDiagnosticoList();
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
                enfermedadesCie10.setIdCategoria(idCategoriaNew);
            }
            List<DetalleDiagnostico> attachedDetalleDiagnosticoListNew = new ArrayList<DetalleDiagnostico>();
            for (DetalleDiagnostico detalleDiagnosticoListNewDetalleDiagnosticoToAttach : detalleDiagnosticoListNew) {
                detalleDiagnosticoListNewDetalleDiagnosticoToAttach = em.getReference(detalleDiagnosticoListNewDetalleDiagnosticoToAttach.getClass(), detalleDiagnosticoListNewDetalleDiagnosticoToAttach.getIdDiagnostico());
                attachedDetalleDiagnosticoListNew.add(detalleDiagnosticoListNewDetalleDiagnosticoToAttach);
            }
            detalleDiagnosticoListNew = attachedDetalleDiagnosticoListNew;
            enfermedadesCie10.setDetalleDiagnosticoList(detalleDiagnosticoListNew);
            enfermedadesCie10 = em.merge(enfermedadesCie10);
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getEnfermedadesCie10List().remove(enfermedadesCie10);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getEnfermedadesCie10List().add(enfermedadesCie10);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            for (DetalleDiagnostico detalleDiagnosticoListOldDetalleDiagnostico : detalleDiagnosticoListOld) {
                if (!detalleDiagnosticoListNew.contains(detalleDiagnosticoListOldDetalleDiagnostico)) {
                    detalleDiagnosticoListOldDetalleDiagnostico.setIdEnfermedad(null);
                    detalleDiagnosticoListOldDetalleDiagnostico = em.merge(detalleDiagnosticoListOldDetalleDiagnostico);
                }
            }
            for (DetalleDiagnostico detalleDiagnosticoListNewDetalleDiagnostico : detalleDiagnosticoListNew) {
                if (!detalleDiagnosticoListOld.contains(detalleDiagnosticoListNewDetalleDiagnostico)) {
                    EnfermedadesCie10 oldIdEnfermedadOfDetalleDiagnosticoListNewDetalleDiagnostico = detalleDiagnosticoListNewDetalleDiagnostico.getIdEnfermedad();
                    detalleDiagnosticoListNewDetalleDiagnostico.setIdEnfermedad(enfermedadesCie10);
                    detalleDiagnosticoListNewDetalleDiagnostico = em.merge(detalleDiagnosticoListNewDetalleDiagnostico);
                    if (oldIdEnfermedadOfDetalleDiagnosticoListNewDetalleDiagnostico != null && !oldIdEnfermedadOfDetalleDiagnosticoListNewDetalleDiagnostico.equals(enfermedadesCie10)) {
                        oldIdEnfermedadOfDetalleDiagnosticoListNewDetalleDiagnostico.getDetalleDiagnosticoList().remove(detalleDiagnosticoListNewDetalleDiagnostico);
                        oldIdEnfermedadOfDetalleDiagnosticoListNewDetalleDiagnostico = em.merge(oldIdEnfermedadOfDetalleDiagnosticoListNewDetalleDiagnostico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfermedadesCie10.getId();
                if (findEnfermedadesCie10(id) == null) {
                    throw new NonexistentEntityException("The enfermedadesCie10 with id " + id + " no longer exists.");
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
            EnfermedadesCie10 enfermedadesCie10;
            try {
                enfermedadesCie10 = em.getReference(EnfermedadesCie10.class, id);
                enfermedadesCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfermedadesCie10 with id " + id + " no longer exists.", enfe);
            }
            CategoriasCie10 idCategoria = enfermedadesCie10.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getEnfermedadesCie10List().remove(enfermedadesCie10);
                idCategoria = em.merge(idCategoria);
            }
            List<DetalleDiagnostico> detalleDiagnosticoList = enfermedadesCie10.getDetalleDiagnosticoList();
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnostico : detalleDiagnosticoList) {
                detalleDiagnosticoListDetalleDiagnostico.setIdEnfermedad(null);
                detalleDiagnosticoListDetalleDiagnostico = em.merge(detalleDiagnosticoListDetalleDiagnostico);
            }
            em.remove(enfermedadesCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfermedadesCie10> findEnfermedadesCie10Entities() {
        return findEnfermedadesCie10Entities(true, -1, -1);
    }

    public List<EnfermedadesCie10> findEnfermedadesCie10Entities(int maxResults, int firstResult) {
        return findEnfermedadesCie10Entities(false, maxResults, firstResult);
    }

    private List<EnfermedadesCie10> findEnfermedadesCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfermedadesCie10.class));
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

    public EnfermedadesCie10 findEnfermedadesCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfermedadesCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfermedadesCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfermedadesCie10> rt = cq.from(EnfermedadesCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    } public List<EnfermedadesCie10> findEnfermedadporNombre (String nombre) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<EnfermedadesCie10> query=em.createNamedQuery("findEnfermedades.findbyNombre",EnfermedadesCie10.class);
            query.setParameter("nombreBuscar","%"+nombre+"%");
            query.setMaxResults(50);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
     public List<EnfermedadesCie10> findEnfermedadporCategoria (String categoria) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<EnfermedadesCie10> query=em.createNamedQuery("findEnfermedades.findByCategoria",EnfermedadesCie10.class);
            query.setParameter("categoriaBuscar","%"+categoria+"%");
            query.setMaxResults(50);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
