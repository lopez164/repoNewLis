/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;

import com.conf.Inicio;
import com.obj.*;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class HiloAuxCarros implements Runnable {

    public static boolean band = false;
    private int tiempo = 5;
    Inicio ini;

    ArrayList<CMarcasDeVehiculos> arrMarcasVehiculos = null;
    ArrayList<CTiposDeVehiculos> arrTiposDeVehiculos = null;
    ArrayList<CTiposDeCarrocerias> arrTiposDeCarrocerias = null;
    ArrayList<CTiposDeServicio> arrTiposDeServicio = null;
    ArrayList<CTiposDeCombustibles> arrCombustibles = null;
    ArrayList<CAgencias> arrAgenciass = null;
    ArrayList<CTiposDeContratosVehiculos> arrContratos = null;

    /**
     * Constructor de clase
     */
    public HiloAuxCarros(Inicio ini, int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
      
    }
}
