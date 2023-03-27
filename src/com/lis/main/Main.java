/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.main;

import com.conf.Inicio;
import com.lis.main.swing.MenuItem;
import com.lis.main.swing.PopUpMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author lelopez
 */
public class Main extends javax.swing.JFrame {

    Inicio ini;
    private MigLayout layout;
    private PanelMenu panelMenu;
    private PanelHeader panelHeader;
    private MainForm mainForm;
    private Animator animator;
    private PanelMensaje panelmensaje;
    private Color colorBakGround;

     public Main(Inicio ini) {
        this.ini = ini;
        colorBakGround = ini.getColorBackGround();
        initComponents();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocation(0, 0);
        init();

    }
    public Main() {
        initComponents();
        colorBakGround = new Color(189,21,34);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocation(0, 0);
        init();

    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%,fill]0", "0[fill,top]0");
        pnlBg.setLayout(layout);
        panelMenu = new PanelMenu();
        //panelMenu.setBackground(ini.getColorBackGround());
        //panelMenu.repaint();
        
        panelHeader = new PanelHeader();
        mainForm = new MainForm();
        panelmensaje = new PanelMensaje();
        
        
        panelMenu.addEventmenuSelected(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("menu index : " + menuIndex + ",sub Menuindex : " + subMenuIndex);

            }

        });
        panelMenu.addEventShowPopUpMenu(new EventShowPopUpMenu() {
            @Override
            public void ShowPopUpMenu(Component comp) {
                MenuItem item = (MenuItem) comp;
                PopUpMenu popUp = new PopUpMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenuModel().getSubMenu());
               /*Posicion del popup menu al hacer click */
                int x = Main.this.getX() + 51; //52
                int y = Main.this.getY() + comp.getY() + 148;
                popUp.setLocation(x, y);
                popUp.setVisible(true);
                popUp.setResizable(false);
            }
        });
               
        panelMenu.initMenuItem();

        pnlBg.add(panelMenu, "w 230!, spany 2"); //span y 2 cell 
        pnlBg.add(panelHeader, "h 50!, wrap");
        pnlBg.add(mainForm, "w 100%, h 100%");
       // pnlBg.add(panelmensaje,"w 20%, h 20%");
       
        TimingTarget target = new TimingTargetAdapter() {

            @Override
            public void timingEvent(float fraction) {
                double width;
                if (panelMenu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * (fraction));
                }
                layout.setComponentConstraints(panelMenu, "w " + width + "!,spany2");
                panelMenu.revalidate();

            }

            @Override
            public void begin() {
                super.begin(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void end() {
                panelMenu.setShowMenu(!panelMenu.isShowMenu());
                panelMenu.setEnableMenu(true);

            }

        };
        
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
       
        panelHeader.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                panelMenu.setEnableMenu(false);
                if (panelMenu.isShowMenu()) {
                    panelMenu.hideAllMenu();
                }
            }
            
            
            
        });
        
        panelmensaje.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if (!animator.isRunning()) {
                    animator.start();
                }
                panelMenu.setEnableMenu(false);
                if (panelMenu.isShowMenu()) {
                    panelMenu.hideAllMenu();
                }
                
            }
        });
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlBg.setBackground(new java.awt.Color(218, 202, 216));
        pnlBg.setOpaque(true);

        javax.swing.GroupLayout pnlBgLayout = new javax.swing.GroupLayout(pnlBg);
        pnlBg.setLayout(pnlBgLayout);
        pnlBgLayout.setHorizontalGroup(
            pnlBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        pnlBgLayout.setVerticalGroup(
            pnlBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane pnlBg;
    // End of variables declaration//GEN-END:variables
}
