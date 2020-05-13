/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Citas;
import java.util.ArrayList;
import java.util.List;
import Entidades.Consulta;
import Entidades.Factura;
import Entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author franb
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getCitasList() == null) {
            usuario.setCitasList(new ArrayList<Citas>());
        }
        if (usuario.getConsultaList() == null) {
            usuario.setConsultaList(new ArrayList<Consulta>());
        }
        if (usuario.getFacturaList() == null) {
            usuario.setFacturaList(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Citas> attachedCitasList = new ArrayList<Citas>();
            for (Citas citasListCitasToAttach : usuario.getCitasList()) {
                citasListCitasToAttach = em.getReference(citasListCitasToAttach.getClass(), citasListCitasToAttach.getIdCita());
                attachedCitasList.add(citasListCitasToAttach);
            }
            usuario.setCitasList(attachedCitasList);
            List<Consulta> attachedConsultaList = new ArrayList<Consulta>();
            for (Consulta consultaListConsultaToAttach : usuario.getConsultaList()) {
                consultaListConsultaToAttach = em.getReference(consultaListConsultaToAttach.getClass(), consultaListConsultaToAttach.getIdConsulta());
                attachedConsultaList.add(consultaListConsultaToAttach);
            }
            usuario.setConsultaList(attachedConsultaList);
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : usuario.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getNumFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            usuario.setFacturaList(attachedFacturaList);
            em.persist(usuario);
            for (Citas citasListCitas : usuario.getCitasList()) {
                Usuario oldIdUsuarioOfCitasListCitas = citasListCitas.getIdUsuario();
                citasListCitas.setIdUsuario(usuario);
                citasListCitas = em.merge(citasListCitas);
                if (oldIdUsuarioOfCitasListCitas != null) {
                    oldIdUsuarioOfCitasListCitas.getCitasList().remove(citasListCitas);
                    oldIdUsuarioOfCitasListCitas = em.merge(oldIdUsuarioOfCitasListCitas);
                }
            }
            for (Consulta consultaListConsulta : usuario.getConsultaList()) {
                Usuario oldIdUsuarioOfConsultaListConsulta = consultaListConsulta.getIdUsuario();
                consultaListConsulta.setIdUsuario(usuario);
                consultaListConsulta = em.merge(consultaListConsulta);
                if (oldIdUsuarioOfConsultaListConsulta != null) {
                    oldIdUsuarioOfConsultaListConsulta.getConsultaList().remove(consultaListConsulta);
                    oldIdUsuarioOfConsultaListConsulta = em.merge(oldIdUsuarioOfConsultaListConsulta);
                }
            }
            for (Factura facturaListFactura : usuario.getFacturaList()) {
                Usuario oldIdUsuarioOfFacturaListFactura = facturaListFactura.getIdUsuario();
                facturaListFactura.setIdUsuario(usuario);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdUsuarioOfFacturaListFactura != null) {
                    oldIdUsuarioOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdUsuarioOfFacturaListFactura = em.merge(oldIdUsuarioOfFacturaListFactura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            List<Citas> citasListOld = persistentUsuario.getCitasList();
            List<Citas> citasListNew = usuario.getCitasList();
            List<Consulta> consultaListOld = persistentUsuario.getConsultaList();
            List<Consulta> consultaListNew = usuario.getConsultaList();
            List<Factura> facturaListOld = persistentUsuario.getFacturaList();
            List<Factura> facturaListNew = usuario.getFacturaList();
            List<String> illegalOrphanMessages = null;
            for (Citas citasListOldCitas : citasListOld) {
                if (!citasListNew.contains(citasListOldCitas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Citas " + citasListOldCitas + " since its idUsuario field is not nullable.");
                }
            }
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Citas> attachedCitasListNew = new ArrayList<Citas>();
            for (Citas citasListNewCitasToAttach : citasListNew) {
                citasListNewCitasToAttach = em.getReference(citasListNewCitasToAttach.getClass(), citasListNewCitasToAttach.getIdCita());
                attachedCitasListNew.add(citasListNewCitasToAttach);
            }
            citasListNew = attachedCitasListNew;
            usuario.setCitasList(citasListNew);
            List<Consulta> attachedConsultaListNew = new ArrayList<Consulta>();
            for (Consulta consultaListNewConsultaToAttach : consultaListNew) {
                consultaListNewConsultaToAttach = em.getReference(consultaListNewConsultaToAttach.getClass(), consultaListNewConsultaToAttach.getIdConsulta());
                attachedConsultaListNew.add(consultaListNewConsultaToAttach);
            }
            consultaListNew = attachedConsultaListNew;
            usuario.setConsultaList(consultaListNew);
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getNumFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            usuario.setFacturaList(facturaListNew);
            usuario = em.merge(usuario);
            for (Citas citasListNewCitas : citasListNew) {
                if (!citasListOld.contains(citasListNewCitas)) {
                    Usuario oldIdUsuarioOfCitasListNewCitas = citasListNewCitas.getIdUsuario();
                    citasListNewCitas.setIdUsuario(usuario);
                    citasListNewCitas = em.merge(citasListNewCitas);
                    if (oldIdUsuarioOfCitasListNewCitas != null && !oldIdUsuarioOfCitasListNewCitas.equals(usuario)) {
                        oldIdUsuarioOfCitasListNewCitas.getCitasList().remove(citasListNewCitas);
                        oldIdUsuarioOfCitasListNewCitas = em.merge(oldIdUsuarioOfCitasListNewCitas);
                    }
                }
            }
            for (Consulta consultaListOldConsulta : consultaListOld) {
                if (!consultaListNew.contains(consultaListOldConsulta)) {
                    consultaListOldConsulta.setIdUsuario(null);
                    consultaListOldConsulta = em.merge(consultaListOldConsulta);
                }
            }
            for (Consulta consultaListNewConsulta : consultaListNew) {
                if (!consultaListOld.contains(consultaListNewConsulta)) {
                    Usuario oldIdUsuarioOfConsultaListNewConsulta = consultaListNewConsulta.getIdUsuario();
                    consultaListNewConsulta.setIdUsuario(usuario);
                    consultaListNewConsulta = em.merge(consultaListNewConsulta);
                    if (oldIdUsuarioOfConsultaListNewConsulta != null && !oldIdUsuarioOfConsultaListNewConsulta.equals(usuario)) {
                        oldIdUsuarioOfConsultaListNewConsulta.getConsultaList().remove(consultaListNewConsulta);
                        oldIdUsuarioOfConsultaListNewConsulta = em.merge(oldIdUsuarioOfConsultaListNewConsulta);
                    }
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Usuario oldIdUsuarioOfFacturaListNewFactura = facturaListNewFactura.getIdUsuario();
                    facturaListNewFactura.setIdUsuario(usuario);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdUsuarioOfFacturaListNewFactura != null && !oldIdUsuarioOfFacturaListNewFactura.equals(usuario)) {
                        oldIdUsuarioOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdUsuarioOfFacturaListNewFactura = em.merge(oldIdUsuarioOfFacturaListNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Citas> citasListOrphanCheck = usuario.getCitasList();
            for (Citas citasListOrphanCheckCitas : citasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Citas " + citasListOrphanCheckCitas + " in its citasList field has a non-nullable idUsuario field.");
            }
            List<Factura> facturaListOrphanCheck = usuario.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Consulta> consultaList = usuario.getConsultaList();
            for (Consulta consultaListConsulta : consultaList) {
                consultaListConsulta.setIdUsuario(null);
                consultaListConsulta = em.merge(consultaListConsulta);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Usuario> findporJoin(String usuario){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findporId",Usuario.class);
            query.setParameter("idProd", "="+usuario);
            return query.getResultList();
        }finally{
            em.close();
}
    }
    public List<Usuario> Validacion(String usuario){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.Validacion",Usuario.class);
            query.setParameter("Nusuario", usuario);
            return query.getResultList();
        }finally{
            em.close();
}
    }
}
