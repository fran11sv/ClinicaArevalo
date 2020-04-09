package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Receta {
    
    int num_receta;
    int id_consulta;
    String indicaciones;

    public Receta(int num_receta, int id_consulta, String indicaciones) {
        this.num_receta = num_receta;
        this.id_consulta = id_consulta;
        this.indicaciones = indicaciones;
    }

    public Receta(int id_consulta, String indicaciones) {
        this.id_consulta = id_consulta;
        this.indicaciones = indicaciones;
    }

    public int getNum_receta() {
        return num_receta;
    }

    public void setNum_receta(int num_receta) {
        this.num_receta = num_receta;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    @Override
    public String toString() {
        return "Receta{" + "num_receta=" + num_receta + ", id_consulta=" + id_consulta + ", indicaciones=" + indicaciones + '}';
    }
        
}
