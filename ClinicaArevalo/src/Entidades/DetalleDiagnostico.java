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
    @NamedQuery(name = "DetalleDiagnostico.findbyIdConsulta", query = "SELECT d FROM DetalleDiagnostico d WHERE d.idDiagnostico=:idconsulta")})
public class DetalleDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_diagnostico")
    private Integer idDetalleDiagnostico;
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "id_Diagnostico", referencedColumnName = "id_Diagnostico")
    @ManyToOne
    private Diagnostico idDiagnostico;
    @JoinColumn(name = "id_Enfermedad", referencedColumnName = "id")
    @ManyToOne
    private EnfermedadesCie10 idEnfermedad;

    public DetalleDiagnostico() {
    }

    public DetalleDiagnostico(Integer idDetalleDiagnostico) {
        this.idDetalleDiagnostico = idDetalleDiagnostico;
    }

    public Integer getIdDetalleDiagnostico() {
        return idDetalleDiagnostico;
    }

    public void setIdDetalleDiagnostico(Integer idDetalleDiagnostico) {
        this.idDetalleDiagnostico = idDetalleDiagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Diagnostico getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Diagnostico idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
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
        hash += (idDetalleDiagnostico != null ? idDetalleDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleDiagnostico)) {
            return false;
        }
        DetalleDiagnostico other = (DetalleDiagnostico) object;
        if ((this.idDetalleDiagnostico == null && other.idDetalleDiagnostico != null) || (this.idDetalleDiagnostico != null && !this.idDetalleDiagnostico.equals(other.idDetalleDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DetalleDiagnostico[ idDetalleDiagnostico=" + idDetalleDiagnostico + " ]";
    }
    
}
