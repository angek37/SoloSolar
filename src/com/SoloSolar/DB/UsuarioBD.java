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
            		+ "PASSWORD = '" + us.getPassword() + "'");
            
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente");
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    	shutdown();
    }
    
    public static String getNombre() {
    	createConnection();
    	String nombre = "";
    	try {
    		stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NOMBRE FROM USUARIO");
			while(rs.next()) {
				nombre = rs.getString(1);
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	shutdown();
    	return nombre;
    }
    
    public static String getUsuario() {
    	createConnection();
    	String usuario = "";
    	try {
    		stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT USUARIO FROM USUARIO");
			while(rs.next()) {
				usuario = rs.getString(1);
			}
	    } catch (SQLException e) {
			e.printStackTrace();
		}
    	shutdown();
    	return usuario;
    }
    
    public static String getPass() {
    	createConnection();
    	String pass = "";
    	try {
    		stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM USUARIO");
			while(rs.next()) {
				pass = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	shutdown();
    	return pass;
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
