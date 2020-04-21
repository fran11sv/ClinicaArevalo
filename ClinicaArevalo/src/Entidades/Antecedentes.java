/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author franb
 */
@Entity
@Table(name = "Antecedentes")
@NamedQueries({
    @NamedQuery(name = "Antecedentes.findAll", query = "SELECT a FROM Antecedentes a"),
    @NamedQuery(name = "Antecedentes.findbyIdPaciente", query = "SELECT a FROM Antecedentes a WHERE a.idPaciente=:numero")})
public class Antecedentes implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Antecedentes")
    private Integer idAntecedentes;
    @Column(name = "Familiares")
    private String familiares;
    @Column(name = "Personales")
    private String personales;
    @JoinColumn(name = "id_Paciente", referencedColumnName = "id_Paciente")
    @ManyToOne(optional = false)
    private Paciente idPaciente;

    public Antecedentes() {
    }

    public Antecedentes(Integer idAntecedentes) {
        this.idAntecedentes = idAntecedentes;
    }

    public Integer getIdAntecedentes() {
        return idAntecedentes;
    }

    public void setIdAntecedentes(Integer idAntecedentes) {
        Integer oldIdAntecedentes = this.idAntecedentes;
        this.idAntecedentes = idAntecedentes;
        changeSupport.firePropertyChange("idAntecedentes", oldIdAntecedentes, idAntecedentes);
    }

    public String getFamiliares() {
        return familiares;
    }

    public void setFamiliares(String familiares) {
        String oldFamiliares = this.familiares;
        this.familiares = familiares;
        changeSupport.firePropertyChange("familiares", oldFamiliares, familiares);
    }

    public String getPersonales() {
        return personales;
    }

    public void setPersonales(String personales) {
        String oldPersonales = this.personales;
        this.personales = personales;
        changeSupport.firePropertyChange("personales", oldPersonales, personales);
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        Paciente oldIdPaciente = this.idPaciente;
        this.idPaciente = idPaciente;
        changeSupport.firePropertyChange("idPaciente", oldIdPaciente, idPaciente);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntecedentes != null ? idAntecedentes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Antecedentes)) {
            return false;
        }
        Antecedentes other = (Antecedentes) object;
        if ((this.idAntecedentes == null && other.idAntecedentes != null) || (this.idAntecedentes != null && !this.idAntecedentes.equals(other.idAntecedentes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Antecedentes[ idAntecedentes=" + idAntecedentes + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
