package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class CategoriaCie {
    
    int id_categoriacie;
    int id_subgrupo;
    String clave_grupo;
    String descripcion_grupo;

    public CategoriaCie(int id_categoriacie, int id_subgrupo, String clave_grupo, String descripcion_grupo) {
        this.id_categoriacie = id_categoriacie;
        this.id_subgrupo = id_subgrupo;
        this.clave_grupo = clave_grupo;
        this.descripcion_grupo = descripcion_grupo;
    }

    public CategoriaCie(int id_subgrupo, String clave_grupo, String descripcion_grupo) {
        this.id_subgrupo = id_subgrupo;
        this.clave_grupo = clave_grupo;
        this.descripcion_grupo = descripcion_grupo;
    }

    public CategoriaCie() {
        
    }
    
    public int getId_categoriacie() {
        return id_categoriacie;
    }

    public void setId_categoriacie(int id_categoriacie) {
        this.id_categoriacie = id_categoriacie;
    }

    public int getId_subgrupo() {
        return id_subgrupo;
    }

    public void setId_subgrupo(int id_subgrupo) {
        this.id_subgrupo = id_subgrupo;
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
        return "CategoriaCie{" + "id_categoriacie=" + id_categoriacie + ", id_subgrupo=" + id_subgrupo + ", clave_grupo=" + clave_grupo + ", descripcion_grupo=" + descripcion_grupo + '}';
    }
        
}
