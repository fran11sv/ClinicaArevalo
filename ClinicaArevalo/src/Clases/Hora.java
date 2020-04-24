/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author franb
 */
public class Hora {
    private int valor;
    private String nombre;

    public Hora() {
    }
    public int getValor() {
        return valor;
    }
    public Hora(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return nombre;
    }

}
