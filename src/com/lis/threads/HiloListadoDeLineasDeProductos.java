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
public class HiloListadoDeLineasDeProductos implements Runnable {

    private int tiempo = 5;
    Inicio ini;
    //IngresarManifiestoDeDistribucion form;
    List<String> listaDeLineasDeProductos = null;

    ResultSet rst = null;

  

    public HiloListadoDeLineasDeProductos(Inicio ini) {
        this.ini = ini;

    }

    @Override
    public void run() {
 
            ResultSet rst = null;
            Statement st;
            Connection con;
            // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
            //con = CConexiones.GetConnection(ini.getCadenaRemota(), ini.getUsuarioBDRemota(), ini.getClaveBDRemota(),"HiloListadoDePlacas");

            con = ini.getConnRemota();
            
            String linea;
            listaDeLineasDeProductos = new ArrayList();
                String sql="select distinct linea from productosCamdun";

            if (con != null) {
                try {
                    st = con.createStatement();
                    rst = st.executeQuery(sql);

                    while (rst.next()) {

                        linea = asignarPropiedades(rst);

                        System.out.println("Cargando vehiculo de placa  -> " + linea);

                        listaDeLineasDeProductos.add(linea);
                        Thread.sleep(10);
                    }
                    rst.close();
                    st.close();
                    //con.close();
                    ini.setListaDeVendedores(listaDeLineasDeProductos);
                } // fin try // fin try // fin try // fin try // fin try // fin try // fin try // fin try
                catch (SQLException | InterruptedException ex) {
                    Logger.getLogger(HiloListadoDeLineasDeProductos.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        

    }

    private String asignarPropiedades(ResultSet rst1) throws SQLException {
       String linea;
       
        linea=(rst1.getString("linea"));
        
        return linea;
    }
}
