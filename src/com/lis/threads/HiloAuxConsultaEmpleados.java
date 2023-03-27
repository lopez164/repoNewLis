/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import java.util.ArrayList;
import com.obj.*;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class HiloAuxConsultaEmpleados implements Runnable {

    public static boolean band = false;
    private int tiempo = 5;
    int nivelDeAcceso;
    Inicio ini;
    List<CEmpleados> arrEmpleados = null;
    
    CUsuarios user;
   
    /**
     * Constructor de clase
     */
    public HiloAuxConsultaEmpleados( Inicio ini,int tiempo) throws Exception {
        this.tiempo = tiempo;
                this.ini=ini;
        this.user=ini.getUser();
        

    }

    @Override
    public void run() {

   }
}
