/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import com.obj.CFacturasParaAnular;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class HiloListadoDeRechasosParaDescargar implements Runnable {

  
   NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
    Inicio ini;
    

     ArrayList<CFacturasParaAnular> listaDeRechasosParaAnular= null;


    /**
     * Constructor de clase
     * @param ini
     */
    public HiloListadoDeRechasosParaDescargar(Inicio ini) {
        this.ini = ini;
    }

    
    @Override
    public void run() {

        ResultSet rst = null;
        Statement st;
        Connection con;
        
        try {
             // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
 con = ini.getConnRemota();            
            listaDeRechasosParaAnular = new ArrayList();
            if (con != null) {
                st = con.createStatement();
                String sql="SELECT * FROM facturasparaanular ;";
                rst = st.executeQuery(sql);
                                
                while (rst.next()) {
                    System.out.println("Cargando  facturas para anular -> " + new Date());
                    CFacturasParaAnular rpa = new CFacturasParaAnular(ini);

                    rpa.setNumeroFactura(rst.getString("numeroFactura"));
                    rpa.setCausalDeRechazo(rst.getString("causalDeRechazo"));
                    

                    System.out.println("Cargando rechazos para anular -> " + rpa.getNumeroFactura());

                    listaDeRechasosParaAnular.add(rpa);
                    
                  
                   
                    Thread.sleep(2);
                
                    System.out.println("saliendo del ciclo facturas para anular " + new Date());

                }
                rst.close();
                st.close();
               //
                ini.setListaDeRechasosParaAnular(listaDeRechasosParaAnular);
                Thread.sleep(1);
            }
        }catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(HiloListadoDeRechasosParaDescargar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
