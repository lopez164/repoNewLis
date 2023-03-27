/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class HiloListadoDePlacas implements Runnable {

    private int tiempo = 5;
    Inicio ini;
    //IngresarManifiestoDeDistribucion form;
    List<String> listaDePlacas = null;
   

    ResultSet rst = null;

  
    public HiloListadoDePlacas(Inicio ini) {
        this.ini = ini;

    }

    @Override
    public void run() {
     
            ResultSet rst = null;
            Statement st;
            Connection con;
            // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
 con = ini.getConnRemota();
            String placa;
            listaDePlacas = new ArrayList();
                String sql="SELECT placa FROM vst_vehiculos where activo=1";

            if (con != null) {
                try {
                    st = con.createStatement();
                    rst = st.executeQuery(sql);

                    while (rst.next()) {

                        placa = asignarPropiedades(rst);

                        System.out.println("Cargando vehiculo de placa  -> " + placa);

                        listaDePlacas.add(placa);
                        Thread.sleep(10);
                    }
                    rst.close();
                    st.close();
                    con.close();
                    ini.setListaDePlacas(listaDePlacas);
                } // fin try // fin try
                catch (SQLException | InterruptedException ex) {
                    Logger.getLogger(HiloListadoDePlacas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        

    }

    private String asignarPropiedades(ResultSet rst1) throws SQLException {
       String placa;
       
        placa=(rst1.getString("placa"));
        
        return placa;
    }
}
