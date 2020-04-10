package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Detalle_Diagnostico {
    
    int id_diagnostico;
    int id_enfermedad;
    String descripcion;
    String clave;

    public Detalle_Diagnostico(int id_diagnostico, int id_enfermedad, String descripcion, String clave) {
        this.id_diagnostico = id_diagnostico;
        this.id_enfermedad = id_enfermedad;
        this.descripcion = descripcion;
        this.clave = clave;
    }

    public Detalle_Diagnostico(int id_enfermedad, String descripcion, String clave) {
        this.id_enfermedad = id_enfermedad;
        this.descripcion = descripcion;
        this.clave = clave;
    }

    public Detalle_Diagnostico() {
    }

    public int getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(int id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    public int getId_enfermedad() {
        return id_enfermedad;
    }

    public void setId_enfermedad(int id_enfermedad) {
        this.id_enfermedad = id_enfermedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Detalle_Diagnostico{" + "id_diagnostico=" + id_diagnostico + ", id_enfermedad=" + id_enfermedad + ", descripcion=" + descripcion + ", clave=" + clave + '}';
    }
       
}
