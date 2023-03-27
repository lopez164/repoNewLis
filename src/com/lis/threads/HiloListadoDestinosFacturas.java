/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import com.obj.CDestinosFacturas;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author Usuario
 */
public class HiloListadoDestinosFacturas implements Runnable {

    List<CDestinosFacturas> listaDeDestinosFacturas = null;

    
    Inicio ini = null;
    JProgressBar barraInf = null;
    JProgressBar barraSup = null;
    int totalFilasDeConsultas;
    int contadorDeRegistros;

   

    /**
     * Constructor de clase
     *
     * @param ini
     * @param barraInf
     * @param barraSup
     * @param totalFilas
     */
    public HiloListadoDestinosFacturas(Inicio ini, JProgressBar barraInf, JProgressBar barraSup, int totalFilas, int contadorDeRegistros) {
        this.barraInf = barraInf;
        this.barraSup = barraSup;
        this.totalFilasDeConsultas = totalFilas;
        this.contadorDeRegistros = contadorDeRegistros;
        this.ini = ini;


    }

    /**
     * Constructor de clase
     *
     * @param ini
     */
    public HiloListadoDestinosFacturas(Inicio ini) {
      this.ini = ini;

    }

    @Override
    public void run() {
        if ( this.barraInf != null) {
            listaForm();
        } else {
            listaVacio();
        }

        
    }

    private void listaForm() throws HeadlessException {
       
        try {

            
                int k = 0; 
                int y = 0;
                ResultSet rst = null;
                Statement st;
                Connection con;
            // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
            //con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal(),"HiloListadoDestinosFacturas");
con = ini.getConnRemota();
           
            CDestinosFacturas destino = new CDestinosFacturas(ini);
             
            listaDeDestinosFacturas = new ArrayList();
            if (con != null) {
                st = con.createStatement();
                rst = st.executeQuery(destino.arrListadoDeDestinosFacturas());

                rst.last();
                int numeroFilas = rst.getRow();
                rst.beforeFirst();

                this.totalFilasDeConsultas += numeroFilas;

                while (rst.next()) {
                  
                    destino = new CDestinosFacturas(ini);
                  
                    destino.setIddestinosFacturas(rst.getInt("iddestinosFacturas"));
                    destino.setNombreDestino(rst.getString("nombreDestino"));
                    destino.setActivoDestino(rst.getInt("activo"));

                    listaDeDestinosFacturas.add(destino);

                    k++;
                         System.out.println("Cargando usuarios -> " + k);
                        
                        /*Le asigna valor a la barra inferior*/
                        y = (int) (k * 100) / numeroFilas;
                        this.barraInf.setValue(y);
                        this.barraInf.repaint();
                        Thread.sleep(1);
                    }

                    /*Asigna el valor a la Barra de progreso superior*/
                    this.contadorDeRegistros += numeroFilas;
                    y = (int) (this.contadorDeRegistros * 100) / totalFilasDeConsultas;
                    this.barraSup.setValue((int) y);//100
                    this.barraSup.repaint();
                
                rst.close();
                st.close();
               //
                ini.setArrDestinosFacturas(listaDeDestinosFacturas);
                Thread.sleep(1);
            }
        } // fin try // fin try // fin try // fin try
        catch (Exception ex) {
            Logger.getLogger(HiloListadoDestinosFacturas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listaVacio() throws HeadlessException {
        ResultSet rst = null;
        Statement st = null;
        Connection con = null;
        try {

            // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
con = ini.getConnRemota();

            CDestinosFacturas destino = new CDestinosFacturas(ini);
            listaDeDestinosFacturas = new ArrayList();
            if (con != null) {
                st = con.createStatement();
                rst = st.executeQuery(destino.arrListadoDeDestinosFacturas());

                while (rst.next()) {
                    System.out.println("Cargando  -> " + new Date());
                    destino = new CDestinosFacturas(ini);
                    //idCargo, nombreCargo, activo, fechaIng, usuario, flag
                    destino.setIddestinosFacturas(rst.getInt("iddestinosFacturas"));
                    destino.setNombreDestino(rst.getString("nombreDestino"));
                    destino.setActivoDestino(rst.getInt("activo"));

                    System.out.println("Cargando Cargos -> " + destino.getNombreDestino());

                    listaDeDestinosFacturas.add(destino);

                    System.out.println("tiempo 2 " + new Date());

                }
                rst.close();
                st.close();
               //
                ini.setArrDestinosFacturas(listaDeDestinosFacturas);
                Thread.sleep(1);
            }
        } // fin try // fin try // fin try // fin try
        catch (InterruptedException e) {
            try {
                rst.close();
                st.close();
               //
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Error thread 0 :" + e, " Alerta, cerrar ventana", 1, null);
            } catch (SQLException ex) {
                Logger.getLogger(HiloListadoDestinosFacturas.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            Logger.getLogger(HiloListadoDestinosFacturas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
