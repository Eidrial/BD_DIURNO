/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.diurno.programacionbasededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azahara
 */
public class BaseDatos {

    //Atributos
    private Connection conexion;

    private final String USER;
    private final String PASS;
    private final String nameDB;
    private final String URL = "jdbc:mysql://127.0.0.1:3306/";

    //Constructor
    public BaseDatos(String nameDB, String USER, String PASS) {
        this.USER = USER;
        this.PASS = PASS;
        this.nameDB = nameDB;
    }

    //Métodos
    //Para conectar
    public void conecta() {

        try {
            conexion = DriverManager.getConnection(URL + nameDB, USER, PASS);
            System.out.println("Conexión establecida...");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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

    public void consultaPrueba() {
        String company;
        String apellidos;
        String nombre;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select company, last_name, first_name from customers limit 10");
            while (resultado.next()) {
                company = resultado.getString("company");
                apellidos = resultado.getString("last_name");
                nombre = resultado.getString("first_name");

                System.out.println(" Company " + company + " Apellidos " + apellidos + " Nombre " + nombre);
            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consultaPrueba2() {
        String nombre;
        int identificadorPedido;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select concat(last_name, ' ' ,first_name) as nombre, orders.id as identificadorPedido from customers INNER JOIN orders ON customers.id = orders.customer_id limit 10;");

            while (resultado.next()) {
                nombre = resultado.getString("nombre");
                identificadorPedido = resultado.getInt("identificadorPedido");

                System.out.println(" Nombre del cliente: " + nombre + " \n Identificador del pedido: " + identificadorPedido + "\n");
            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] describe(String nombre) {

        String[] columnas = null;
        int n_columnas;
        int i;

        try {

            Statement statement = conexion.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM " + nombre);
            ResultSetMetaData metadatos = resultset.getMetaData();

            n_columnas = metadatos.getColumnCount();
            columnas = new String[n_columnas];

            for (i = 1; i <= n_columnas; i++) {
                columnas[i - 1] = metadatos.getColumnName(i);
            }
        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }
}
