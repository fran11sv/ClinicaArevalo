package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Factura {
    
    int num_factura;
    int id_usuario;
    String nombre_cliente;
    String fecha_factura;
    int estado_factura;

    public Factura(int num_factura, int id_usuario, String nombre_cliente, String fecha_factura, int estado_factura) {
        this.num_factura = num_factura;
        this.id_usuario = id_usuario;
        this.nombre_cliente = nombre_cliente;
        this.fecha_factura = fecha_factura;
        this.estado_factura = estado_factura;
    }

    public Factura(int id_usuario, String nombre_cliente, String fecha_factura, int estado_factura) {
        this.id_usuario = id_usuario;
        this.nombre_cliente = nombre_cliente;
        this.fecha_factura = fecha_factura;
        this.estado_factura = estado_factura;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public int getEstado_factura() {
        return estado_factura;
    }

    public void setEstado_factura(int estado_factura) {
        this.estado_factura = estado_factura;
    }

    @Override
    public String toString() {
        return "Factura{" + "num_factura=" + num_factura + ", id_usuario=" + id_usuario + ", nombre_cliente=" + nombre_cliente + ", fecha_factura=" + fecha_factura + ", estado_factura=" + estado_factura + '}';
    }
  
}
