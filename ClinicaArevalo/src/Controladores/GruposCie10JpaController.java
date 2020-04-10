/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Entidades.GruposCie10;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.SubGruposCie10;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franb
 */
public class GruposCie10JpaController implements Serializable {

    public GruposCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GruposCie10 gruposCie10) {
        if (gruposCie10.getSubGruposCie10List() == null) {
            gruposCie10.setSubGruposCie10List(new ArrayList<SubGruposCie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SubGruposCie10> attachedSubGruposCie10List = new ArrayList<SubGruposCie10>();
            for (SubGruposCie10 subGruposCie10ListSubGruposCie10ToAttach : gruposCie10.getSubGruposCie10List()) {
                subGruposCie10ListSubGruposCie10ToAttach = em.getReference(subGruposCie10ListSubGruposCie10ToAttach.getClass(), subGruposCie10ListSubGruposCie10ToAttach.getId());
                attachedSubGruposCie10List.add(subGruposCie10ListSubGruposCie10ToAttach);
            }
            gruposCie10.setSubGruposCie10List(attachedSubGruposCie10List);
            em.persist(gruposCie10);
            for (SubGruposCie10 subGruposCie10ListSubGruposCie10 : gruposCie10.getSubGruposCie10List()) {
                GruposCie10 oldIdGrupoOfSubGruposCie10ListSubGruposCie10 = subGruposCie10ListSubGruposCie10.getIdGrupo();
                subGruposCie10ListSubGruposCie10.setIdGrupo(gruposCie10);
                subGruposCie10ListSubGruposCie10 = em.merge(subGruposCie10ListSubGruposCie10);
                if (oldIdGrupoOfSubGruposCie10ListSubGruposCie10 != null) {
                    oldIdGrupoOfSubGruposCie10ListSubGruposCie10.getSubGruposCie10List().remove(subGruposCie10ListSubGruposCie10);
                    oldIdGrupoOfSubGruposCie10ListSubGruposCie10 = em.merge(oldIdGrupoOfSubGruposCie10ListSubGruposCie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GruposCie10 gruposCie10) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GruposCie10 persistentGruposCie10 = em.find(GruposCie10.class, gruposCie10.getId());
            List<SubGruposCie10> subGruposCie10ListOld = persistentGruposCie10.getSubGruposCie10List();
            List<SubGruposCie10> subGruposCie10ListNew = gruposCie10.getSubGruposCie10List();
            List<String> illegalOrphanMessages = null;
            for (SubGruposCie10 subGruposCie10ListOldSubGruposCie10 : subGruposCie10ListOld) {
                if (!subGruposCie10ListNew.contains(subGruposCie10ListOldSubGruposCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SubGruposCie10 " + subGruposCie10ListOldSubGruposCie10 + " since its idGrupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SubGruposCie10> attachedSubGruposCie10ListNew = new ArrayList<SubGruposCie10>();
            for (SubGruposCie10 subGruposCie10ListNewSubGruposCie10ToAttach : subGruposCie10ListNew) {
                subGruposCie10ListNewSubGruposCie10ToAttach = em.getReference(subGruposCie10ListNewSubGruposCie10ToAttach.getClass(), subGruposCie10ListNewSubGruposCie10ToAttach.getId());
                attachedSubGruposCie10ListNew.add(subGruposCie10ListNewSubGruposCie10ToAttach);
            }
            subGruposCie10ListNew = attachedSubGruposCie10ListNew;
            gruposCie10.setSubGruposCie10List(subGruposCie10ListNew);
            gruposCie10 = em.merge(gruposCie10);
            for (SubGruposCie10 subGruposCie10ListNewSubGruposCie10 : subGruposCie10ListNew) {
                if (!subGruposCie10ListOld.contains(subGruposCie10ListNewSubGruposCie10)) {
                    GruposCie10 oldIdGrupoOfSubGruposCie10ListNewSubGruposCie10 = subGruposCie10ListNewSubGruposCie10.getIdGrupo();
                    subGruposCie10ListNewSubGruposCie10.setIdGrupo(gruposCie10);
                    subGruposCie10ListNewSubGruposCie10 = em.merge(subGruposCie10ListNewSubGruposCie10);
                    if (oldIdGrupoOfSubGruposCie10ListNewSubGruposCie10 != null && !oldIdGrupoOfSubGruposCie10ListNewSubGruposCie10.equals(gruposCie10)) {
                        oldIdGrupoOfSubGruposCie10ListNewSubGruposCie10.getSubGruposCie10List().remove(subGruposCie10ListNewSubGruposCie10);
                        oldIdGrupoOfSubGruposCie10ListNewSubGruposCie10 = em.merge(oldIdGrupoOfSubGruposCie10ListNewSubGruposCie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gruposCie10.getId();
                if (findGruposCie10(id) == null) {
                    throw new NonexistentEntityException("The gruposCie10 with id " + id + " no longer exists.");
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
            GruposCie10 gruposCie10;
            try {
                gruposCie10 = em.getReference(GruposCie10.class, id);
                gruposCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gruposCie10 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SubGruposCie10> subGruposCie10ListOrphanCheck = gruposCie10.getSubGruposCie10List();
            for (SubGruposCie10 subGruposCie10ListOrphanCheckSubGruposCie10 : subGruposCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This GruposCie10 (" + gruposCie10 + ") cannot be destroyed since the SubGruposCie10 " + subGruposCie10ListOrphanCheckSubGruposCie10 + " in its subGruposCie10List field has a non-nullable idGrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(gruposCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GruposCie10> findGruposCie10Entities() {
        return findGruposCie10Entities(true, -1, -1);
    }

    public List<GruposCie10> findGruposCie10Entities(int maxResults, int firstResult) {
        return findGruposCie10Entities(false, maxResults, firstResult);
    }

    private List<GruposCie10> findGruposCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GruposCie10.class));
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

    public GruposCie10 findGruposCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GruposCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getGruposCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GruposCie10> rt = cq.from(GruposCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
