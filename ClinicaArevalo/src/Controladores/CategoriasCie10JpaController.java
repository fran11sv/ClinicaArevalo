/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Entidades.CategoriasCie10;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.SubGruposCie10;
import Entidades.EnfermedadesCie10;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author babef
 */
public class CategoriasCie10JpaController implements Serializable {

    public CategoriasCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriasCie10 categoriasCie10) {
        if (categoriasCie10.getEnfermedadesCie10List() == null) {
            categoriasCie10.setEnfermedadesCie10List(new ArrayList<EnfermedadesCie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubGruposCie10 idSubGrupo = categoriasCie10.getIdSubGrupo();
            if (idSubGrupo != null) {
                idSubGrupo = em.getReference(idSubGrupo.getClass(), idSubGrupo.getId());
                categoriasCie10.setIdSubGrupo(idSubGrupo);
            }
            List<EnfermedadesCie10> attachedEnfermedadesCie10List = new ArrayList<EnfermedadesCie10>();
            for (EnfermedadesCie10 enfermedadesCie10ListEnfermedadesCie10ToAttach : categoriasCie10.getEnfermedadesCie10List()) {
                enfermedadesCie10ListEnfermedadesCie10ToAttach = em.getReference(enfermedadesCie10ListEnfermedadesCie10ToAttach.getClass(), enfermedadesCie10ListEnfermedadesCie10ToAttach.getId());
                attachedEnfermedadesCie10List.add(enfermedadesCie10ListEnfermedadesCie10ToAttach);
            }
            categoriasCie10.setEnfermedadesCie10List(attachedEnfermedadesCie10List);
            em.persist(categoriasCie10);
            if (idSubGrupo != null) {
                idSubGrupo.getCategoriasCie10List().add(categoriasCie10);
                idSubGrupo = em.merge(idSubGrupo);
            }
            for (EnfermedadesCie10 enfermedadesCie10ListEnfermedadesCie10 : categoriasCie10.getEnfermedadesCie10List()) {
                CategoriasCie10 oldIdCategoriaOfEnfermedadesCie10ListEnfermedadesCie10 = enfermedadesCie10ListEnfermedadesCie10.getIdCategoria();
                enfermedadesCie10ListEnfermedadesCie10.setIdCategoria(categoriasCie10);
                enfermedadesCie10ListEnfermedadesCie10 = em.merge(enfermedadesCie10ListEnfermedadesCie10);
                if (oldIdCategoriaOfEnfermedadesCie10ListEnfermedadesCie10 != null) {
                    oldIdCategoriaOfEnfermedadesCie10ListEnfermedadesCie10.getEnfermedadesCie10List().remove(enfermedadesCie10ListEnfermedadesCie10);
                    oldIdCategoriaOfEnfermedadesCie10ListEnfermedadesCie10 = em.merge(oldIdCategoriaOfEnfermedadesCie10ListEnfermedadesCie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriasCie10 categoriasCie10) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriasCie10 persistentCategoriasCie10 = em.find(CategoriasCie10.class, categoriasCie10.getId());
            SubGruposCie10 idSubGrupoOld = persistentCategoriasCie10.getIdSubGrupo();
            SubGruposCie10 idSubGrupoNew = categoriasCie10.getIdSubGrupo();
            List<EnfermedadesCie10> enfermedadesCie10ListOld = persistentCategoriasCie10.getEnfermedadesCie10List();
            List<EnfermedadesCie10> enfermedadesCie10ListNew = categoriasCie10.getEnfermedadesCie10List();
            List<String> illegalOrphanMessages = null;
            for (EnfermedadesCie10 enfermedadesCie10ListOldEnfermedadesCie10 : enfermedadesCie10ListOld) {
                if (!enfermedadesCie10ListNew.contains(enfermedadesCie10ListOldEnfermedadesCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfermedadesCie10 " + enfermedadesCie10ListOldEnfermedadesCie10 + " since its idCategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idSubGrupoNew != null) {
                idSubGrupoNew = em.getReference(idSubGrupoNew.getClass(), idSubGrupoNew.getId());
                categoriasCie10.setIdSubGrupo(idSubGrupoNew);
            }
            List<EnfermedadesCie10> attachedEnfermedadesCie10ListNew = new ArrayList<EnfermedadesCie10>();
            for (EnfermedadesCie10 enfermedadesCie10ListNewEnfermedadesCie10ToAttach : enfermedadesCie10ListNew) {
                enfermedadesCie10ListNewEnfermedadesCie10ToAttach = em.getReference(enfermedadesCie10ListNewEnfermedadesCie10ToAttach.getClass(), enfermedadesCie10ListNewEnfermedadesCie10ToAttach.getId());
                attachedEnfermedadesCie10ListNew.add(enfermedadesCie10ListNewEnfermedadesCie10ToAttach);
            }
            enfermedadesCie10ListNew = attachedEnfermedadesCie10ListNew;
            categoriasCie10.setEnfermedadesCie10List(enfermedadesCie10ListNew);
            categoriasCie10 = em.merge(categoriasCie10);
            if (idSubGrupoOld != null && !idSubGrupoOld.equals(idSubGrupoNew)) {
                idSubGrupoOld.getCategoriasCie10List().remove(categoriasCie10);
                idSubGrupoOld = em.merge(idSubGrupoOld);
            }
            if (idSubGrupoNew != null && !idSubGrupoNew.equals(idSubGrupoOld)) {
                idSubGrupoNew.getCategoriasCie10List().add(categoriasCie10);
                idSubGrupoNew = em.merge(idSubGrupoNew);
            }
            for (EnfermedadesCie10 enfermedadesCie10ListNewEnfermedadesCie10 : enfermedadesCie10ListNew) {
                if (!enfermedadesCie10ListOld.contains(enfermedadesCie10ListNewEnfermedadesCie10)) {
                    CategoriasCie10 oldIdCategoriaOfEnfermedadesCie10ListNewEnfermedadesCie10 = enfermedadesCie10ListNewEnfermedadesCie10.getIdCategoria();
                    enfermedadesCie10ListNewEnfermedadesCie10.setIdCategoria(categoriasCie10);
                    enfermedadesCie10ListNewEnfermedadesCie10 = em.merge(enfermedadesCie10ListNewEnfermedadesCie10);
                    if (oldIdCategoriaOfEnfermedadesCie10ListNewEnfermedadesCie10 != null && !oldIdCategoriaOfEnfermedadesCie10ListNewEnfermedadesCie10.equals(categoriasCie10)) {
                        oldIdCategoriaOfEnfermedadesCie10ListNewEnfermedadesCie10.getEnfermedadesCie10List().remove(enfermedadesCie10ListNewEnfermedadesCie10);
                        oldIdCategoriaOfEnfermedadesCie10ListNewEnfermedadesCie10 = em.merge(oldIdCategoriaOfEnfermedadesCie10ListNewEnfermedadesCie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriasCie10.getId();
                if (findCategoriasCie10(id) == null) {
                    throw new NonexistentEntityException("The categoriasCie10 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriasCie10 categoriasCie10;
            try {
                categoriasCie10 = em.getReference(CategoriasCie10.class, id);
                categoriasCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriasCie10 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EnfermedadesCie10> enfermedadesCie10ListOrphanCheck = categoriasCie10.getEnfermedadesCie10List();
            for (EnfermedadesCie10 enfermedadesCie10ListOrphanCheckEnfermedadesCie10 : enfermedadesCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CategoriasCie10 (" + categoriasCie10 + ") cannot be destroyed since the EnfermedadesCie10 " + enfermedadesCie10ListOrphanCheckEnfermedadesCie10 + " in its enfermedadesCie10List field has a non-nullable idCategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SubGruposCie10 idSubGrupo = categoriasCie10.getIdSubGrupo();
            if (idSubGrupo != null) {
                idSubGrupo.getCategoriasCie10List().remove(categoriasCie10);
                idSubGrupo = em.merge(idSubGrupo);
            }
            em.remove(categoriasCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriasCie10> findCategoriasCie10Entities() {
        return findCategoriasCie10Entities(true, -1, -1);
    }

    public List<CategoriasCie10> findCategoriasCie10Entities(int maxResults, int firstResult) {
        return findCategoriasCie10Entities(false, maxResults, firstResult);
    }

    private List<CategoriasCie10> findCategoriasCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriasCie10.class));
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

    public CategoriasCie10 findCategoriasCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriasCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriasCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriasCie10> rt = cq.from(CategoriasCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
