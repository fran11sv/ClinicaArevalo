/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Antecedentes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Paciente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author babef
 */
public class AntecedentesJpaController implements Serializable {

    public AntecedentesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Antecedentes antecedentes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente idPaciente = antecedentes.getIdPaciente();
            if (idPaciente != null) {
                idPaciente = em.getReference(idPaciente.getClass(), idPaciente.getIdPaciente());
                antecedentes.setIdPaciente(idPaciente);
            }
            em.persist(antecedentes);
            if (idPaciente != null) {
                idPaciente.getAntecedentesList().add(antecedentes);
                idPaciente = em.merge(idPaciente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Antecedentes antecedentes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Antecedentes persistentAntecedentes = em.find(Antecedentes.class, antecedentes.getIdAntecedentes());
            Paciente idPacienteOld = persistentAntecedentes.getIdPaciente();
            Paciente idPacienteNew = antecedentes.getIdPaciente();
            if (idPacienteNew != null) {
                idPacienteNew = em.getReference(idPacienteNew.getClass(), idPacienteNew.getIdPaciente());
                antecedentes.setIdPaciente(idPacienteNew);
            }
            antecedentes = em.merge(antecedentes);
            if (idPacienteOld != null && !idPacienteOld.equals(idPacienteNew)) {
                idPacienteOld.getAntecedentesList().remove(antecedentes);
                idPacienteOld = em.merge(idPacienteOld);
            }
            if (idPacienteNew != null && !idPacienteNew.equals(idPacienteOld)) {
                idPacienteNew.getAntecedentesList().add(antecedentes);
                idPacienteNew = em.merge(idPacienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = antecedentes.getIdAntecedentes();
                if (findAntecedentes(id) == null) {
                    throw new NonexistentEntityException("The antecedentes with id " + id + " no longer exists.");
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
            Antecedentes antecedentes;
            try {
                antecedentes = em.getReference(Antecedentes.class, id);
                antecedentes.getIdAntecedentes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The antecedentes with id " + id + " no longer exists.", enfe);
            }
            Paciente idPaciente = antecedentes.getIdPaciente();
            if (idPaciente != null) {
                idPaciente.getAntecedentesList().remove(antecedentes);
                idPaciente = em.merge(idPaciente);
            }
            em.remove(antecedentes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Antecedentes> findAntecedentesEntities() {
        return findAntecedentesEntities(true, -1, -1);
    }

    public List<Antecedentes> findAntecedentesEntities(int maxResults, int firstResult) {
        return findAntecedentesEntities(false, maxResults, firstResult);
    }

    private List<Antecedentes> findAntecedentesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Antecedentes.class));
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

    public Antecedentes findAntecedentes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Antecedentes.class, id);
        } finally {
            em.close();
        }
    }

    public int getAntecedentesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Antecedentes> rt = cq.from(Antecedentes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
