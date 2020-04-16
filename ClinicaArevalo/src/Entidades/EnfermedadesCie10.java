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
@Table(name = "EnfermedadesCie10")
@NamedQueries({
    @NamedQuery(name = "EnfermedadesCie10.findAll", query = "SELECT e FROM EnfermedadesCie10 e")})
public class EnfermedadesCie10 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoriasCie10 idCategoria;
    @OneToMany(mappedBy = "idEnfermedad")
    private List<DetalleDiagnostico> detalleDiagnosticoList;

    public EnfermedadesCie10() {
    }

    public EnfermedadesCie10(Integer id) {
        this.id = id;
    }

    public EnfermedadesCie10(Integer id, String clave, String descripcion) {
        this.id = id;
        this.clave = clave;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriasCie10 getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriasCie10 idCategoria) {
        this.idCategoria = idCategoria;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnfermedadesCie10)) {
            return false;
        }
        EnfermedadesCie10 other = (EnfermedadesCie10) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.EnfermedadesCie10[ id=" + id + " ]";
    }
    
}
