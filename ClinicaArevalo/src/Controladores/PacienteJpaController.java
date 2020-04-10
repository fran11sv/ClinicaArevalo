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
import Entidades.Citas;
import java.util.ArrayList;
import java.util.List;
import Entidades.Antecedentes;
import Entidades.Consulta;
import Entidades.Paciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author franb
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) {
        if (paciente.getCitasList() == null) {
            paciente.setCitasList(new ArrayList<Citas>());
        }
        if (paciente.getAntecedentesList() == null) {
            paciente.setAntecedentesList(new ArrayList<Antecedentes>());
        }
        if (paciente.getConsultaList() == null) {
            paciente.setConsultaList(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Citas> attachedCitasList = new ArrayList<Citas>();
            for (Citas citasListCitasToAttach : paciente.getCitasList()) {
                citasListCitasToAttach = em.getReference(citasListCitasToAttach.getClass(), citasListCitasToAttach.getIdCita());
                attachedCitasList.add(citasListCitasToAttach);
            }
            paciente.setCitasList(attachedCitasList);
            List<Antecedentes> attachedAntecedentesList = new ArrayList<Antecedentes>();
            for (Antecedentes antecedentesListAntecedentesToAttach : paciente.getAntecedentesList()) {
                antecedentesListAntecedentesToAttach = em.getReference(antecedentesListAntecedentesToAttach.getClass(), antecedentesListAntecedentesToAttach.getIdAntecedentes());
                attachedAntecedentesList.add(antecedentesListAntecedentesToAttach);
            }
            paciente.setAntecedentesList(attachedAntecedentesList);
            List<Consulta> attachedConsultaList = new ArrayList<Consulta>();
            for (Consulta consultaListConsultaToAttach : paciente.getConsultaList()) {
                consultaListConsultaToAttach = em.getReference(consultaListConsultaToAttach.getClass(), consultaListConsultaToAttach.getIdConsulta());
                attachedConsultaList.add(consultaListConsultaToAttach);
            }
            paciente.setConsultaList(attachedConsultaList);
            em.persist(paciente);
            for (Citas citasListCitas : paciente.getCitasList()) {
                Paciente oldIdPacienteOfCitasListCitas = citasListCitas.getIdPaciente();
                citasListCitas.setIdPaciente(paciente);
                citasListCitas = em.merge(citasListCitas);
                if (oldIdPacienteOfCitasListCitas != null) {
                    oldIdPacienteOfCitasListCitas.getCitasList().remove(citasListCitas);
                    oldIdPacienteOfCitasListCitas = em.merge(oldIdPacienteOfCitasListCitas);
                }
            }
            for (Antecedentes antecedentesListAntecedentes : paciente.getAntecedentesList()) {
                Paciente oldIdPacienteOfAntecedentesListAntecedentes = antecedentesListAntecedentes.getIdPaciente();
                antecedentesListAntecedentes.setIdPaciente(paciente);
                antecedentesListAntecedentes = em.merge(antecedentesListAntecedentes);
                if (oldIdPacienteOfAntecedentesListAntecedentes != null) {
                    oldIdPacienteOfAntecedentesListAntecedentes.getAntecedentesList().remove(antecedentesListAntecedentes);
                    oldIdPacienteOfAntecedentesListAntecedentes = em.merge(oldIdPacienteOfAntecedentesListAntecedentes);
                }
            }
            for (Consulta consultaListConsulta : paciente.getConsultaList()) {
                Paciente oldIdPacienteOfConsultaListConsulta = consultaListConsulta.getIdPaciente();
                consultaListConsulta.setIdPaciente(paciente);
                consultaListConsulta = em.merge(consultaListConsulta);
                if (oldIdPacienteOfConsultaListConsulta != null) {
                    oldIdPacienteOfConsultaListConsulta.getConsultaList().remove(consultaListConsulta);
                    oldIdPacienteOfConsultaListConsulta = em.merge(oldIdPacienteOfConsultaListConsulta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getIdPaciente());
            List<Citas> citasListOld = persistentPaciente.getCitasList();
            List<Citas> citasListNew = paciente.getCitasList();
            List<Antecedentes> antecedentesListOld = persistentPaciente.getAntecedentesList();
            List<Antecedentes> antecedentesListNew = paciente.getAntecedentesList();
            List<Consulta> consultaListOld = persistentPaciente.getConsultaList();
            List<Consulta> consultaListNew = paciente.getConsultaList();
            List<String> illegalOrphanMessages = null;
            for (Citas citasListOldCitas : citasListOld) {
                if (!citasListNew.contains(citasListOldCitas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Citas " + citasListOldCitas + " since its idPaciente field is not nullable.");
                }
            }
            for (Antecedentes antecedentesListOldAntecedentes : antecedentesListOld) {
                if (!antecedentesListNew.contains(antecedentesListOldAntecedentes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Antecedentes " + antecedentesListOldAntecedentes + " since its idPaciente field is not nullable.");
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
            paciente.setCitasList(citasListNew);
            List<Antecedentes> attachedAntecedentesListNew = new ArrayList<Antecedentes>();
            for (Antecedentes antecedentesListNewAntecedentesToAttach : antecedentesListNew) {
                antecedentesListNewAntecedentesToAttach = em.getReference(antecedentesListNewAntecedentesToAttach.getClass(), antecedentesListNewAntecedentesToAttach.getIdAntecedentes());
                attachedAntecedentesListNew.add(antecedentesListNewAntecedentesToAttach);
            }
            antecedentesListNew = attachedAntecedentesListNew;
            paciente.setAntecedentesList(antecedentesListNew);
            List<Consulta> attachedConsultaListNew = new ArrayList<Consulta>();
            for (Consulta consultaListNewConsultaToAttach : consultaListNew) {
                consultaListNewConsultaToAttach = em.getReference(consultaListNewConsultaToAttach.getClass(), consultaListNewConsultaToAttach.getIdConsulta());
                attachedConsultaListNew.add(consultaListNewConsultaToAttach);
            }
            consultaListNew = attachedConsultaListNew;
            paciente.setConsultaList(consultaListNew);
            paciente = em.merge(paciente);
            for (Citas citasListNewCitas : citasListNew) {
                if (!citasListOld.contains(citasListNewCitas)) {
                    Paciente oldIdPacienteOfCitasListNewCitas = citasListNewCitas.getIdPaciente();
                    citasListNewCitas.setIdPaciente(paciente);
                    citasListNewCitas = em.merge(citasListNewCitas);
                    if (oldIdPacienteOfCitasListNewCitas != null && !oldIdPacienteOfCitasListNewCitas.equals(paciente)) {
                        oldIdPacienteOfCitasListNewCitas.getCitasList().remove(citasListNewCitas);
                        oldIdPacienteOfCitasListNewCitas = em.merge(oldIdPacienteOfCitasListNewCitas);
                    }
                }
            }
            for (Antecedentes antecedentesListNewAntecedentes : antecedentesListNew) {
                if (!antecedentesListOld.contains(antecedentesListNewAntecedentes)) {
                    Paciente oldIdPacienteOfAntecedentesListNewAntecedentes = antecedentesListNewAntecedentes.getIdPaciente();
                    antecedentesListNewAntecedentes.setIdPaciente(paciente);
                    antecedentesListNewAntecedentes = em.merge(antecedentesListNewAntecedentes);
                    if (oldIdPacienteOfAntecedentesListNewAntecedentes != null && !oldIdPacienteOfAntecedentesListNewAntecedentes.equals(paciente)) {
                        oldIdPacienteOfAntecedentesListNewAntecedentes.getAntecedentesList().remove(antecedentesListNewAntecedentes);
                        oldIdPacienteOfAntecedentesListNewAntecedentes = em.merge(oldIdPacienteOfAntecedentesListNewAntecedentes);
                    }
                }
            }
            for (Consulta consultaListOldConsulta : consultaListOld) {
                if (!consultaListNew.contains(consultaListOldConsulta)) {
                    consultaListOldConsulta.setIdPaciente(null);
                    consultaListOldConsulta = em.merge(consultaListOldConsulta);
                }
            }
            for (Consulta consultaListNewConsulta : consultaListNew) {
                if (!consultaListOld.contains(consultaListNewConsulta)) {
                    Paciente oldIdPacienteOfConsultaListNewConsulta = consultaListNewConsulta.getIdPaciente();
                    consultaListNewConsulta.setIdPaciente(paciente);
                    consultaListNewConsulta = em.merge(consultaListNewConsulta);
                    if (oldIdPacienteOfConsultaListNewConsulta != null && !oldIdPacienteOfConsultaListNewConsulta.equals(paciente)) {
                        oldIdPacienteOfConsultaListNewConsulta.getConsultaList().remove(consultaListNewConsulta);
                        oldIdPacienteOfConsultaListNewConsulta = em.merge(oldIdPacienteOfConsultaListNewConsulta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paciente.getIdPaciente();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getIdPaciente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Citas> citasListOrphanCheck = paciente.getCitasList();
            for (Citas citasListOrphanCheckCitas : citasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Citas " + citasListOrphanCheckCitas + " in its citasList field has a non-nullable idPaciente field.");
            }
            List<Antecedentes> antecedentesListOrphanCheck = paciente.getAntecedentesList();
            for (Antecedentes antecedentesListOrphanCheckAntecedentes : antecedentesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Antecedentes " + antecedentesListOrphanCheckAntecedentes + " in its antecedentesList field has a non-nullable idPaciente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Consulta> consultaList = paciente.getConsultaList();
            for (Consulta consultaListConsulta : consultaList) {
                consultaListConsulta.setIdPaciente(null);
                consultaListConsulta = em.merge(consultaListConsulta);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
