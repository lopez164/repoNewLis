/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.main.menu;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author lelopez
 */
public class ParaProbarMiMenu extends JFrame {
    // Constructor de la clase ParaProbarMiMenu    

    public ParaProbarMiMenu() {
        super("Probando ... ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(50, 50, screenSize.width - 100, screenSize.height - 100);
        /* Definición e inicialización de la matriz n x 2 contentiva       
            de las opciones que se desea dar a MiMenu. Se espera que       
            esta matriz contenga sólo tipos String, y que cada elemento,      
            en sentido vertical, esté compuesto por el par Clave, Opción,      
            donde Clave es un número que mantiene la jerarquía del       
            árbol de opciones, por ejemplo, 112 es subopción de 11, la      
            cual es subopción de 1, y así en todos los demás casos. */
        String vtOpciones[][] = {
            {"1", "Opción _1"},
            {"11", "Opción 1_1"},
            {"12", "Opción 1_2"},
            {"121", "Opción 12_1"},
            {"122", "Opción 12_2"},
            {"13", "Opción 1_3"},
            {"2", "Opción _2"},
            {"21", "Opción 2_1"},
            {"22", "Opción 2_2"},
            {"221", "Opción 22_1"},
            {"3", "Opción _3"}

        };
        /* Llamada de MiMenu que envía la matriz de opciones y el      
            Método de la clase que lo invoca que resuelve las acciones       
            del menú dado */
        MiMenu mnPrin = new MiMenu(vtOpciones, "AccionesMenu");
        // Establecimiento de MiMenu como el menú de la aplicación 
        setJMenuBar(mnPrin);
    }// F    

    /* Método que resuelve las acciones a tomar cuando se ha seleccionado    
una opción de MiMenu, la cual pasa como parámetro String en Opc y   
representa una clave de la matriz de opciones definida. */
    public void AccionesMenu(String Opc) {
        /* En este ejemplo, estas son las claves de opciones terminales       
(esto es, aquellas que provocan acciones) definidas. Por     
supuesto si cambia el menú de opciones, será necesario      
alterar el contenido de este método, en consecuencia. */
        if (Opc.equals("11")) {
            System.out.print("11");
        } else if (Opc.equals("121")) {
            System.out.print("121");
        } else if (Opc.equals("122")) {
            System.out.print("122");
        } else if (Opc.equals("13")) {
            System.out.print("13");
        } else if (Opc.equals("21")) {
            System.out.print("21");
        } else if (Opc.equals("221")) {
            System.out.print("221");
        } else if (Opc.equals("3")) {
            System.out.print("3");
        }

    } // Fin de AccionesMenu          
// Principal de ParaProbarMiMenu     

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        ParaProbarMiMenu frame = new ParaProbarMiMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } // Fin de mai

}






