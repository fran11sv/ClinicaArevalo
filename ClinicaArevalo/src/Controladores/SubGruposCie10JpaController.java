/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.GruposCie10;
import Entidades.CategoriasCie10;
import Entidades.SubGruposCie10;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franb
 */
public class SubGruposCie10JpaController implements Serializable {

    public SubGruposCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubGruposCie10 subGruposCie10) {
        if (subGruposCie10.getCategoriasCie10List() == null) {
            subGruposCie10.setCategoriasCie10List(new ArrayList<CategoriasCie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GruposCie10 idGrupo = subGruposCie10.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getId());
                subGruposCie10.setIdGrupo(idGrupo);
            }
            List<CategoriasCie10> attachedCategoriasCie10List = new ArrayList<CategoriasCie10>();
            for (CategoriasCie10 categoriasCie10ListCategoriasCie10ToAttach : subGruposCie10.getCategoriasCie10List()) {
                categoriasCie10ListCategoriasCie10ToAttach = em.getReference(categoriasCie10ListCategoriasCie10ToAttach.getClass(), categoriasCie10ListCategoriasCie10ToAttach.getId());
                attachedCategoriasCie10List.add(categoriasCie10ListCategoriasCie10ToAttach);
            }
            subGruposCie10.setCategoriasCie10List(attachedCategoriasCie10List);
            em.persist(subGruposCie10);
            if (idGrupo != null) {
                idGrupo.getSubGruposCie10List().add(subGruposCie10);
                idGrupo = em.merge(idGrupo);
            }
            for (CategoriasCie10 categoriasCie10ListCategoriasCie10 : subGruposCie10.getCategoriasCie10List()) {
                SubGruposCie10 oldIdSubGrupoOfCategoriasCie10ListCategoriasCie10 = categoriasCie10ListCategoriasCie10.getIdSubGrupo();
                categoriasCie10ListCategoriasCie10.setIdSubGrupo(subGruposCie10);
                categoriasCie10ListCategoriasCie10 = em.merge(categoriasCie10ListCategoriasCie10);
                if (oldIdSubGrupoOfCategoriasCie10ListCategoriasCie10 != null) {
                    oldIdSubGrupoOfCategoriasCie10ListCategoriasCie10.getCategoriasCie10List().remove(categoriasCie10ListCategoriasCie10);
                    oldIdSubGrupoOfCategoriasCie10ListCategoriasCie10 = em.merge(oldIdSubGrupoOfCategoriasCie10ListCategoriasCie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SubGruposCie10 subGruposCie10) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubGruposCie10 persistentSubGruposCie10 = em.find(SubGruposCie10.class, subGruposCie10.getId());
            GruposCie10 idGrupoOld = persistentSubGruposCie10.getIdGrupo();
            GruposCie10 idGrupoNew = subGruposCie10.getIdGrupo();
            List<CategoriasCie10> categoriasCie10ListOld = persistentSubGruposCie10.getCategoriasCie10List();
            List<CategoriasCie10> categoriasCie10ListNew = subGruposCie10.getCategoriasCie10List();
            List<String> illegalOrphanMessages = null;
            for (CategoriasCie10 categoriasCie10ListOldCategoriasCie10 : categoriasCie10ListOld) {
                if (!categoriasCie10ListNew.contains(categoriasCie10ListOldCategoriasCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CategoriasCie10 " + categoriasCie10ListOldCategoriasCie10 + " since its idSubGrupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getId());
                subGruposCie10.setIdGrupo(idGrupoNew);
            }
            List<CategoriasCie10> attachedCategoriasCie10ListNew = new ArrayList<CategoriasCie10>();
            for (CategoriasCie10 categoriasCie10ListNewCategoriasCie10ToAttach : categoriasCie10ListNew) {
                categoriasCie10ListNewCategoriasCie10ToAttach = em.getReference(categoriasCie10ListNewCategoriasCie10ToAttach.getClass(), categoriasCie10ListNewCategoriasCie10ToAttach.getId());
                attachedCategoriasCie10ListNew.add(categoriasCie10ListNewCategoriasCie10ToAttach);
            }
            categoriasCie10ListNew = attachedCategoriasCie10ListNew;
            subGruposCie10.setCategoriasCie10List(categoriasCie10ListNew);
            subGruposCie10 = em.merge(subGruposCie10);
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getSubGruposCie10List().remove(subGruposCie10);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getSubGruposCie10List().add(subGruposCie10);
                idGrupoNew = em.merge(idGrupoNew);
            }
            for (CategoriasCie10 categoriasCie10ListNewCategoriasCie10 : categoriasCie10ListNew) {
                if (!categoriasCie10ListOld.contains(categoriasCie10ListNewCategoriasCie10)) {
                    SubGruposCie10 oldIdSubGrupoOfCategoriasCie10ListNewCategoriasCie10 = categoriasCie10ListNewCategoriasCie10.getIdSubGrupo();
                    categoriasCie10ListNewCategoriasCie10.setIdSubGrupo(subGruposCie10);
                    categoriasCie10ListNewCategoriasCie10 = em.merge(categoriasCie10ListNewCategoriasCie10);
                    if (oldIdSubGrupoOfCategoriasCie10ListNewCategoriasCie10 != null && !oldIdSubGrupoOfCategoriasCie10ListNewCategoriasCie10.equals(subGruposCie10)) {
                        oldIdSubGrupoOfCategoriasCie10ListNewCategoriasCie10.getCategoriasCie10List().remove(categoriasCie10ListNewCategoriasCie10);
                        oldIdSubGrupoOfCategoriasCie10ListNewCategoriasCie10 = em.merge(oldIdSubGrupoOfCategoriasCie10ListNewCategoriasCie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subGruposCie10.getId();
                if (findSubGruposCie10(id) == null) {
                    throw new NonexistentEntityException("The subGruposCie10 with id " + id + " no longer exists.");
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
            SubGruposCie10 subGruposCie10;
            try {
                subGruposCie10 = em.getReference(SubGruposCie10.class, id);
                subGruposCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subGruposCie10 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CategoriasCie10> categoriasCie10ListOrphanCheck = subGruposCie10.getCategoriasCie10List();
            for (CategoriasCie10 categoriasCie10ListOrphanCheckCategoriasCie10 : categoriasCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SubGruposCie10 (" + subGruposCie10 + ") cannot be destroyed since the CategoriasCie10 " + categoriasCie10ListOrphanCheckCategoriasCie10 + " in its categoriasCie10List field has a non-nullable idSubGrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            GruposCie10 idGrupo = subGruposCie10.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getSubGruposCie10List().remove(subGruposCie10);
                idGrupo = em.merge(idGrupo);
            }
            em.remove(subGruposCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SubGruposCie10> findSubGruposCie10Entities() {
        return findSubGruposCie10Entities(true, -1, -1);
    }

    public List<SubGruposCie10> findSubGruposCie10Entities(int maxResults, int firstResult) {
        return findSubGruposCie10Entities(false, maxResults, firstResult);
    }

    private List<SubGruposCie10> findSubGruposCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubGruposCie10.class));
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

    public SubGruposCie10 findSubGruposCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SubGruposCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubGruposCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubGruposCie10> rt = cq.from(SubGruposCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
