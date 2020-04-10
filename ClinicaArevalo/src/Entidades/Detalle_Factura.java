package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Detalle_Factura {
    
    int id_detalle_factura;
    int num_factura;
    int id_producto;

    public Detalle_Factura(int id_detalle_factura, int num_factura, int id_producto) {
        this.id_detalle_factura = id_detalle_factura;
        this.num_factura = num_factura;
        this.id_producto = id_producto;
    }

    public Detalle_Factura(int num_factura, int id_producto) {
        this.num_factura = num_factura;
        this.id_producto = id_producto;
    }

    public Detalle_Factura() {
        
    }

    public int getId_detalle_factura() {
        return id_detalle_factura;
    }

    public void setId_detalle_factura(int id_detalle_factura) {
        this.id_detalle_factura = id_detalle_factura;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    @Override
    public String toString() {
        return "Detalle_Factura{" + "id_detalle_factura=" + id_detalle_factura + ", num_factura=" + num_factura + ", id_producto=" + id_producto + '}';
    }
       
}
