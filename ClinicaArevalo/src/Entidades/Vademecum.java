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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author babef
 */
@Entity
@Table(name = "Vademecum")
@NamedQueries({
    @NamedQuery(name = "Vademecum.findAll", query = "SELECT v FROM Vademecum v")})
public class Vademecum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_insumo")
    private Integer idInsumo;
    @Basic(optional = false)
    @Column(name = "Principio_activo")
    private String principioactivo;
    @Column(name = "Accion_terapeutica")
    private String accionterapeutica;
    @Column(name = "Indicaciones")
    private String indicaciones;
    @Column(name = "Marcas")
    private String marcas;
    @Column(name = "Propiedades")
    private String propiedades;
    @Column(name = "Precauciones")
    private String precauciones;
    @Column(name = "Dosificacion")
    private String dosificacion;
    @Column(name = "Reacciones_adversas")
    private String reaccionesadversas;
    @Column(name = "Interacciones")
    private String interacciones;
    @Column(name = "Contraindicaciones")
    private String contraindicaciones;
    @Column(name = "Sobredosificacion")
    private String sobredosificacion;
    @OneToMany(mappedBy = "idInsumo")
    private List<DetalleReceta> detalleRecetaList;

    public Vademecum() {
    }

    public Vademecum(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Vademecum(Integer idInsumo, String principioactivo) {
        this.idInsumo = idInsumo;
        this.principioactivo = principioactivo;
    }

    public Integer getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getPrincipioactivo() {
        return principioactivo;
    }

    public void setPrincipioactivo(String principioactivo) {
        this.principioactivo = principioactivo;
    }

    public String getAccionterapeutica() {
        return accionterapeutica;
    }

    public void setAccionterapeutica(String accionterapeutica) {
        this.accionterapeutica = accionterapeutica;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
    }

    public String getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(String propiedades) {
        this.propiedades = propiedades;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }

    public String getDosificacion() {
        return dosificacion;
    }

    public void setDosificacion(String dosificacion) {
        this.dosificacion = dosificacion;
    }

    public String getReaccionesadversas() {
        return reaccionesadversas;
    }

    public void setReaccionesadversas(String reaccionesadversas) {
        this.reaccionesadversas = reaccionesadversas;
    }

    public String getInteracciones() {
        return interacciones;
    }

    public void setInteracciones(String interacciones) {
        this.interacciones = interacciones;
    }

    public String getContraindicaciones() {
        return contraindicaciones;
    }

    public void setContraindicaciones(String contraindicaciones) {
        this.contraindicaciones = contraindicaciones;
    }

    public String getSobredosificacion() {
        return sobredosificacion;
    }

    public void setSobredosificacion(String sobredosificacion) {
        this.sobredosificacion = sobredosificacion;
    }

    public List<DetalleReceta> getDetalleRecetaList() {
        return detalleRecetaList;
    }

    public void setDetalleRecetaList(List<DetalleReceta> detalleRecetaList) {
        this.detalleRecetaList = detalleRecetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumo != null ? idInsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vademecum)) {
            return false;
        }
        Vademecum other = (Vademecum) object;
        if ((this.idInsumo == null && other.idInsumo != null) || (this.idInsumo != null && !this.idInsumo.equals(other.idInsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Vademecum[ idInsumo=" + idInsumo + " ]";
    }
    
}
