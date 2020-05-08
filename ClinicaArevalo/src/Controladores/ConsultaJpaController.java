/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Consulta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Paciente;
import Entidades.Usuario;
import Entidades.Diagnostico;
import java.util.ArrayList;
import java.util.List;
import Entidades.Receta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
        if (consulta.getDiagnosticoList() == null) {
            consulta.setDiagnosticoList(new ArrayList<Diagnostico>());
        }
        if (consulta.getRecetaList() == null) {
            consulta.setRecetaList(new ArrayList<Receta>());
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
            List<Diagnostico> attachedDiagnosticoList = new ArrayList<Diagnostico>();
            for (Diagnostico diagnosticoListDiagnosticoToAttach : consulta.getDiagnosticoList()) {
                diagnosticoListDiagnosticoToAttach = em.getReference(diagnosticoListDiagnosticoToAttach.getClass(), diagnosticoListDiagnosticoToAttach.getIdDiagnostico());
                attachedDiagnosticoList.add(diagnosticoListDiagnosticoToAttach);
            }
            consulta.setDiagnosticoList(attachedDiagnosticoList);
            List<Receta> attachedRecetaList = new ArrayList<Receta>();
            for (Receta recetaListRecetaToAttach : consulta.getRecetaList()) {
                recetaListRecetaToAttach = em.getReference(recetaListRecetaToAttach.getClass(), recetaListRecetaToAttach.getNumReceta());
                attachedRecetaList.add(recetaListRecetaToAttach);
            }
            consulta.setRecetaList(attachedRecetaList);
            em.persist(consulta);
            if (idPaciente != null) {
                idPaciente.getConsultaList().add(consulta);
                idPaciente = em.merge(idPaciente);
            }
            if (idUsuario != null) {
                idUsuario.getConsultaList().add(consulta);
                idUsuario = em.merge(idUsuario);
            }
            for (Diagnostico diagnosticoListDiagnostico : consulta.getDiagnosticoList()) {
                Consulta oldIdConsultasOfDiagnosticoListDiagnostico = diagnosticoListDiagnostico.getIdConsultas();
                diagnosticoListDiagnostico.setIdConsultas(consulta);
                diagnosticoListDiagnostico = em.merge(diagnosticoListDiagnostico);
                if (oldIdConsultasOfDiagnosticoListDiagnostico != null) {
                    oldIdConsultasOfDiagnosticoListDiagnostico.getDiagnosticoList().remove(diagnosticoListDiagnostico);
                    oldIdConsultasOfDiagnosticoListDiagnostico = em.merge(oldIdConsultasOfDiagnosticoListDiagnostico);
                }
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
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consulta consulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consulta persistentConsulta = em.find(Consulta.class, consulta.getIdConsulta());
            Paciente idPacienteOld = persistentConsulta.getIdPaciente();
            Paciente idPacienteNew = consulta.getIdPaciente();
            Usuario idUsuarioOld = persistentConsulta.getIdUsuario();
            Usuario idUsuarioNew = consulta.getIdUsuario();
            List<Diagnostico> diagnosticoListOld = persistentConsulta.getDiagnosticoList();
            List<Diagnostico> diagnosticoListNew = consulta.getDiagnosticoList();
            List<Receta> recetaListOld = persistentConsulta.getRecetaList();
            List<Receta> recetaListNew = consulta.getRecetaList();
            if (idPacienteNew != null) {
                idPacienteNew = em.getReference(idPacienteNew.getClass(), idPacienteNew.getIdPaciente());
                consulta.setIdPaciente(idPacienteNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                consulta.setIdUsuario(idUsuarioNew);
            }
            List<Diagnostico> attachedDiagnosticoListNew = new ArrayList<Diagnostico>();
            for (Diagnostico diagnosticoListNewDiagnosticoToAttach : diagnosticoListNew) {
                diagnosticoListNewDiagnosticoToAttach = em.getReference(diagnosticoListNewDiagnosticoToAttach.getClass(), diagnosticoListNewDiagnosticoToAttach.getIdDiagnostico());
                attachedDiagnosticoListNew.add(diagnosticoListNewDiagnosticoToAttach);
            }
            diagnosticoListNew = attachedDiagnosticoListNew;
            consulta.setDiagnosticoList(diagnosticoListNew);
            List<Receta> attachedRecetaListNew = new ArrayList<Receta>();
            for (Receta recetaListNewRecetaToAttach : recetaListNew) {
                recetaListNewRecetaToAttach = em.getReference(recetaListNewRecetaToAttach.getClass(), recetaListNewRecetaToAttach.getNumReceta());
                attachedRecetaListNew.add(recetaListNewRecetaToAttach);
            }
            recetaListNew = attachedRecetaListNew;
            consulta.setRecetaList(recetaListNew);
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
            for (Diagnostico diagnosticoListOldDiagnostico : diagnosticoListOld) {
                if (!diagnosticoListNew.contains(diagnosticoListOldDiagnostico)) {
                    diagnosticoListOldDiagnostico.setIdConsultas(null);
                    diagnosticoListOldDiagnostico = em.merge(diagnosticoListOldDiagnostico);
                }
            }
            for (Diagnostico diagnosticoListNewDiagnostico : diagnosticoListNew) {
                if (!diagnosticoListOld.contains(diagnosticoListNewDiagnostico)) {
                    Consulta oldIdConsultasOfDiagnosticoListNewDiagnostico = diagnosticoListNewDiagnostico.getIdConsultas();
                    diagnosticoListNewDiagnostico.setIdConsultas(consulta);
                    diagnosticoListNewDiagnostico = em.merge(diagnosticoListNewDiagnostico);
                    if (oldIdConsultasOfDiagnosticoListNewDiagnostico != null && !oldIdConsultasOfDiagnosticoListNewDiagnostico.equals(consulta)) {
                        oldIdConsultasOfDiagnosticoListNewDiagnostico.getDiagnosticoList().remove(diagnosticoListNewDiagnostico);
                        oldIdConsultasOfDiagnosticoListNewDiagnostico = em.merge(oldIdConsultasOfDiagnosticoListNewDiagnostico);
                    }
                }
            }
            for (Receta recetaListOldReceta : recetaListOld) {
                if (!recetaListNew.contains(recetaListOldReceta)) {
                    recetaListOldReceta.setIdConsulta(null);
                    recetaListOldReceta = em.merge(recetaListOldReceta);
                }
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            List<Diagnostico> diagnosticoList = consulta.getDiagnosticoList();
            for (Diagnostico diagnosticoListDiagnostico : diagnosticoList) {
                diagnosticoListDiagnostico.setIdConsultas(null);
                diagnosticoListDiagnostico = em.merge(diagnosticoListDiagnostico);
            }
            List<Receta> recetaList = consulta.getRecetaList();
            for (Receta recetaListReceta : recetaList) {
                recetaListReceta.setIdConsulta(null);
                recetaListReceta = em.merge(recetaListReceta);
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
    public List<Consulta> findbyIdPacienteDESC (Paciente id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Consulta> query=em.createNamedQuery("Consulta.findbyIdPacienteDESC",Consulta.class);
            query.setParameter("idpaciente",id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
