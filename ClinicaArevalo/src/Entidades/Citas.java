package Entidades;
import java.sql.Date;

/**
 *
 * @author BASE CCDIT
 */
public class Citas {
    
    int id_citas;
    int id_paciente;
    int id_usuario;
    Date fecha_cita;

    public Citas(int id_citas, int id_paciente, int id_usuario, Date fecha_cita) {
        this.id_citas = id_citas;
        this.id_paciente = id_paciente;
        this.id_usuario = id_usuario;
        this.fecha_cita = fecha_cita;
    }

    public Citas(int id_paciente, int id_usuario, Date fecha_cita) {
        this.id_paciente = id_paciente;
        this.id_usuario = id_usuario;
        this.fecha_cita = fecha_cita;
    }

    public Citas() {
        
    }
    
    public int getId_citas() {
        return id_citas;
    }

    public void setId_citas(int id_citas) {
        this.id_citas = id_citas;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    @Override
    public String toString() {
        return "Citas{" + "id_citas=" + id_citas + ", id_paciente=" + id_paciente + ", id_usuario=" + id_usuario + ", fecha_cita=" + fecha_cita + '}';
    }
       
}
