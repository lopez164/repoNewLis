package com.lis.main.swing;

import com.lis.main.EventMenu;
import com.lis.main.EventMenuSelected;
import com.lis.main.menu.ModelMenu;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author lelopez
 */
public class MenuItem extends javax.swing.JPanel {

    private float alpha;
    private ModelMenu menuModel;
    private boolean open;
    private EventMenuSelected eventSelected;
    private EventMenu eventMenu;
    private int index;

    public MenuItem(ModelMenu menuModel, EventMenu eventMenu, EventMenuSelected eventSelected, int index) {

        initComponents();
        this.menuModel = menuModel;
        this.eventMenu = eventMenu;
        this.eventSelected = eventSelected;
        this.index = index;
        // setOpen(false);
        setOpaque(false);
        setLayout(new MigLayout("wrap , fillx, insets 0", "[fill]", "[fill, 40!]0[fill, 35!]"));
       
        MenuButton firstItem = new MenuButton(menuModel.getIcon(), "     " + menuModel.getMenuName());
       
        firstItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuModel.getSubMenu().length > 0) {
                    // System.out.println("Click first Item");
                    if (eventMenu.menuPressed(MenuItem.this, !open)) {
                        open = !open;
                    }
                    eventSelected.menuSelected(index, -1);
                }
            }
        });
        add(firstItem);
        int subMenuIndex = -1;
        for (String st : menuModel.getSubMenu()) {
            MenuButton item = new MenuButton(st);
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eventSelected.menuSelected(index, item.getIndex());
                }
            });
            add(item);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public ModelMenu getMenuModel() {
        return menuModel;
    }

    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(EventMenuSelected eventSelected) {
        this.eventSelected = eventSelected;
    }

    public int getIndex() {
        return index;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getPreferredSize().getSize().height;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(125, 15, 158));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.fillRect(0, 4, width, 26);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(196, 47, 242));
        g2.fillRect(0, 30, width, height - 30);
        g2.setColor(new Color(255, 255, 255));
        g2.drawLine(30, 40, 30, height - 17);
        for (int i = 0; i < menuModel.getSubMenu().length; i++) {
            int y = ((i + 1) * 35 + 40) - 17;
            g2.drawLine(30, y, 38, y);
        }
        if (menuModel.getSubMenu().length > 0) {
            createArrowButton(g2);
        }

        super.paintComponent(g);
    }

    private void createArrowButton(Graphics2D g2) {
        int size = 4;
        int y = 19;
        int x = 205;
        g2.setColor(new Color(255, 255, 255));
        float ay = alpha * size;
        float ay1 = (1f - alpha) * size;
        g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
        g2.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
