package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.jdbc.*;

import com.SoloSolar.Capsulas.*;


public class Consulta {
    private static String dbURL = "jdbc:derby:db/;user=admin;password=solosolo";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public Consulta() {
    	createConnection();
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
    
    public Categoria[] selectCategories(){
    	Categoria[] cat = new Categoria[1];
    	Categoria[] aux;
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from Categoria");
//            ResultSetMetaData rsmd = results.getMetaData();
//            int numberCols = rsmd.getColumnCount();            
//            for (int i=1; i<=numberCols; i++) {
//                //print Column Names
//                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
//            }
            int c = 0;
            while(results.next()) {
            	cat[c] = new Categoria();
            	cat[c].setId(results.getInt(1));
            	cat[c].setNombre(results.getString(2));
            	cat[c].setDescripcion(results.getString(3));
            	aux = new Categoria[cat.length];
            	for(int x = 0; x < cat.length; x++) {
            		aux[x] = cat[x];
            	}
            	cat = new Categoria[c+1];
            	for(int x = 0; x < aux.length; x++) {
            		cat[x] = aux[x];
            	}
            	c++;
            }
            aux = null;
            results.close();
            stmt.close();
            shutdown();
            return cat;
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return null;
        }
    }
    
    public static void shutdown() {
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