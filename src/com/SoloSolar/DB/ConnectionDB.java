package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.jdbc.*;


public class ConnectionDB {
    private static String dbURL = "jdbc:derby:db/;user=admin;password=solosolo";
    private static Connection conn = null;
    private static Statement stmt = null;

    public ConnectionDB() {
    	  createConnection();
    }
    
    
    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except) {
        	System.out.println("error de conexión");
            except.printStackTrace();
        }
    }
    
    public String getPassword(String table, String user) {
    	String r = "";
    	try {
          stmt = conn.createStatement();
          ResultSet results = stmt.executeQuery("select Password from Usuario where Usuario = '" + user + "'");
          results.next();
          r = results.getString(1);
          stmt.close();
          results.close();
        }
      catch (SQLException sqlExcept) {
          sqlExcept.printStackTrace();
      }
    	shutdown();
    	return r;
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
//    
//    public static void main(String[] mr) {
//    	new ConnectionDB();
//    }
}