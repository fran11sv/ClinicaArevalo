package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Productos {
    
    int id_producto;
    int id_categoria;
    String nombre;
    String detalle;
    String precio;

    public Productos(int id_producto, int id_categoria, String nombre, String detalle, String precio) {
        this.id_producto = id_producto;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.detalle = detalle;
        this.precio = precio;
    }

    public Productos(int id_categoria, String nombre, String detalle, String precio) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.detalle = detalle;
        this.precio = precio;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Productos{" + "id_producto=" + id_producto + ", id_categoria=" + id_categoria + ", nombre=" + nombre + ", detalle=" + detalle + ", precio=" + precio + '}';
    }
       
}
