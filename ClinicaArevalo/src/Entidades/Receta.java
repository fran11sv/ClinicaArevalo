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
 * @author babef
 */
@Entity
@Table(name = "Receta")
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r")})
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Num_Receta")
    private Integer numReceta;
    @Column(name = "Indicaciones")
    private String indicaciones;
    @OneToMany(mappedBy = "numReceta")
    private List<DetalleReceta> detalleRecetaList;
    @JoinColumn(name = "id_Consulta", referencedColumnName = "id_Consulta")
    @ManyToOne(optional = false)
    private Consulta idConsulta;

    public Receta() {
    }

    public Receta(Integer numReceta) {
        this.numReceta = numReceta;
    }

    public Integer getNumReceta() {
        return numReceta;
    }

    public void setNumReceta(Integer numReceta) {
        this.numReceta = numReceta;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public List<DetalleReceta> getDetalleRecetaList() {
        return detalleRecetaList;
    }

    public void setDetalleRecetaList(List<DetalleReceta> detalleRecetaList) {
        this.detalleRecetaList = detalleRecetaList;
    }

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numReceta != null ? numReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.numReceta == null && other.numReceta != null) || (this.numReceta != null && !this.numReceta.equals(other.numReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Receta[ numReceta=" + numReceta + " ]";
    }
    
}
