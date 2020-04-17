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
@Table(name = "Detalle_Receta")
@NamedQueries({
    @NamedQuery(name = "DetalleReceta.findAll", query = "SELECT d FROM DetalleReceta d"),
    @NamedQuery(name = "DetalleReceta.findbyNumReceta", query = "SELECT d FROM DetalleReceta d WHERE d.numReceta=:numreceta")})
public class DetalleReceta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Detalle_Receta")
    private Integer detalleReceta;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Column(name = "Dosis")
    private String dosis;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne
    private Vademecum idInsumo;
    @JoinColumn(name = "Num_Receta", referencedColumnName = "Num_Receta")
    @ManyToOne
    private Receta numReceta;

    public DetalleReceta() {
    }

    public DetalleReceta(Integer detalleReceta) {
        this.detalleReceta = detalleReceta;
    }

    public Integer getDetalleReceta() {
        return detalleReceta;
    }

    public void setDetalleReceta(Integer detalleReceta) {
        this.detalleReceta = detalleReceta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Vademecum getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Vademecum idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Receta getNumReceta() {
        return numReceta;
    }

    public void setNumReceta(Receta numReceta) {
        this.numReceta = numReceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleReceta != null ? detalleReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleReceta)) {
            return false;
        }
        DetalleReceta other = (DetalleReceta) object;
        if ((this.detalleReceta == null && other.detalleReceta != null) || (this.detalleReceta != null && !this.detalleReceta.equals(other.detalleReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DetalleReceta[ detalleReceta=" + detalleReceta + " ]";
    }
    
}
