/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import com.obj.dist.CFacturas;
import com.obj.dist.CProductosPorFactura;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class HiloListadoDeProductosPorFactura implements Runnable {

    public static boolean band = false;
    private int tiempo = 5;
    Inicio ini;
    CFacturas factura;
    List<CProductosPorFactura> arrProductosPorFactura = null;

    ResultSet rst = null;

    /**
     * Constructor de clase
     * @param ini
     * @param tiempo
     * @param factura
     */
    public HiloListadoDeProductosPorFactura(Inicio ini, int tiempo, CFacturas factura) {
        this.tiempo = tiempo;
        this.ini = ini;
       this.factura=factura;
    }

    @Override
    public void run() {
        
     factura.getListaCProductosPorFactura();
           
    }
}
