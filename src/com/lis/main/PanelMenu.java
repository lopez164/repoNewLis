package com.lis.main;

import com.lis.main.menu.ModelMenu;
import com.lis.main.swing.MenuAnimation;
import com.lis.main.swing.MenuItem;
import com.lis.main.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author lelopez
 */
public class PanelMenu extends javax.swing.JPanel {

    private final MigLayout layout;
    //private final Animator animator;
    private EventMenuSelected event;
    private EventShowPopUpMenu  eventShowPopUpMenu;
    private EventMenu eventMenu;
    private boolean enableMenu = true;
    private boolean showMenu = true;
    Color colorBackGround;

    public PanelMenu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
        
        
        
    }
    

   private void addMenu(ModelMenu menu){
       panel.add(new MenuItem(menu, getEventMenu(),event, panel.getComponentCount()),"h 30!"); // h 30! oculta los itm del submenu
   }
   
   private EventMenu getEventMenu(){
       return new EventMenu() {
           @Override
           public boolean menuPressed(Component comp, boolean open) {
             if(enableMenu){
                 if(showMenu){
                     if(open){
                         new MenuAnimation(layout, comp).openMenu();
                     }else{
                          new MenuAnimation(layout, comp).closeMenu();
                     }
                     return true;
                 }else{
                   eventShowPopUpMenu.ShowPopUpMenu(comp);
                 }
             }
             return false;
           }
       };
   }
   public void initMenuItem(){
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/01.png")), "Dashboard", "Home", "Buttons", "Cards", "Tabs", "Accordions", "Modals"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/02.png")), "Charts", "Morris", "Flot", "Line"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/03.png")), "Report", "Income", "Expense", "Profit"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/04.png")), "Message", "Sender", "Inbox", "User"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/05.png")), "Staff", "Sender", "Inbox", "User"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/06.png")), "Student", "Menu 001", "Menu 002", "Menu 003"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/07.png")), "Library", "Menu 001", "Menu 002", "Menu 003"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/08.png")), "Holiday", "Menu 001", "Menu 002", "Menu 003"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/09.png")), "Calendar", "Menu 001", "Menu 002", "Menu 003"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/010.png")), "Chat App", "Menu 001", "Menu 002", "Menu 003"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/011.png")), "Contace", "Menu 001", "Menu 002", "Menu 003"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/012.png")), "File Manager", "Menu 001", "Menu 002", "Menu 003"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/013.png")), "Our Centres"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/lis/icon/014.png")), "Gallery"));
   }

    public void addEventMenu(EventMenu eventMenu) {
        this.eventMenu = eventMenu;
    }

   
   
    public void addEventShowPopUpMenu(EventShowPopUpMenu eventShowPopUpMenu) {
        this.eventShowPopUpMenu = eventShowPopUpMenu;
    }


    public void addEventmenuSelected(EventMenuSelected eventmenuSelected) {
        this.event = eventmenuSelected;
    }

   

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

   
    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setColorBackGround(Color colorBackGround) {
        this.colorBackGround = colorBackGround;
    }

    public Color getColorBackGround() {
        return colorBackGround;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profileLogo = new com.lis.main.ProfileLogo();
        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(250, 219, 224));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setViewportBorder(null);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profileLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profileLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /*Degradado del panel del menu*/
        GradientPaint gr = new GradientPaint(0, 0, new Color(189,21,34), getWidth(), 0, new Color(250,10,7));
        g2.setPaint(gr);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);

    }

    public void hideAllMenu(){
        for(Component comp : panel.getComponents()){
            MenuItem item = (MenuItem) comp;
            if(item.isOpen()){
                 new MenuAnimation(layout, comp,500 ).closeMenu();
                 item.setOpen(false);
                
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private com.lis.main.ProfileLogo profileLogo;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
