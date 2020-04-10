package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class GruposCIe10 {
    
    int id_grupo;
    String clave_grupo;
    String descripcion_grupo;

    public GruposCIe10(int id_grupo, String clave_grupo, String descripcion_grupo) {
        this.id_grupo = id_grupo;
        this.clave_grupo = clave_grupo;
        this.descripcion_grupo = descripcion_grupo;
    }

    public GruposCIe10(String clave_grupo, String descripcion_grupo) {
        this.clave_grupo = clave_grupo;
        this.descripcion_grupo = descripcion_grupo;
    }

    public GruposCIe10() {
        
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getClave_grupo() {
        return clave_grupo;
    }

    public void setClave_grupo(String clave_grupo) {
        this.clave_grupo = clave_grupo;
    }

    public String getDescripcion_grupo() {
        return descripcion_grupo;
    }

    public void setDescripcion_grupo(String descripcion_grupo) {
        this.descripcion_grupo = descripcion_grupo;
    }

    @Override
    public String toString() {
        return "GruposCIe10{" + "id_grupo=" + id_grupo + ", clave_grupo=" + clave_grupo + ", descripcion_grupo=" + descripcion_grupo + '}';
    }
        
}
