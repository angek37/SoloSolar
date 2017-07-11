package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.jdbc.*;

import javax.swing.JOptionPane;

import com.SoloSolar.Capsulas.Usuario;

public class UsuarioBD {
	private static String dbURL = "jdbc:derby:db/;user=admin;password=solosolo";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static void ModificarUsuario(Usuario cli) {
    	createConnection();
    	try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate("");
            
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    	shutdown();
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
