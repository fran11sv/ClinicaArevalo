/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author franb
 */
@Entity
@Table(name = "Factura")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findporIdUsuario", query = "SELECT u.usuario FROM Usuario u, Factura f WHERE f.idUsuario = :idUsu"),
    @NamedQuery(name = "Factura.findporEstadoFactura", query = "SELECT f  FROM  Factura f  WHERE f.estadoFactura = 0 ORDER BY f.numFactura DESC"),
    @NamedQuery(name = "Factura.findporFacturaCobrada", query = "SELECT f  FROM  Factura f  WHERE f.estadoFactura = 1  ORDER BY f.numFactura DESC"),
    @NamedQuery(name = "Factura.findbyNumFactura", query = "SELECT f FROM  Factura f WHERE f.numFactura = :numFactura")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Num_Factura")
    private Integer numFactura;
    @Column(name = "Nombre_Cliente")
    private String nombreCliente;
    @Column(name = "Direccion_cliente")
    private String direccioncliente;
    @Basic(optional = false)
    @Column(name = "Fecha_Factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Basic(optional = false)
    @Column(name = "Estado_Factura")
    private int estadoFactura;
    @Column(name = "num_letras")
    private String numLetras;
    @Column(name = "Total")
    private Long total;
    @OneToMany(mappedBy = "numFactura")
    private List<DetalleFactura> detalleFacturaList;
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Factura() {
    }

    public Factura(Integer numFactura) {
        this.numFactura = numFactura;
    }

    public Factura(Integer numFactura, Date fechaFactura, int estadoFactura) {
        this.numFactura = numFactura;
        this.fechaFactura = fechaFactura;
        this.estadoFactura = estadoFactura;
    }

    public Integer getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(Integer numFactura) {
        this.numFactura = numFactura;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(int estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getNumLetras() {
        return numLetras;
    }

    public void setNumLetras(String numLetras) {
        this.numLetras = numLetras;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numFactura != null ? numFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.numFactura == null && other.numFactura != null) || (this.numFactura != null && !this.numFactura.equals(other.numFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Factura[ numFactura=" + numFactura + " ]";
    }
    
}
