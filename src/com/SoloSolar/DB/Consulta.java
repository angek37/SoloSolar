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
    	createConnection();
    	Categoria[] cat = new Categoria[0];
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
            	aux = new Categoria[cat.length];
            	for(int x = 0; x < cat.length; x++) {
            		aux[x] = cat[x];
            	}
            	cat = new Categoria[c+1];
            	for(int x = 0; x < aux.length; x++) {
            		cat[x] = aux[x];
            	}
            	cat[c] = new Categoria();
            	cat[c].setId(results.getInt(1));
            	cat[c].setNombre(results.getString(2));
            	cat[c].setDescripcion(results.getString(3));
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
    
    public Producto[] seekProducts(String product) {
    	product = product.toUpperCase();
    	createConnection();
    	Producto[] prod = new Producto[0];
    	Producto[] aux;
    	try {
			stmt = conn.createStatement();
			ResultSet results = product.equals("") 
					? stmt.executeQuery("SELECT P.CLAVE, P.NOMBRE, C.NOMBRE, P.PRECIO1, P.PRECIO2 "
							+ "FROM PRODUCTO AS P JOIN CATEGORIA AS C ON C.ID_CAT = P.CATEGORIA")
					: stmt.executeQuery("SELECT P.CLAVE, P.NOMBRE, C.NOMBRE, P.PRECIO1, P.PRECIO2 "
							+ "FROM PRODUCTO AS P JOIN CATEGORIA AS C ON C.ID_CAT = P.CATEGORIA "
							+ "WHERE UPPER(P.NOMBRE) LIKE '%" + product + "%' "
							+ "OR UPPER(P.NOMBRE) LIKE '" + product + "%' "
							+ "OR UPPER(P.NOMBRE) LIKE '%" + product + "'");
			int c = 0;
			/*case 0: 
		    	  return aux = "Clave";
		      case 1: 
		    	  return aux = "Nombre";
		      case 2:
		    	  return aux = "Categoria";
		      case 3: 
		    	  return aux = "Costo";
		      case 4:
		    	  return aux = "Precio 1";
		      case 5:
		    	  return aux = "Precio 2";
		      }*/
            while(results.next()) {
            	aux = new Producto[prod.length];
            	for(int x = 0; x < prod.length; x++) {
            		aux[x] = prod[x];
            	}
            	prod = new Producto[c+1];
            	for(int x = 0; x < aux.length; x++) {
            		prod[x] = aux[x];
            	}
            	prod[c] = new Producto();
            	prod[c].setClave(results.getString(1));
            	prod[c].setNombre(results.getString(2));
            	prod[c].setCategoriaNombre(results.getString(3));
            	prod[c].setPrecio1(results.getDouble(4));
            	prod[c].setPrecio2(results.getDouble(5));
            	c++;
            }
            aux = null;
            results.close();
            stmt.close();
            shutdown();
            return prod;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			return null;
		}
    }
    
    public Producto[] selectProducts(){
    	createConnection();
    	Producto[] prod = new Producto[0];
    	Producto[] aux;
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from Producto");
            int c = 0;
            while(results.next()) {
            	aux = new Producto[prod.length];
            	for(int x = 0; x < prod.length; x++) {
            		aux[x] = prod[x];
            	}
            	prod = new Producto[c+1];
            	for(int x = 0; x < aux.length; x++) {
            		prod[x] = aux[x];
            	}
            	prod[c] = new Producto();
            	prod[c].setClave(results.getString(1));
            	prod[c].setNombre(results.getString(2));
            	prod[c].setCategoria(results.getInt(3));
            	prod[c].setPaquete(results.getInt(4));
            	prod[c].setCosto(results.getDouble(5));
            	prod[c].setPrecio1(results.getDouble(6));
            	prod[c].setPrecio2(results.getDouble(7));
            	c++;
            }
            aux = null;
            results.close();
            stmt.close();
            shutdown();
            return prod;
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