package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Vademecum {
    int id_insumo;
    String principio_activo;
    String accion_terapeutica;
    String indicaciones;
    String marcas;
    String propiedades;
    String precauciones;
    String dosificacion;
    String reacciones_adversas;
    String interacciones;
    String contraindicaciones;
    String sobredosificacion;

    public Vademecum(int id_insumo, String principio_activo, String accion_terapeutica, String indicaciones, String marcas, String propiedades, String precauciones, String dosificacion, String reacciones_adversas, String interacciones, String contraindicaciones, String sobredosificacion) {
        this.id_insumo = id_insumo;
        this.principio_activo = principio_activo;
        this.accion_terapeutica = accion_terapeutica;
        this.indicaciones = indicaciones;
        this.marcas = marcas;
        this.propiedades = propiedades;
        this.precauciones = precauciones;
        this.dosificacion = dosificacion;
        this.reacciones_adversas = reacciones_adversas;
        this.interacciones = interacciones;
        this.contraindicaciones = contraindicaciones;
        this.sobredosificacion = sobredosificacion;
    }

    public Vademecum(String principio_activo, String accion_terapeutica, String indicaciones, String marcas, String propiedades, String precauciones, String dosificacion, String reacciones_adversas, String interacciones, String contraindicaciones, String sobredosificacion) {
        this.principio_activo = principio_activo;
        this.accion_terapeutica = accion_terapeutica;
        this.indicaciones = indicaciones;
        this.marcas = marcas;
        this.propiedades = propiedades;
        this.precauciones = precauciones;
        this.dosificacion = dosificacion;
        this.reacciones_adversas = reacciones_adversas;
        this.interacciones = interacciones;
        this.contraindicaciones = contraindicaciones;
        this.sobredosificacion = sobredosificacion;
    }

    public Vademecum() {
        
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public String getPrincipio_activo() {
        return principio_activo;
    }

    public void setPrincipio_activo(String principio_activo) {
        this.principio_activo = principio_activo;
    }

    public String getAccion_terapeutica() {
        return accion_terapeutica;
    }

    public void setAccion_terapeutica(String accion_terapeutica) {
        this.accion_terapeutica = accion_terapeutica;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
    }

    public String getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(String propiedades) {
        this.propiedades = propiedades;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }

    public String getDosificacion() {
        return dosificacion;
    }

    public void setDosificacion(String dosificacion) {
        this.dosificacion = dosificacion;
    }

    public String getReacciones_adversas() {
        return reacciones_adversas;
    }

    public void setReacciones_adversas(String reacciones_adversas) {
        this.reacciones_adversas = reacciones_adversas;
    }

    public String getInteracciones() {
        return interacciones;
    }

    public void setInteracciones(String interacciones) {
        this.interacciones = interacciones;
    }

    public String getContraindicaciones() {
        return contraindicaciones;
    }

    public void setContraindicaciones(String contraindicaciones) {
        this.contraindicaciones = contraindicaciones;
    }

    public String getSobredosificacion() {
        return sobredosificacion;
    }

    public void setSobredosificacion(String sobredosificacion) {
        this.sobredosificacion = sobredosificacion;
    }

    @Override
    public String toString() {
        return "Vademecum{" + "id_insumo=" + id_insumo + ", principio_activo=" + principio_activo + ", accion_terapeutica=" + accion_terapeutica + ", indicaciones=" + indicaciones + ", marcas=" + marcas + ", propiedades=" + propiedades + ", precauciones=" + precauciones + ", dosificacion=" + dosificacion + ", reacciones_adversas=" + reacciones_adversas + ", interacciones=" + interacciones + ", contraindicaciones=" + contraindicaciones + ", sobredosificacion=" + sobredosificacion + '}';
    }
        
}
