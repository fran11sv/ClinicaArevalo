package Entidades;
import java.sql.Date;

/**
 *
 * @author BASE CCDIT
 */

public class Consulta {
    
    int id_consulta;
    int id_usuario;
    int id_paciente;
    int id_diagnostico;
    Date fecha_consulta;
    String observaciones;
    String motivo_de_consulta;
    double presion_arterial;
    int frecuencia_cardiaca;
    int frecuencia_respiratoria;
    double temperatura;
    double peso;
    double talla;

    public Consulta(int id_consulta, int id_usuario, int id_paciente, int id_diagnostico, Date fecha_consulta, String observaciones, String motivo_de_consulta, double presion_arterial, int frecuencia_cardiaca, int frecuencia_respiratoria, double temperatura, double peso, double talla) {
        this.id_consulta = id_consulta;
        this.id_usuario = id_usuario;
        this.id_paciente = id_paciente;
        this.id_diagnostico = id_diagnostico;
        this.fecha_consulta = fecha_consulta;
        this.observaciones = observaciones;
        this.motivo_de_consulta = motivo_de_consulta;
        this.presion_arterial = presion_arterial;
        this.frecuencia_cardiaca = frecuencia_cardiaca;
        this.frecuencia_respiratoria = frecuencia_respiratoria;
        this.temperatura = temperatura;
        this.peso = peso;
        this.talla = talla;
    }

    public Consulta(int id_usuario, int id_paciente, int id_diagnostico, Date fecha_consulta, String observaciones, String motivo_de_consulta, double presion_arterial, int frecuencia_cardiaca, int frecuencia_respiratoria, double temperatura, double peso, double talla) {
        this.id_usuario = id_usuario;
        this.id_paciente = id_paciente;
        this.id_diagnostico = id_diagnostico;
        this.fecha_consulta = fecha_consulta;
        this.observaciones = observaciones;
        this.motivo_de_consulta = motivo_de_consulta;
        this.presion_arterial = presion_arterial;
        this.frecuencia_cardiaca = frecuencia_cardiaca;
        this.frecuencia_respiratoria = frecuencia_respiratoria;
        this.temperatura = temperatura;
        this.peso = peso;
        this.talla = talla;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(int id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    public Date getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(Date fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMotivo_de_consulta() {
        return motivo_de_consulta;
    }

    public void setMotivo_de_consulta(String motivo_de_consulta) {
        this.motivo_de_consulta = motivo_de_consulta;
    }

    public double getPresion_arterial() {
        return presion_arterial;
    }

    public void setPresion_arterial(double presion_arterial) {
        this.presion_arterial = presion_arterial;
    }

    public int getFrecuencia_cardiaca() {
        return frecuencia_cardiaca;
    }

    public void setFrecuencia_cardiaca(int frecuencia_cardiaca) {
        this.frecuencia_cardiaca = frecuencia_cardiaca;
    }

    public int getFrecuencia_respiratoria() {
        return frecuencia_respiratoria;
    }

    public void setFrecuencia_respiratoria(int frecuencia_respiratoria) {
        this.frecuencia_respiratoria = frecuencia_respiratoria;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id_consulta=" + id_consulta + ", id_usuario=" + id_usuario + ", id_paciente=" + id_paciente + ", id_diagnostico=" + id_diagnostico + ", fecha_consulta=" + fecha_consulta + ", observaciones=" + observaciones + ", motivo_de_consulta=" + motivo_de_consulta + ", presion_arterial=" + presion_arterial + ", frecuencia_cardiaca=" + frecuencia_cardiaca + ", frecuencia_respiratoria=" + frecuencia_respiratoria + ", temperatura=" + temperatura + ", peso=" + peso + ", talla=" + talla + '}';
    }
 
}
