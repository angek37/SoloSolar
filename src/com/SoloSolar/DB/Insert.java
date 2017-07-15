package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.derby.jdbc.*;

import com.SoloSolar.Capsulas.*;


public class Insert {
    private static String dbURL = "jdbc:derby:db/;user=admin;password=solosolo";
    private static Connection conn = null;
    private static Statement stmt = null;
    

    public Insert() {
    	createConnection();
    }
    
    public boolean InsertCategory(Categoria cat) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("insert into Categoria(Nombre, Descripcion) values ('" +
            		cat.getNombre() + "','" + cat.getDescripcion() + "')");
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    	
    }
    
    public boolean UpdateCategory(Categoria cat) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("update Categoria set nombre = '" + cat.getNombre() + "',"
            		+ "descripcion = '" + cat.getDescripcion() + "' where id_cat = "+ cat.getId());
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
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