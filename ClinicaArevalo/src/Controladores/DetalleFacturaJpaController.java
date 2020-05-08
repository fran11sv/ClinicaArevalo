/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.DetalleFactura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Producto;
import Entidades.Factura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author franb
 */
public class DetalleFacturaJpaController implements Serializable {

    public DetalleFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleFactura detalleFactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = detalleFactura.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                detalleFactura.setIdProducto(idProducto);
            }
            Factura numFactura = detalleFactura.getNumFactura();
            if (numFactura != null) {
                numFactura = em.getReference(numFactura.getClass(), numFactura.getNumFactura());
                detalleFactura.setNumFactura(numFactura);
            }
            em.persist(detalleFactura);
            if (idProducto != null) {
                idProducto.getDetalleFacturaList().add(detalleFactura);
                idProducto = em.merge(idProducto);
            }
            if (numFactura != null) {
                numFactura.getDetalleFacturaList().add(detalleFactura);
                numFactura = em.merge(numFactura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleFactura detalleFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleFactura persistentDetalleFactura = em.find(DetalleFactura.class, detalleFactura.getIdDetalleFactura());
            Producto idProductoOld = persistentDetalleFactura.getIdProducto();
            Producto idProductoNew = detalleFactura.getIdProducto();
            Factura numFacturaOld = persistentDetalleFactura.getNumFactura();
            Factura numFacturaNew = detalleFactura.getNumFactura();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                detalleFactura.setIdProducto(idProductoNew);
            }
            if (numFacturaNew != null) {
                numFacturaNew = em.getReference(numFacturaNew.getClass(), numFacturaNew.getNumFactura());
                detalleFactura.setNumFactura(numFacturaNew);
            }
            detalleFactura = em.merge(detalleFactura);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getDetalleFacturaList().remove(detalleFactura);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getDetalleFacturaList().add(detalleFactura);
                idProductoNew = em.merge(idProductoNew);
            }
            if (numFacturaOld != null && !numFacturaOld.equals(numFacturaNew)) {
                numFacturaOld.getDetalleFacturaList().remove(detalleFactura);
                numFacturaOld = em.merge(numFacturaOld);
            }
            if (numFacturaNew != null && !numFacturaNew.equals(numFacturaOld)) {
                numFacturaNew.getDetalleFacturaList().add(detalleFactura);
                numFacturaNew = em.merge(numFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleFactura.getIdDetalleFactura();
                if (findDetalleFactura(id) == null) {
                    throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.");
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
            DetalleFactura detalleFactura;
            try {
                detalleFactura = em.getReference(DetalleFactura.class, id);
                detalleFactura.getIdDetalleFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.", enfe);
            }
            Producto idProducto = detalleFactura.getIdProducto();
            if (idProducto != null) {
                idProducto.getDetalleFacturaList().remove(detalleFactura);
                idProducto = em.merge(idProducto);
            }
            Factura numFactura = detalleFactura.getNumFactura();
            if (numFactura != null) {
                numFactura.getDetalleFacturaList().remove(detalleFactura);
                numFactura = em.merge(numFactura);
            }
            em.remove(detalleFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleFactura> findDetalleFacturaEntities() {
        return findDetalleFacturaEntities(true, -1, -1);
    }

    public List<DetalleFactura> findDetalleFacturaEntities(int maxResults, int firstResult) {
        return findDetalleFacturaEntities(false, maxResults, firstResult);
    }

    private List<DetalleFactura> findDetalleFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleFactura.class));
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

    public DetalleFactura findDetalleFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleFactura> rt = cq.from(DetalleFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<DetalleFactura> findporFactura(Factura numFactura){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<DetalleFactura> query = em.createNamedQuery("DetalleFactura.findporFactura",DetalleFactura.class);
            query.setParameter("numFactura", numFactura);
            return query.getResultList();
        }finally{
            em.close();
}
    }
}
