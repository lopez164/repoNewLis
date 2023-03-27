
import com.conf.Inicio;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lelopez
 */
public class PanelLogin extends javax.swing.JPanel {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Login.class);

    private int port; //= 9966;
    static ServerSocket serverSocket = null;
    int intentos = 0;
    Inicio ini;
    private Properties propiedades;

    /**
     * Creates new form Login
     */
    public PanelLogin() {
        try {
            propiedades = new Properties();
            propiedades.load(new FileReader("propiedadesLogistica.properties"));
            ini = new Inicio();
            cargarConfiguracion();

            if (singleAPP(port)) {
                initComponents();
                jpanelImagen1.setImageIcon("/img/logoAldPlus.png");
                setOpaque(false);

            } else {
                JOptionPane.showMessageDialog(this, "No se pudo iniciar la aplicacion porque \n hay otra instancia en ejecución", "Error...", JOptionPane.WARNING_MESSAGE);

                return;
            }

        } catch (Exception ex) {
            Logger.getLogger(PanelLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addEventLogin(ActionListener event) {
        cmd.addActionListener(event);
    }

    public boolean checkUser() {
        return !(txtUsuario.getText().trim().equals("") || String.valueOf(txtPassword.getPassword()).trim().equals(""));

    }

    public String getUserName() {
        return txtUsuario.getText().trim();
    }

    public String getPassword() {
        return String.valueOf(txtPassword.getPassword()).trim();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new com.lis.swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new com.lis.swing.PasswordField();
        cmd = new com.lis.swing.Button();
        jpanelImagen1 = new ui.swing.Panel.PanelImagen();

        txtUsuario.setLabelText("Usuario");
        txtUsuario.setLineColor(new java.awt.Color(189, 21, 34));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(189, 21, 34));
        jLabel1.setText("Login");

        txtPassword.setLabelText("PassWord");
        txtPassword.setLineColor(new java.awt.Color(189, 21, 34));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        cmd.setBackground(new java.awt.Color(189, 21, 34));
        cmd.setForeground(new java.awt.Color(255, 255, 255));
        cmd.setText("Login");
        cmd.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelImagen1Layout = new javax.swing.GroupLayout(jpanelImagen1);
        jpanelImagen1.setLayout(jpanelImagen1Layout);
        jpanelImagen1Layout.setHorizontalGroup(
            jpanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jpanelImagen1Layout.setVerticalGroup(
            jpanelImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmd, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jpanelImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanelImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPassword.requestFocus();
        }

    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private static boolean singleAPP(int port) {

        try {
            serverSocket = new java.net.ServerSocket(port);

        } catch (java.io.IOException ex) {
            return false;

        }
        return true;

    }

    private void cargarConfiguracion() {
        ini.setPropiedades(this.propiedades);

        ini.setRutaDeEntrada(Integer.parseInt(propiedades.getProperty("idOperador")));

        /*Datos del cliente*/
        ini.setNitCliente(propiedades.getProperty("nitCliente"));
        ini.setIdAliado(propiedades.getProperty("idAliado"));
        ini.setNombreDelCliente(propiedades.getProperty("nombreCliente"));
        ini.setDireccionCliente(propiedades.getProperty("direccionCliente"));
        ini.setCiudadCliente(propiedades.getProperty("ciudadCliente"));

        ini.setContactoCliente(propiedades.getProperty("contactoCliente"));
        ini.setEmailCliente(propiedades.getProperty("emailCliente"));
        ini.setTelefonoCliente(propiedades.getProperty("telefonoCliente"));
        ini.setCelularCliente(propiedades.getProperty("celularCliente"));

        ini.setPrefijos(propiedades.getProperty("prefijos"));
        ini.setuRLFuente(propiedades.getProperty("uRLFuente"));
        ini.setServidorFuente(propiedades.getProperty("servidorFuente"));
        ini.setDbFuente(propiedades.getProperty("dbFuente"));
        ini.setUserFuente(propiedades.getProperty("userFuente"));
        ini.setPsdFuente(propiedades.getProperty("psdFuente"));
        ini.setGeoPositionCliente(propiedades.getProperty("geoPositionCliente"));

        if (propiedades.getProperty("imprimirMinuta").equals("true")) {
            ini.setImprimirMinuta(true);
        } else {
            ini.setImprimirMinuta(false);
        }

        if (propiedades.getProperty("permitirVariosManifiestos").equals("true")) {
            ini.setPermitirVariosManifiestos(true);
        } else {
            ini.setPermitirVariosManifiestos(false);
        }

        ini.setCodigoAgencia(propiedades.getProperty("codigoAgencia"));

        port = Integer.parseInt(propiedades.getProperty("puerto"));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.lis.swing.Button cmd;
    private javax.swing.JLabel jLabel1;
    private ui.swing.Panel.PanelImagen jpanelImagen1;
    private com.lis.swing.PasswordField txtPassword;
    private com.lis.swing.TextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
