/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import com.obj.dist.CFacturasPorManifiesto;
import com.obj.dist.CManifiestosDeDistribucion;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author Usuario
 */
public class HiloListadoDeFacturasPorManifiesto implements Runnable {
    
    public static boolean band = false;
    private int tiempo = 5;
    Inicio ini;
    CManifiestosDeDistribucion manifiesto = null;
      List<CFacturasPorManifiesto> listaFacturasPorManifiesto;
    Object ob;
    ResultSet rst = null;
    String numeroDeManifiesto;
    boolean exportar = false;

    /**
     * Constructor de clase
     */
    public HiloListadoDeFacturasPorManifiesto(Inicio ini, int tiempo, CManifiestosDeDistribucion manifiesto) {
        this.tiempo = tiempo;
        this.ini = ini;
        this.manifiesto = manifiesto;
        numeroDeManifiesto = manifiesto.getNumeroManifiesto();
    }
    
    
    
    
    
    
    @Override
    public void run() {
        if (exportar) {
            crearListaDeFacturasporManifiesto();
            
        } else {
            
            crearListaDeFacturasporManifiesto();
        }
        
    }
    
    private void crearListaDeFacturasporManifiesto() throws HeadlessException {
        ResultSet rst = null;
        Statement st;
        Connection con;

        // con = CConexiones.GetConnection(ini.getCadenaLocal(), ini.getUsuarioBDLocal(), ini.getClaveBDLocal());
        //con = CConexiones.GetConnection(ini.getCadenaRemota(), ini.getUsuarioBDRemota(), ini.getClaveBDRemota(),"HiloListadoDeFacturasPorManifiesto");
        
        con = ini.getConnRemota();
        
        String sql = null;
        
        listaFacturasPorManifiesto = new ArrayList();
        // manifiesto.listadoDeFacturas(manifiesto.getNumeroManifiesto());
        sql = "SELECT * "
                + " FROM vst_defintivofacturaspormanifiesto "
                + "WHERE "
                + "numeroManifiesto='" + numeroDeManifiesto + "' ORDER BY adherencia ASC ";
        
        try {
            
            if (con != null) {
                
                st = con.createStatement();
                rst = st.executeQuery(sql);
                while (rst.next()) {
                    System.out.println("Cargando  -> " + new Date());
                    CFacturasPorManifiesto fxm = new CFacturasPorManifiesto(ini);
                    fxm.setConsecutivo(rst.getInt("consecutivo"));
                    fxm.setAdherencia(rst.getInt("adherencia"));
                    fxm.setNumeroManifiesto(rst.getString("numeroManifiesto"));
                    fxm.setFechaDistribucion(rst.getString("fechaDistribucion"));
                    fxm.setVehiculo(rst.getString("vehiculo"));
                    fxm.setConductor(rst.getString("conductor"));
                    fxm.setNombreConductor(rst.getString("nombreConductor"));
                    fxm.setDespachador(rst.getString("despachador"));
                    fxm.setNombreDespachador(rst.getString("nombreDespachador"));
                    fxm.setIdRuta(rst.getInt("idRuta"));
                    fxm.setNombreDeRuta(rst.getString("nombreDeRuta"));
                    fxm.setNumeroFactura(rst.getString("numeroFactura"));
                    fxm.setValorARecaudarFactura(rst.getDouble("valorARecaudarFactura"));
                    fxm.setFechaIng(rst.getString("fechaIng"));
                    fxm.setFechaDeVenta(rst.getDate("fechaDeVenta"));
                    fxm.setCliente(rst.getString("cliente"));
                    fxm.setNombreDeCliente(rst.getString("nombreDeCliente"));
                    fxm.setDireccionDeCliente(rst.getString("direccionDeCliente"));
                    fxm.setVendedor(rst.getString("vendedor"));
                    fxm.setFormaDePago(rst.getString("formaDePago"));
                    fxm.setIdCanal(rst.getInt("idCanal"));
                    fxm.setNombreCanal(rst.getString("nombreCanal"));
                    fxm.setValorFacturaSinIva(rst.getDouble("valorFacturaSinIva"));
                    fxm.setValorIvaFactura(rst.getDouble("valorIvaFactura"));
                    fxm.setValorTotalFactura(rst.getDouble("valorTotalFactura"));
                    fxm.setValorRechazo(rst.getDouble("valorRechazo"));
                    fxm.setValorDescuento(rst.getDouble("valorDescuento"));
                    fxm.setValorRecaudado(rst.getDouble("valorTotalRecaudado"));
                    fxm.setSalidasDistribucion(rst.getInt("salidasDistribucion"));
                    fxm.setTrasmitido(rst.getInt("trasmitido"));
                    
                    fxm.setAdherenciaDescargue(0);
                    fxm.setIdTipoDeMovimiento(1);
                    fxm.setNombreIdmovimiento("NINGUNO");
                    fxm.setCausalDeRechazo(1);
                    fxm.setNombreCausalDeDevolucion("NINGUNO");
                    fxm.setLatitud(rst.getString("latitud"));
                    fxm.setLongitud(rst.getString("longitud"));
                    
                    System.out.println("Cargando vst_factura en manifiesto -> " + fxm.getNumeroFactura());
                    
                    listaFacturasPorManifiesto.add(fxm);
                }
                rst.close();
                st.close();
               // con.close();
                if (this.manifiesto != null) {
                    this.manifiesto.setListaFacturasPorManifiesto(listaFacturasPorManifiesto);
                }
                
                
                Thread.sleep(1);
            }
        } // fin try // fin try // fin try // fin try // fin try // fin try // fin try // fin try
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error thread 0 :" + e, " Alerta, cerrar ventana", 1, null);
            band = true;
        } catch (Exception ex) {
            Logger.getLogger(HiloListadoDeFacturasPorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
