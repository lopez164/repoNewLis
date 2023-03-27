/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import com.obj.CRegionales;
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
public class HiloListadoDeRegionales implements Runnable {

    List<CRegionales> listaDeRegionales = null;

    Inicio ini = null;
    JProgressBar barraInf = null;
    JProgressBar barraSup = null;
    int totalFilasDeConsultas;
    int contadorDeRegistros;

    /**
     * Constructor de clase
     */
    public HiloListadoDeRegionales(Inicio ini) {
        this.ini = ini;

    }

    /**
     * Constructor de clase
     */
    public HiloListadoDeRegionales(Inicio ini, JProgressBar barraInf, JProgressBar barraSup, int totalFilas, int contadorDeRegistros) {
        this.barraInf = barraInf;
        this.barraSup = barraSup;
        this.totalFilasDeConsultas = totalFilas;
        this.contadorDeRegistros = contadorDeRegistros;
        this.ini = ini;
    }

    @Override
    public void run() {
        if (this.barraInf != null) {
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
 con = ini.getConnRemota();           
            
            CRegionales regional = new CRegionales(ini);

            listaDeRegionales = new ArrayList();
            if (con != null) {
                st = con.createStatement();
                rst = st.executeQuery(regional.arrListadoDeRegionales());
                rst.last();
                int numeroFilas = rst.getRow();
                rst.beforeFirst();

                this.totalFilasDeConsultas += numeroFilas;

                while (rst.next()) {

                    regional = new CRegionales(ini);

                    regional.setIdRegional(rst.getInt("idRegional"));
                    regional.setIdZona(rst.getInt("idZona"));
                    regional.setNombreRegional(rst.getString("nombreRegional"));
                    regional.setNombreZona(rst.getString("nombreZona"));
                    regional.setActivoRegional(rst.getInt("activo"));

                   listaDeRegionales.add(regional);
                    k++;
                    System.out.println("Cargando Regionales -> " + k);

                    /*Le asigna valor a la barra inferior*/
                    y = (int) (k * 100) / numeroFilas;
                    this.barraInf.setValue(y);
                    this.barraInf.repaint();
                    Thread.sleep(1);
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
                ini.setListaDeRegionales(listaDeRegionales);
                Thread.sleep(1);
            }
        } // fin try // fin try // fin try // fin try // fin try // fin try // fin try // fin try
        catch (Exception ex) {
            Logger.getLogger(HiloListadoDeRegionales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listaVacio() throws HeadlessException {
        ResultSet rst = null;
        Statement st = null;
        Connection con = null;
        try {

            // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
 con = ini.getConnRemota();          
            
            CRegionales regional = new CRegionales(ini);

            listaDeRegionales = new ArrayList();
            if (con != null) {
                st = con.createStatement();
                rst = st.executeQuery(regional.arrListadoDeRegionales());

                while (rst.next()) {
                    System.out.println("Cargando  -> " + new Date());
                    regional = new CRegionales(ini);
                    regional.setIdRegional(rst.getInt("idRegional"));
                    regional.setIdZona(rst.getInt("idZona"));
                    regional.setNombreRegional(rst.getString("nombreRegional"));
                    regional.setNombreZona(rst.getString("nombreZona"));
                    regional.setActivoRegional(rst.getInt("activo"));

                    System.out.println("Cargando regionales -> " + regional.getNombreRegional());

                    listaDeRegionales.add(regional);

                    Thread.sleep(1);
                }
                rst.close();
                st.close();
               //
                ini.setListaDeRegionales(listaDeRegionales);
                Thread.sleep(1);
            }
        } // fin try // fin try // fin try // fin try // fin try // fin try // fin try // fin try
        catch (InterruptedException e) {

            try {
                rst.close();
                st.close();
               //
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Error thread 0 :" + e, " Alerta, cerrar ventana", 1, null);
            } catch (SQLException ex) {
                Logger.getLogger(HiloListadoDeRegionales.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            Logger.getLogger(HiloListadoDeRegionales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
