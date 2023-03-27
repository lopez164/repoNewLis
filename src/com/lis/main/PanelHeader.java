
package com.lis.main;

import java.awt.Color;
import java.awt.event.ActionListener;

/**
 *
 * @author lelopez
 */
public class PanelHeader extends javax.swing.JPanel {

   
    public PanelHeader() {
        initComponents();
        setBackground(Color.WHITE);
    }

  public void addMenuEvent( ActionListener evt){
      cmdMenu.addActionListener(evt);
  }
  
  
  
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMenu = new com.lis.main.swing.Button();
        imageAvatar1 = new com.raven.swing.ImageAvatar();

        setBackground(new java.awt.Color(230, 230, 230));

        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lis/icon/menu.png"))); // NOI18N

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lis/icon/profile2.jpg"))); // NOI18N
        imageAvatar1.setMaximumSize(new java.awt.Dimension(34, 34));
        imageAvatar1.setMinimumSize(new java.awt.Dimension(34, 34));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 465, Short.MAX_VALUE)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.lis.main.swing.Button cmdMenu;
    private com.raven.swing.ImageAvatar imageAvatar1;
    // End of variables declaration//GEN-END:variables
}
