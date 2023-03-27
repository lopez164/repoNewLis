/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.threads;


import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class JcProgresInfinitear implements Runnable {

    private PerformanceInfiniteProgressPanel jProgressBar = new PerformanceInfiniteProgressPanel(true);
    private int i = 1;
    private int value = 50;//retardo en milisegundos

    public JcProgresInfinitear(PerformanceInfiniteProgressPanel panel, int value) {
        this.jProgressBar = panel;
        this.value = value;
        //jProgressBar.setVisible(true);
    }

    @Override
    public void run() {
        int i = 1;
        // jProgressBar.setVisible(true);
        while (!HiloAux.band) {
            try {
                Thread.sleep(this.value);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                 JOptionPane.showMessageDialog(null, "Error thread JcProgresInfinitear :" + e, " Alerta, cerrar ventana", 1, null);
                    
                jProgressBar.setVisible(false);
                jProgressBar = null;
            }

            if (HiloAux.band) {
                jProgressBar.setVisible(false);
                jProgressBar = null;
                System.out.println("Trabajo finalizado...");
                break;//rompe ciclo 
            }

        }



    }
}
