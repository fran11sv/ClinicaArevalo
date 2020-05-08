/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author franb
 */
@Entity
@Table(name = "Diagnostico")
@NamedQueries({
    @NamedQuery(name = "Diagnostico.findAll", query = "SELECT d FROM Diagnostico d"),
    @NamedQuery(name = "Diagnostico.findbyDESC", query = "SELECT d FROM Diagnostico d ORDER BY d.idDiagnostico DESC")})
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Diagnostico")
    private Integer idDiagnostico;
    @JoinColumn(name = "id_Consultas", referencedColumnName = "id_Consulta")
    @ManyToOne
    private Consulta idConsultas;
    @OneToMany(mappedBy = "idDiagnostico")
    private List<DetalleDiagnostico> detalleDiagnosticoList;

    public Diagnostico() {
    }

    public Diagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Consulta getIdConsultas() {
        return idConsultas;
    }

    public void setIdConsultas(Consulta idConsultas) {
        this.idConsultas = idConsultas;
    }

    public List<DetalleDiagnostico> getDetalleDiagnosticoList() {
        return detalleDiagnosticoList;
    }

    public void setDetalleDiagnosticoList(List<DetalleDiagnostico> detalleDiagnosticoList) {
        this.detalleDiagnosticoList = detalleDiagnosticoList;
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
        if (!(object instanceof Diagnostico)) {
            return false;
        }
        Diagnostico other = (Diagnostico) object;
        if ((this.idDiagnostico == null && other.idDiagnostico != null) || (this.idDiagnostico != null && !this.idDiagnostico.equals(other.idDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Diagnostico[ idDiagnostico=" + idDiagnostico + " ]";
    }
    
}
