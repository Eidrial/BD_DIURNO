/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.diurno.programacionbasededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azahara
 */
public class BaseDatos {

    //Atributos
    private Connection conexion;

    private String URL = "jdbc:mysql://localhost:3306/";
    private String user;
    private String password;
    private String nameDB;

    //Constructor
    public BaseDatos(String user, String password, String nameDB) {
        this.user = user;
        this.password = password;
        this.nameDB = nameDB;
    }

    //Métodos
    //Para conectar
    public void conecta() {

        Connection conexion;

        try {
            conexion = DriverManager.getConnection(URL + nameDB, user, password);
            System.out.println("Conexión establecida...");
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error de conexión.");
        }
    }

    //Para desconectar
    public void desconecta() {

        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Desconexión completada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error de desconexión.");
        }
    }

}
