/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lis.swing;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


/**
 *
 * @author lelopez
 */
public class PanelTransparent extends  JPanel{

    private float alpha = 1f;
    
    public PanelTransparent() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 =(Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }
    

            
    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    
    
  
    
}
