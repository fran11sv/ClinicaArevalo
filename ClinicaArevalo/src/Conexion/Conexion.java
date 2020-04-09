/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franb
 */
public class Conexion {
    Connection cx;
    String bd="u795523052_clinica";
    String url ="jdbc:mysql://194.5.156.94:3306/"+bd;
    String user = "u795523052_admin";
    String pass ="Clinica123";
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cx=(Connection)DriverManager.getConnection(url,user,pass);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException|SQLException ex) {
           System.out.println("No hay conexión");
        }
        return cx;
    }
    public void desconectar (){
        try {
            cx.close();
        } catch (SQLException ex) {
            System.out.println("Hay un problema cerrando la conexión");
        }
    }
}
