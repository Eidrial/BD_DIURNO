/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.iesruizgijon.diurno.programacionbasededatos;

/**
 *
 * @author azahara
 */
public class App {

    public static void main(String[] args) {

        final String USER = "root";
        final String PASS = "123qweASD_";
        final String nameDB = "northwind";
        final String URL = "jdbc:mysql://localhost:3306/"; //o 127.0.0.1:3306

        BaseDatos bd = new BaseDatos(USER, PASS, nameDB);
        bd.conecta();
        bd.desconecta();

    }
}
