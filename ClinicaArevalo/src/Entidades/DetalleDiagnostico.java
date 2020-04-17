/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

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

/**
 *
 * @author franb
 */
@Entity
@Table(name = "Detalle_Diagnostico")
@NamedQueries({
    @NamedQuery(name = "DetalleDiagnostico.findAll", query = "SELECT d FROM DetalleDiagnostico d"),
    @NamedQuery(name = "DetalleDiagnostico.findbyIdConsulta", query = "SELECT d FROM DetalleDiagnostico d WHERE d.idConsulta=:idconsulta")})
public class DetalleDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Diagnostico")
    private Integer idDiagnostico;
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "id_Consulta", referencedColumnName = "id_Consulta")
    @ManyToOne
    private Consulta idConsulta;
    @JoinColumn(name = "id_Enfermedad", referencedColumnName = "id")
    @ManyToOne
    private EnfermedadesCie10 idEnfermedad;

    public DetalleDiagnostico() {
    }

    public DetalleDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    public EnfermedadesCie10 getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(EnfermedadesCie10 idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiagnostico != null ? idDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleDiagnostico)) {
            return false;
        }
        DetalleDiagnostico other = (DetalleDiagnostico) object;
        if ((this.idDiagnostico == null && other.idDiagnostico != null) || (this.idDiagnostico != null && !this.idDiagnostico.equals(other.idDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DetalleDiagnostico[ idDiagnostico=" + idDiagnostico + " ]";
    }
    
}
