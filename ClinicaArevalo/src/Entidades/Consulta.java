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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author franb
 */
@Entity
@Table(name = "Consulta")
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")})
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Consulta")
    private Integer idConsulta;
    @Column(name = "Fecha_Consulta")
    @Temporal(TemporalType.DATE)
    private Date fechaConsulta;
    @Column(name = "Observaciones")
    private String observaciones;
    @Column(name = "Motivo")
    private String motivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Presion")
    private Double presion;
    @Column(name = "Frecuencia_cardiaca")
    private Integer frecuenciacardiaca;
    @Column(name = "Frecuencia_respiratoria")
    private Integer frecuenciarespiratoria;
    @Column(name = "Temperatura")
    private Double temperatura;
    @Column(name = "Peso")
    private Double peso;
    @Column(name = "Talla")
    private Double talla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsulta")
    private List<Receta> recetaList;
    @JoinColumn(name = "id_Diagnostico", referencedColumnName = "id_Diagnostico")
    @ManyToOne
    private DetalleDiagnostico idDiagnostico;
    @JoinColumn(name = "id_Paciente", referencedColumnName = "id_Paciente")
    @ManyToOne
    private Paciente idPaciente;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Consulta() {
    }

    public Consulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getPresion() {
        return presion;
    }

    public void setPresion(Double presion) {
        this.presion = presion;
    }

    public Integer getFrecuenciacardiaca() {
        return frecuenciacardiaca;
    }

    public void setFrecuenciacardiaca(Integer frecuenciacardiaca) {
        this.frecuenciacardiaca = frecuenciacardiaca;
    }

    public Integer getFrecuenciarespiratoria() {
        return frecuenciarespiratoria;
    }

    public void setFrecuenciarespiratoria(Integer frecuenciarespiratoria) {
        this.frecuenciarespiratoria = frecuenciarespiratoria;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }

    public List<Receta> getRecetaList() {
        return recetaList;
    }

    public void setRecetaList(List<Receta> recetaList) {
        this.recetaList = recetaList;
    }

    public DetalleDiagnostico getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(DetalleDiagnostico idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
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
        hash += (idConsulta != null ? idConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.idConsulta == null && other.idConsulta != null) || (this.idConsulta != null && !this.idConsulta.equals(other.idConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Consulta[ idConsulta=" + idConsulta + " ]";
    }
    
}
