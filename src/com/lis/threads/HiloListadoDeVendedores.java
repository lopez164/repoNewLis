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
public class HiloListadoDeVendedores implements Runnable {

    private int tiempo = 5;
    Inicio ini;
    //IngresarManifiestoDeDistribucion form;
    List<String> listaDeVendedores = null;

    ResultSet rst = null;

    

    public HiloListadoDeVendedores(Inicio ini) {
        this.ini = ini;

    }

    @Override
    public void run() {

            ResultSet rst = null;
            Statement st;
            Connection con;
            // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
con = ini.getConnRemota();

            String vendedor;
            listaDeVendedores = new ArrayList();
                String sql="select distinct vendedor from facturascamdun";

            if (con != null) {
                try {
                    st = con.createStatement();
                    rst = st.executeQuery(sql);

                    while (rst.next()) {

                        vendedor = asignarPropiedades(rst);

                        System.out.println("Cargando vehiculo de placa  -> " + vendedor);

                        listaDeVendedores.add(vendedor);
                        Thread.sleep(10);
                    }
                    rst.close();
                    st.close();
                   //
                    ini.setListaDeVendedores(listaDeVendedores);
                } // fin try // fin try // fin try // fin try
                catch (SQLException | InterruptedException ex) {
                    Logger.getLogger(HiloListadoDeVendedores.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        

    }

    private String asignarPropiedades(ResultSet rst1) throws SQLException {
       String placa;
       
        placa=(rst1.getString("vendedor"));
        
        return placa;
    }
}
