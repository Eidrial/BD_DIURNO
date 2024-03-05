/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.diurno.client_mysql;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

        try {

            Statement statement = conexion.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM " + nombre);
            ResultSetMetaData metadatos = resultset.getMetaData(); //Tener todos los datos que queramos y necesitemos.

            n_columnas = metadatos.getColumnCount(); //Contar columnas.
            columnas = new String[n_columnas]; //Definimos el array con el total de las columnas contadas anteriormente.

            for (int i = 1; i <= n_columnas; i++) {
                columnas[i - 1] = metadatos.getColumnName(i); //Sifuese: i = 0, sería: columnas[i + 1];
            }
        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }

    public void getDataBaseName() {

        try {

            //BD que debe de existir siempre, la cuál es la BD "mysql".
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/mysql", "root", "123qweASD_");

            System.out.println("\nConexión realizada con éxito.\n");

            Statement stmt = conexion.createStatement();

            //Devolver los datos.
            ResultSet rs = stmt.executeQuery("Show Databases"); //Es un comando, NO PUEDE TOCARSE, es el mismo que se ejecuta en el terminal.

            System.out.println("Lista bases de datos: \n");

            while (rs.next()) {

                System.out.print(" " + rs.getString(1));

                System.out.println();

            }

        } catch (SQLException ex) {

            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
//
//    public ArrayList<String> consultaEnGui(String consulta) {
//
//        ArrayList<String> lista = new ArrayList();
//
//        try {
//            Statement sentencia = conexion.createStatement();
//            ResultSet resultado = sentencia.executeQuery(consulta);
//            int columnsNumber = rsmd.getColumnCount();
//    
//            while (resultado.next()) {
//            
//            for (int i = 0; i < columnsNUmber; i++) {
//
//              lista.add(resultado.getArray(i));
//                
//            }
//            resultado.close();
//            sentencia.close();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return null;
//
//    }
    
//    public String consultaEnGui(resultado) {
//        String nombre;
//        int identificadorPedido;
//
//        try {
//            Statement sentencia = conexion.createStatement();
//            ResultSet resultado = sentencia.executeQuery("select concat(last_name, ' ' ,first_name) as nombre, orders.id as identificadorPedido from customers INNER JOIN orders ON customers.id = orders.customer_id limit 10;");
//
//            while (resultado.next()) {
//                nombre = resultado.getString("nombre");
//                identificadorPedido = resultado.getInt("identificadorPedido");
//
//                System.out.println(" Nombre del cliente: " + nombre + " \n Identificador del pedido: " + identificadorPedido + "\n");
//            }
//            resultado.close();
//            sentencia.close();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
