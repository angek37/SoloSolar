package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.jdbc.*;

import javax.swing.JOptionPane;

import com.SoloSolar.Capsulas.Usuario;

public class UsuarioBD {
	private static String dbURL = "jdbc:derby:db/;user=admin;password=solosolo";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static void ModificarUsuario(Usuario us) {
    	createConnection();
    	try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE USUARIO SET NOMBRE = '" + us.getNombre() + "', "
            		+ "USUARIO = '" + us.getUsuario() + "', "
            		+ "PASSWORD = '" + us.getPassword() + "', "
            		+ "RFC = '" + us.getRFC() + "'");
            
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente");
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    	shutdown();
    }
    
    public static Usuario Datos() {
    	Usuario u = new Usuario();
    	createConnection();
    	try {
    		stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO");
			while(rs.next()) {
				u.setUsuario(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setNombre(rs.getString(3));
				u.setRFC(rs.getString(4));
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	shutdown();
    	return u;
    }
    
    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept) {
            
        }

    }
}
