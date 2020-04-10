
package Entidades;

import java.sql.Date;

/**
 *
 * @author BASE CC
 */
public class Paciente {
    int idPaciente;
    String Nombres;
    String Apellidos;
    String Direccion;
    String DUI;
    Date FechaNac;
    String Ocupacion;
    String Sexo;

    public Paciente() {
    }


    public Paciente(int idPaciente, String Nombres, String Apellidos, String Direccion, String DUI, Date FechaNac, String Ocupacion, String Sexo) {
        this.idPaciente = idPaciente;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Direccion = Direccion;
        this.DUI = DUI;
        this.FechaNac = FechaNac;
        this.Ocupacion = Ocupacion;
        this.Sexo = Sexo;
    }

    public Paciente(String Nombres, String Apellidos, String Direccion, String DUI, Date FechaNac, String Ocupacion, String Sexo) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Direccion = Direccion;
        this.DUI = DUI;
        this.FechaNac = FechaNac;
        this.Ocupacion = Ocupacion;
        this.Sexo = Sexo;
    }
    
    

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getDUI() {
        return DUI;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public Date getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(Date FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String Ocupacion) {
        this.Ocupacion = Ocupacion;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", Nombres=" + Nombres + ", Apellidos=" + Apellidos + ", Direccion=" + Direccion + ", DUI=" + DUI + ", FechaNac=" + FechaNac + ", Ocupacion=" + Ocupacion + ", Sexo=" + Sexo + '}';
    }

    
    
    
}
