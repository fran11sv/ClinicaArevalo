package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Detalle_Receta {
    
    int detalle_receta;
    int num_receta;
    int id_insumo;
    int cantidad_recetada;
    String dosis;

    public Detalle_Receta(int detalle_receta, int num_receta, int id_insumo, int cantidad_recetada, String dosis) {
        this.detalle_receta = detalle_receta;
        this.num_receta = num_receta;
        this.id_insumo = id_insumo;
        this.cantidad_recetada = cantidad_recetada;
        this.dosis = dosis;
    }

    public Detalle_Receta(int num_receta, int id_insumo, int cantidad_recetada, String dosis) {
        this.num_receta = num_receta;
        this.id_insumo = id_insumo;
        this.cantidad_recetada = cantidad_recetada;
        this.dosis = dosis;
    }

    public Detalle_Receta() {
    }  

    public int getDetalle_receta() {
        return detalle_receta;
    }

    public void setDetalle_receta(int detalle_receta) {
        this.detalle_receta = detalle_receta;
    }

    public int getNum_receta() {
        return num_receta;
    }

    public void setNum_receta(int num_receta) {
        this.num_receta = num_receta;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public int getCantidad_recetada() {
        return cantidad_recetada;
    }

    public void setCantidad_recetada(int cantidad_recetada) {
        this.cantidad_recetada = cantidad_recetada;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    @Override
    public String toString() {
        return "Detalle_Receta{" + "detalle_receta=" + detalle_receta + ", num_receta=" + num_receta + ", id_insumo=" + id_insumo + ", cantidad_recetada=" + cantidad_recetada + ", dosis=" + dosis + '}';
    }
        
}
