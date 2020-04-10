/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.Conexion;
import Entidades.Paciente;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author franb
 */
public class ControladorPaciente {
    Conexion c;
    
    public ControladorPaciente(){
        c=new Conexion();
    }   
    public boolean create(Paciente p){
        try {
            String sql="INSERT INTO Paciente(Nombres,Apellidos,Direccion,DUI,Fecha_Nacimiento,Ocupacion,Sexo) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps=c.conectar().prepareStatement(sql);
        ps.setString(1,p.getNombres());
        ps.setString(2,p.getApellidos());
        ps.setString(3,p.getDireccion());
        ps.setString(4,p.getDUI());
        ps.setDate(5,p.getFechaNac());
        ps.setString(6,p.getOcupacion());
        ps.setString(7,p.getSexo());
        ps.execute();
        ps.close();
        ps=null;
        c.desconectar();
        return true;
        } catch (SQLException ex) {
        System.out.println("ERROR no se inserto registro");
        return false;
        }
    }
}
