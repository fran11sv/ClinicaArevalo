/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "subGruposCie10")
@NamedQueries({
    @NamedQuery(name = "SubGruposCie10.findAll", query = "SELECT s FROM SubGruposCie10 s")})
public class SubGruposCie10 implements Serializable {

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
    @JoinColumn(name = "idGrupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GruposCie10 idGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubGrupo")
    private List<CategoriasCie10> categoriasCie10List;

    public SubGruposCie10() {
    }

    public SubGruposCie10(Integer id) {
        this.id = id;
    }

    public SubGruposCie10(Integer id, String clave, String descripcion) {
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

    public GruposCie10 getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(GruposCie10 idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<CategoriasCie10> getCategoriasCie10List() {
        return categoriasCie10List;
    }

    public void setCategoriasCie10List(List<CategoriasCie10> categoriasCie10List) {
        this.categoriasCie10List = categoriasCie10List;
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
        if (!(object instanceof SubGruposCie10)) {
            return false;
        }
        SubGruposCie10 other = (SubGruposCie10) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.SubGruposCie10[ id=" + id + " ]";
    }
    
}
