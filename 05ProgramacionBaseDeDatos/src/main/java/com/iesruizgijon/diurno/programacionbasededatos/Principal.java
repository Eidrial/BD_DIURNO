/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.iesruizgijon.diurno.programacionbasededatos;

/**
 *
 * @author azahara
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final String USER = "root";
        final String PASS = "123qweASD_";
        final String nameDB = "northwind";

        BaseDatos bd = new BaseDatos(nameDB, USER, PASS);

        bd.conecta();

        System.out.println("\nPRUEBA 1 ~ CONSULTA SIMPLE:\n");
        bd.consultaPrueba();

        System.out.println("\n\nPRUEBA 2 ~ CONSULTA CONCATENAR APELLIDOS-NOMBRE CON LOS PEDIDOS REALIZADOS:\n");
        bd.consultaPrueba2();

        System.out.println("\n\nPRUEBA 3 ~ IMPLEMENTA LA FUNCIÓN 'DESCRIBE' DE UN CLIENTE MYSQL:\n");
        String[] TablaPedidos = bd.describe("orders");

        for (String TablaPedido : TablaPedidos) {
            System.out.println(" " + TablaPedido);
        }
        System.out.println("");

        System.out.println("\n\nPRUEBA 4 ~ MOSTRAR LOS NOMBRES DE TODAS LAS BASES DE DATOS CREADAS:");
        bd.getDataBaseName();
        System.out.println("");
        
        bd.desconecta();
        

    }

}
