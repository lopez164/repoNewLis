/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.lis.main.menu.Model_user;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import static java.lang.Math.pow;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author lelopez
 */
public class PanelLoading extends javax.swing.JLayeredPane {

    private final Animator animator;
    private boolean slideLeft;
    private float animate;
    private boolean isMessage;
    private Model_user data;

    public PanelLoading() {
        initComponents();
        loading.setVisible(true);
        profile.setVisible(false);
        message.setVisible(false);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (isMessage) {
                    message.setVisible(true);

                } else {
                    profile.setVisible(true);
                }
            }

            @Override
            public void timingEvent(float fraction) {
                if (isMessage) {
                    message.setAlpha(fraction);
                    loading.setAlpha(1f - fraction);
                } else {
                    profile.setAlpha(fraction);
                     loading.setAlpha(1f - fraction);
                }
                repaint();

            }

            @Override
            public void end() {
                super.end(); //To change body of generated methods, choose Tools | Templates.
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        // setOpaque(false);
    }

    public void addEventBack(ActionListener evt) {
        cmdCancel.addActionListener(evt);
        cmdCancel1.addActionListener(evt);
        cmdCancel2.addActionListener(evt);
    }

    public void setAnimate(boolean slideLeft, float animate) {
        this.slideLeft = slideLeft;
        this.animate = animate;
    }

    public void doneLogin(Model_user data){
        isMessage = false;
        this.data = data;
        pic.setIcon(data.getProfile());
        cmdContinue.setText("continuar como " + data.getUserName());
        animator.start();
    }
    
    public void addEventContinue(ActionListener evt){
        cmdContinue.addActionListener(evt);
        
    }
    
    public void showMessageErro(String msg){
        lblMessage.setText(msg);
        isMessage = true;
        animator.start();
    }
    
    public void reset(){
        loading.setAlpha(1f);
        loading.setVisible(true);
        profile.setVisible(false);
        message.setVisible(false);
                
        
    }

    public Model_user getData() {
        return data;
    }
            
            
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loading = new com.lis.swing.PanelTransparent();
        jLabel1 = new javax.swing.JLabel();
        cmdCancel = new com.lis.swing.Button();
        profile = new com.lis.swing.PanelTransparent();
        cmdCancel1 = new com.lis.swing.Button();
        pic = new com.lis.swing.ImageAvatar();
        cmdContinue = new com.lis.swing.Button();
        message = new com.lis.swing.PanelTransparent();
        lblMessage = new javax.swing.JLabel();
        cmdCancel2 = new com.lis.swing.Button();

        setLayout(new java.awt.CardLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lis/icon/loading.gif"))); // NOI18N

        cmdCancel.setForeground(new java.awt.Color(250, 10, 7));
        cmdCancel.setText("go Back");
        cmdCancel.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        javax.swing.GroupLayout loadingLayout = new javax.swing.GroupLayout(loading);
        loading.setLayout(loadingLayout);
        loadingLayout.setHorizontalGroup(
            loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(loadingLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        loadingLayout.setVerticalGroup(
            loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loadingLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(loading, "card2");

        cmdCancel1.setBackground(new java.awt.Color(255, 10, 7));
        cmdCancel1.setForeground(new java.awt.Color(255, 255, 255));
        cmdCancel1.setText("go Back");
        cmdCancel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cmdCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancel1ActionPerformed(evt);
            }
        });

        pic.setBackground(new java.awt.Color(67, 99, 132));
        pic.setForeground(new java.awt.Color(146, 129, 129));
        pic.setBorderSize(3);
        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lis/icon/p1.jpg"))); // NOI18N

        cmdContinue.setBackground(new java.awt.Color(255, 10, 7));
        cmdContinue.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cmdContinue.setForeground(new java.awt.Color(255, 255, 255));
        cmdContinue.setText("Continue");
        cmdContinue.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        javax.swing.GroupLayout profileLayout = new javax.swing.GroupLayout(profile);
        profile.setLayout(profileLayout);
        profileLayout.setHorizontalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profileLayout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addGroup(profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdCancel1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdContinue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        profileLayout.setVerticalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(cmdCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        add(profile, "card2");

        lblMessage.setBackground(new java.awt.Color(67, 99, 132));
        lblMessage.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblMessage.setForeground(java.awt.Color.white);
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("Error");

        cmdCancel2.setBackground(new java.awt.Color(255, 10, 7));
        cmdCancel2.setForeground(new java.awt.Color(255, 255, 255));
        cmdCancel2.setText("go Back");
        cmdCancel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cmdCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout messageLayout = new javax.swing.GroupLayout(message);
        message.setLayout(messageLayout);
        messageLayout.setHorizontalGroup(
            messageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(messageLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(cmdCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        messageLayout.setVerticalGroup(
            messageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, messageLayout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(cmdCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        add(message, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdCancel1ActionPerformed

    private void cmdCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdCancel2ActionPerformed

    @Override
    public void paint(Graphics g) {
        //To change body of generated methods, choose Tools | Templates
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int width = getWidth();
        int height = getHeight();
        int x = 0;//easeOutQuint(animate) * width;
        int y = 0;
        if (slideLeft) {
            int centerY = height / 2;
            Path2D.Float p = new Path2D.Float();
            p.moveTo(x, y);
            p.lineTo(width, y);
            p.lineTo(width, height);
            p.lineTo(x, height);
            p.curveTo(x, height, easeOutBounce(animate) * width, centerY, x, y);

            g2.fill(p);
        } else {
            g2.fillRect(x, y, width, height);
        }
        g2.dispose();
        super.paint(g);

    }

    private float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;

        double v;

        if (x < 1 / d1) {
            v = n1 * x * x;
        } else if (x < 2 / d1) {
            v = n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            v = n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            v = n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }

    }

    private float easeOutQuint(float x) {
        double v;
        v = 1 - pow(1 - x, 5);

        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.lis.swing.Button cmdCancel;
    private com.lis.swing.Button cmdCancel1;
    private com.lis.swing.Button cmdCancel2;
    private com.lis.swing.Button cmdContinue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblMessage;
    private com.lis.swing.PanelTransparent loading;
    private com.lis.swing.PanelTransparent message;
    private com.lis.swing.ImageAvatar pic;
    private com.lis.swing.PanelTransparent profile;
    // End of variables declaration//GEN-END:variables
}
