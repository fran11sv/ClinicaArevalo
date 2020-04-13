/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Entidades.Consulta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Paciente;
import Entidades.Usuario;
import Entidades.Receta;
import java.util.ArrayList;
import java.util.List;
import Entidades.DetalleDiagnostico;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franb
 */
public class ConsultaJpaController implements Serializable {

    public ConsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consulta consulta) {
        if (consulta.getRecetaList() == null) {
            consulta.setRecetaList(new ArrayList<Receta>());
        }
        if (consulta.getDetalleDiagnosticoList() == null) {
            consulta.setDetalleDiagnosticoList(new ArrayList<DetalleDiagnostico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente idPaciente = consulta.getIdPaciente();
            if (idPaciente != null) {
                idPaciente = em.getReference(idPaciente.getClass(), idPaciente.getIdPaciente());
                consulta.setIdPaciente(idPaciente);
            }
            Usuario idUsuario = consulta.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                consulta.setIdUsuario(idUsuario);
            }
            List<Receta> attachedRecetaList = new ArrayList<Receta>();
            for (Receta recetaListRecetaToAttach : consulta.getRecetaList()) {
                recetaListRecetaToAttach = em.getReference(recetaListRecetaToAttach.getClass(), recetaListRecetaToAttach.getNumReceta());
                attachedRecetaList.add(recetaListRecetaToAttach);
            }
            consulta.setRecetaList(attachedRecetaList);
            List<DetalleDiagnostico> attachedDetalleDiagnosticoList = new ArrayList<DetalleDiagnostico>();
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnosticoToAttach : consulta.getDetalleDiagnosticoList()) {
                detalleDiagnosticoListDetalleDiagnosticoToAttach = em.getReference(detalleDiagnosticoListDetalleDiagnosticoToAttach.getClass(), detalleDiagnosticoListDetalleDiagnosticoToAttach.getIdDiagnostico());
                attachedDetalleDiagnosticoList.add(detalleDiagnosticoListDetalleDiagnosticoToAttach);
            }
            consulta.setDetalleDiagnosticoList(attachedDetalleDiagnosticoList);
            em.persist(consulta);
            if (idPaciente != null) {
                idPaciente.getConsultaList().add(consulta);
                idPaciente = em.merge(idPaciente);
            }
            if (idUsuario != null) {
                idUsuario.getConsultaList().add(consulta);
                idUsuario = em.merge(idUsuario);
            }
            for (Receta recetaListReceta : consulta.getRecetaList()) {
                Consulta oldIdConsultaOfRecetaListReceta = recetaListReceta.getIdConsulta();
                recetaListReceta.setIdConsulta(consulta);
                recetaListReceta = em.merge(recetaListReceta);
                if (oldIdConsultaOfRecetaListReceta != null) {
                    oldIdConsultaOfRecetaListReceta.getRecetaList().remove(recetaListReceta);
                    oldIdConsultaOfRecetaListReceta = em.merge(oldIdConsultaOfRecetaListReceta);
                }
            }
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnostico : consulta.getDetalleDiagnosticoList()) {
                Consulta oldIdConsultaOfDetalleDiagnosticoListDetalleDiagnostico = detalleDiagnosticoListDetalleDiagnostico.getIdConsulta();
                detalleDiagnosticoListDetalleDiagnostico.setIdConsulta(consulta);
                detalleDiagnosticoListDetalleDiagnostico = em.merge(detalleDiagnosticoListDetalleDiagnostico);
                if (oldIdConsultaOfDetalleDiagnosticoListDetalleDiagnostico != null) {
                    oldIdConsultaOfDetalleDiagnosticoListDetalleDiagnostico.getDetalleDiagnosticoList().remove(detalleDiagnosticoListDetalleDiagnostico);
                    oldIdConsultaOfDetalleDiagnosticoListDetalleDiagnostico = em.merge(oldIdConsultaOfDetalleDiagnosticoListDetalleDiagnostico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consulta consulta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consulta persistentConsulta = em.find(Consulta.class, consulta.getIdConsulta());
            Paciente idPacienteOld = persistentConsulta.getIdPaciente();
            Paciente idPacienteNew = consulta.getIdPaciente();
            Usuario idUsuarioOld = persistentConsulta.getIdUsuario();
            Usuario idUsuarioNew = consulta.getIdUsuario();
            List<Receta> recetaListOld = persistentConsulta.getRecetaList();
            List<Receta> recetaListNew = consulta.getRecetaList();
            List<DetalleDiagnostico> detalleDiagnosticoListOld = persistentConsulta.getDetalleDiagnosticoList();
            List<DetalleDiagnostico> detalleDiagnosticoListNew = consulta.getDetalleDiagnosticoList();
            List<String> illegalOrphanMessages = null;
            for (Receta recetaListOldReceta : recetaListOld) {
                if (!recetaListNew.contains(recetaListOldReceta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Receta " + recetaListOldReceta + " since its idConsulta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPacienteNew != null) {
                idPacienteNew = em.getReference(idPacienteNew.getClass(), idPacienteNew.getIdPaciente());
                consulta.setIdPaciente(idPacienteNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                consulta.setIdUsuario(idUsuarioNew);
            }
            List<Receta> attachedRecetaListNew = new ArrayList<Receta>();
            for (Receta recetaListNewRecetaToAttach : recetaListNew) {
                recetaListNewRecetaToAttach = em.getReference(recetaListNewRecetaToAttach.getClass(), recetaListNewRecetaToAttach.getNumReceta());
                attachedRecetaListNew.add(recetaListNewRecetaToAttach);
            }
            recetaListNew = attachedRecetaListNew;
            consulta.setRecetaList(recetaListNew);
            List<DetalleDiagnostico> attachedDetalleDiagnosticoListNew = new ArrayList<DetalleDiagnostico>();
            for (DetalleDiagnostico detalleDiagnosticoListNewDetalleDiagnosticoToAttach : detalleDiagnosticoListNew) {
                detalleDiagnosticoListNewDetalleDiagnosticoToAttach = em.getReference(detalleDiagnosticoListNewDetalleDiagnosticoToAttach.getClass(), detalleDiagnosticoListNewDetalleDiagnosticoToAttach.getIdDiagnostico());
                attachedDetalleDiagnosticoListNew.add(detalleDiagnosticoListNewDetalleDiagnosticoToAttach);
            }
            detalleDiagnosticoListNew = attachedDetalleDiagnosticoListNew;
            consulta.setDetalleDiagnosticoList(detalleDiagnosticoListNew);
            consulta = em.merge(consulta);
            if (idPacienteOld != null && !idPacienteOld.equals(idPacienteNew)) {
                idPacienteOld.getConsultaList().remove(consulta);
                idPacienteOld = em.merge(idPacienteOld);
            }
            if (idPacienteNew != null && !idPacienteNew.equals(idPacienteOld)) {
                idPacienteNew.getConsultaList().add(consulta);
                idPacienteNew = em.merge(idPacienteNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getConsultaList().remove(consulta);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getConsultaList().add(consulta);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (Receta recetaListNewReceta : recetaListNew) {
                if (!recetaListOld.contains(recetaListNewReceta)) {
                    Consulta oldIdConsultaOfRecetaListNewReceta = recetaListNewReceta.getIdConsulta();
                    recetaListNewReceta.setIdConsulta(consulta);
                    recetaListNewReceta = em.merge(recetaListNewReceta);
                    if (oldIdConsultaOfRecetaListNewReceta != null && !oldIdConsultaOfRecetaListNewReceta.equals(consulta)) {
                        oldIdConsultaOfRecetaListNewReceta.getRecetaList().remove(recetaListNewReceta);
                        oldIdConsultaOfRecetaListNewReceta = em.merge(oldIdConsultaOfRecetaListNewReceta);
                    }
                }
            }
            for (DetalleDiagnostico detalleDiagnosticoListOldDetalleDiagnostico : detalleDiagnosticoListOld) {
                if (!detalleDiagnosticoListNew.contains(detalleDiagnosticoListOldDetalleDiagnostico)) {
                    detalleDiagnosticoListOldDetalleDiagnostico.setIdConsulta(null);
                    detalleDiagnosticoListOldDetalleDiagnostico = em.merge(detalleDiagnosticoListOldDetalleDiagnostico);
                }
            }
            for (DetalleDiagnostico detalleDiagnosticoListNewDetalleDiagnostico : detalleDiagnosticoListNew) {
                if (!detalleDiagnosticoListOld.contains(detalleDiagnosticoListNewDetalleDiagnostico)) {
                    Consulta oldIdConsultaOfDetalleDiagnosticoListNewDetalleDiagnostico = detalleDiagnosticoListNewDetalleDiagnostico.getIdConsulta();
                    detalleDiagnosticoListNewDetalleDiagnostico.setIdConsulta(consulta);
                    detalleDiagnosticoListNewDetalleDiagnostico = em.merge(detalleDiagnosticoListNewDetalleDiagnostico);
                    if (oldIdConsultaOfDetalleDiagnosticoListNewDetalleDiagnostico != null && !oldIdConsultaOfDetalleDiagnosticoListNewDetalleDiagnostico.equals(consulta)) {
                        oldIdConsultaOfDetalleDiagnosticoListNewDetalleDiagnostico.getDetalleDiagnosticoList().remove(detalleDiagnosticoListNewDetalleDiagnostico);
                        oldIdConsultaOfDetalleDiagnosticoListNewDetalleDiagnostico = em.merge(oldIdConsultaOfDetalleDiagnosticoListNewDetalleDiagnostico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consulta.getIdConsulta();
                if (findConsulta(id) == null) {
                    throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.");
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
            Consulta consulta;
            try {
                consulta = em.getReference(Consulta.class, id);
                consulta.getIdConsulta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Receta> recetaListOrphanCheck = consulta.getRecetaList();
            for (Receta recetaListOrphanCheckReceta : recetaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Consulta (" + consulta + ") cannot be destroyed since the Receta " + recetaListOrphanCheckReceta + " in its recetaList field has a non-nullable idConsulta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Paciente idPaciente = consulta.getIdPaciente();
            if (idPaciente != null) {
                idPaciente.getConsultaList().remove(consulta);
                idPaciente = em.merge(idPaciente);
            }
            Usuario idUsuario = consulta.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getConsultaList().remove(consulta);
                idUsuario = em.merge(idUsuario);
            }
            List<DetalleDiagnostico> detalleDiagnosticoList = consulta.getDetalleDiagnosticoList();
            for (DetalleDiagnostico detalleDiagnosticoListDetalleDiagnostico : detalleDiagnosticoList) {
                detalleDiagnosticoListDetalleDiagnostico.setIdConsulta(null);
                detalleDiagnosticoListDetalleDiagnostico = em.merge(detalleDiagnosticoListDetalleDiagnostico);
            }
            em.remove(consulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consulta> findConsultaEntities() {
        return findConsultaEntities(true, -1, -1);
    }

    public List<Consulta> findConsultaEntities(int maxResults, int firstResult) {
        return findConsultaEntities(false, maxResults, firstResult);
    }

    private List<Consulta> findConsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consulta.class));
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

    public Consulta findConsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consulta> rt = cq.from(Consulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
