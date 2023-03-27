/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import com.obj.CTiposDeCarrocerias;
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
public class HiloListadoDeTiposDeCarrocerias implements Runnable {

    List<CTiposDeCarrocerias> listaDeTiposDeCarrocerias = null;

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
    public HiloListadoDeTiposDeCarrocerias(Inicio ini) {
        this.ini = ini;

    }

    /**
     * Constructor de clase
     */
    public HiloListadoDeTiposDeCarrocerias(Inicio ini, JProgressBar barraInf, JProgressBar barraSup, int totalFilas, int contadorDeRegistros) {
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

            CTiposDeCarrocerias tipCarr = new CTiposDeCarrocerias(ini);
            listaDeTiposDeCarrocerias = new ArrayList();
            if (con != null) {
                st = con.createStatement();
                rst = st.executeQuery(tipCarr.arrListadoDeTiposDeCarrocerias());

                rst.last();
                int numeroFilas = rst.getRow();
                rst.beforeFirst();

                this.totalFilasDeConsultas += numeroFilas;

                while (rst.next()) {

                    tipCarr = new CTiposDeCarrocerias(ini);

                    tipCarr.setIdCarroceria(rst.getInt("idTipoCarroceria"));
                    tipCarr.setNombreCarroceria(rst.getString("nombreTipoCarroceria"));
                    tipCarr.setActivoCarroceria(rst.getInt("activo"));;

                    listaDeTiposDeCarrocerias.add(tipCarr);

                    k++;
                    System.out.println("Cargando carrocerias -> " + k);

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
                ini.setListaDeTiposDeCarrocerias(listaDeTiposDeCarrocerias);
                Thread.sleep(1);
            }
        } // fin try // fin try // fin try // fin try // fin try // fin try // fin try // fin try
        catch (Exception ex) {
            Logger.getLogger(HiloListadoDeTiposDeCarrocerias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listaVacio() throws HeadlessException {
        ResultSet rst = null;
        Statement st = null;
        Connection con = null;
        try {

            // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
con = ini.getConnRemota();

            CTiposDeCarrocerias tipCarr = new CTiposDeCarrocerias(ini);
            listaDeTiposDeCarrocerias = new ArrayList();
            if (con != null) {
                st = con.createStatement();
                rst = st.executeQuery(tipCarr.arrListadoDeTiposDeCarrocerias());
                while (rst.next()) {
                    System.out.println("Cargando  -> " + new Date());
                    tipCarr = new CTiposDeCarrocerias(ini);

                    tipCarr.setIdCarroceria(rst.getInt("idTipoCarroceria"));
                    tipCarr.setNombreCarroceria(rst.getString("nombreTipoCarroceria"));
                    tipCarr.setActivoCarroceria(rst.getInt("activo"));;

                    System.out.println("Cargando Tipos de carroceria -> " + tipCarr.getNombreCarroceria());

                    listaDeTiposDeCarrocerias.add(tipCarr);

                    System.out.println("tiempo 2 " + new Date());

                }
                rst.close();
                st.close();
               //
                ini.setListaDeTiposDeCarrocerias(listaDeTiposDeCarrocerias);
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
                Logger.getLogger(HiloListadoDeTiposDeCarrocerias.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(HiloListadoDeTiposDeCarrocerias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
