package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Enfermedad {
    
    int id_enfermedad;
    int id_categoriascie;
    String clave_categoria;
    String descripcion_cat;

    public Enfermedad(int id_enfermedad, int id_categoriascie, String clave_categoria, String descripcion_cat) {
        this.id_enfermedad = id_enfermedad;
        this.id_categoriascie = id_categoriascie;
        this.clave_categoria = clave_categoria;
        this.descripcion_cat = descripcion_cat;
    }

    public Enfermedad(int id_categoriascie, String clave_categoria, String descripcion_cat) {
        this.id_categoriascie = id_categoriascie;
        this.clave_categoria = clave_categoria;
        this.descripcion_cat = descripcion_cat;
    }

    public Enfermedad() {
    }

    public int getId_enfermedad() {
        return id_enfermedad;
    }

    public void setId_enfermedad(int id_enfermedad) {
        this.id_enfermedad = id_enfermedad;
    }

    public int getId_categoriascie() {
        return id_categoriascie;
    }

    public void setId_categoriascie(int id_categoriascie) {
        this.id_categoriascie = id_categoriascie;
    }

    public String getClave_categoria() {
        return clave_categoria;
    }

    public void setClave_categoria(String clave_categoria) {
        this.clave_categoria = clave_categoria;
    }

    public String getDescripcion_cat() {
        return descripcion_cat;
    }

    public void setDescripcion_cat(String descripcion_cat) {
        this.descripcion_cat = descripcion_cat;
    }

    @Override
    public String toString() {
        return "Enfermedad{" + "id_enfermedad=" + id_enfermedad + ", id_categoriascie=" + id_categoriascie + ", clave_categoria=" + clave_categoria + ", descripcion_cat=" + descripcion_cat + '}';
    }
  
}
