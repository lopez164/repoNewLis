
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public abstract class Conexiones {

    String cadena;
   

    public Conexiones() {

    }

    public static Connection GetConnection(String cadena, String usuario, String clave) {
        Connection conexion = null;
        try {

            conexion = DriverManager.getConnection(cadena, usuario, clave);

        } catch (SQLException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error2 en la Conexión con la BD " + ex.getMessage(), "Error en la conexión ", JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            return conexion;
        }
    }

    public static Connection GetConnection(String cadena, String usuario, String clave, String claseOrigen) {
        Connection conexion = null;
        try {
           conexion = DriverManager.getConnection(cadena, usuario, clave);

        } catch (SQLException ex) {
           Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null, "Errorde Conexión con la BD en la clase  " + claseOrigen + "\n " + ex.getMessage(), "Error en la conexión ", JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            return conexion;
        }
    }

    public static boolean hayConexion(String cadena, String usuario, String clave) {
        boolean conx = true;
        Connection conexion = null;
        String sql = "";
        try {
            conexion = DriverManager.getConnection(cadena, usuario, clave);
            conx = true;

        } catch (SQLException ex) {
            
         Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);

             JOptionPane.showMessageDialog(null,  "Error 2 en la Conexión con servidor ","Error en la conexión ", JOptionPane.ERROR_MESSAGE);
            conexion = null;
            conx = false;
        } finally {
            return conx;
        }
    }

    public static Connection GetConnectionDeConfiguracion(String host, String usuario, String clave, String objeto) {
        Connection conexion = null;
       
        try {
            conexion = DriverManager.getConnection(host, usuario, clave);

        } catch (SQLException ex) {
           Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);

            //JOptionPane.showMessageDialog(null, "Errorde Conexión con la BD en la clase MisClientes \n " + ex.getMessage(), "Error en la conexión ", JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            return conexion;
        }
    }

}
