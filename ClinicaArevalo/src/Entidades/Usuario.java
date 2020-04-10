package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Usuario {
    
    int id_usuario;
    String usuario;
    String contraseña;
    String Nombres;
    String Apellidos;
    int JVPM;
    String DUI;
    String NIT;

    public Usuario(int id_usuario, String usuario, String contraseña, String Nombres, String Apellidos, int JVPM, String DUI, String NIT) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.JVPM = JVPM;
        this.DUI = DUI;
        this.NIT = NIT;
    }

    public Usuario(String usuario, String contraseña, String Nombres, String Apellidos, int JVPM, String DUI, String NIT) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.JVPM = JVPM;
        this.DUI = DUI;
        this.NIT = NIT;
    }

    public Usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public int getJVPM() {
        return JVPM;
    }

    public void setJVPM(int JVPM) {
        this.JVPM = JVPM;
    }

    public String getDUI() {
        return DUI;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", Nombres=" + Nombres + ", Apellidos=" + Apellidos + ", JVPM=" + JVPM + ", DUI=" + DUI + ", NIT=" + NIT + '}';
    }
    
}
