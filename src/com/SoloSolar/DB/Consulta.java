package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    public static int cantidadDatos() {
    	int cantidad = 0;
    	createConnection();
    	try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM PRODUCTO");
			while(results.next()) {
				cantidad++;
			}
			shutdown();
			return cantidad;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
    	return cantidad;
    }
    
    public static String[][] dataProducts() {
    	String datos[][] = new String[cantidadDatos()][5];
    	int count = 0;
    	createConnection();
    	try {
    		stmt = conn.createStatement();
    		ResultSet results = stmt.executeQuery("SELECT P.CLAVE, P.NOMBRE, C.NOMBRE, P.PRECIO1, P.PRECIO2 "
    				+ "FROM PRODUCTO AS P JOIN CATEGORIA AS C ON C.ID_CAT = P.CATEGORIA");
    		while(results.next()) {
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2);
    			datos[count][2] = results.getString(3);
    			datos[count][3] = results.getString(4);
    			datos[count][4] = results.getString(5);
    			count++;
    		}
			shutdown();
			return datos;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			shutdown();
		}
    	return datos;
    }
    
    public static int cantidadVentas() {
    	int cantidad = 0;
    	createConnection();
    	try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM PEDIDO AS P");
			while(results.next()) {
				cantidad++;
			}
			shutdown();
			return cantidad;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
    	return cantidad;
    }
    
    public static String[][] dataVentas() {
    	String datos[][] = new String[cantidadDatos()][5];
    	int count = 0;
    	createConnection();
    	try {
    		stmt = conn.createStatement();
    		ResultSet results = stmt.executeQuery("SELECT P.CLAVE, P.NOMBRE, C.NOMBRE, P.PRECIO1, P.PRECIO2 "
    				+ "FROM PRODUCTO AS P JOIN CATEGORIA AS C ON C.ID_CAT = P.CATEGORIA");
    		while(results.next()) {
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2);
    			datos[count][2] = results.getString(3);
    			datos[count][3] = results.getString(4);
    			datos[count][4] = results.getString(5);
    			count++;
    		}
			shutdown();
			return datos;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			shutdown();
		}
    	return datos;
    }
    
    public static String[][] dataProductsP() {
    	String datos[][] = new String[cantidadDatos()][6];
    	int count = 0;
    	createConnection();
    	try {
    		stmt = conn.createStatement();
    		ResultSet results = stmt.executeQuery("SELECT P.CLAVE, P.NOMBRE, C.NOMBRE, P.PAQUETE, P.PRECIO1,"
    				+ "P.PRECIO2 FROM PRODUCTO AS P JOIN CATEGORIA AS C ON C.ID_CAT = P.CATEGORIA");
    		while(results.next()) {
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2);
    			datos[count][2] = results.getString(3);
    			datos[count][3] = results.getString(4);
    			datos[count][4] = results.getString(5);
    			datos[count][5] = results.getString(6);
    			count++;
    		}
			shutdown();
			return datos;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			shutdown();
		}
    	return datos;
    }
    
    public static int cantidadClientes() {
    	int cantidad = 0;
    	createConnection();
    	try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM CLIENTE");
			while(results.next()) {
				cantidad++;
			}
			shutdown();
			return cantidad;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
    	return cantidad;
    }
    
    public static String[][] dataClientes() {
    	String datos[][] = new String[cantidadClientes()][3];
    	int count = 0;
    	createConnection();
    	try {
    		stmt = conn.createStatement();
    		ResultSet results = stmt.executeQuery("SELECT ID_CUS, FIRSTNAME, CIUDAD FROM CLIENTE");
    		while(results.next()) {
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2);
    			datos[count][2] = results.getString(3);
    			count++;
    		}
			shutdown();
			return datos;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			shutdown();
		}
    	return datos;
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
    
    public Producto[] SelectProductsBySupplier(int id_pro) {
    	createConnection();
    	Producto[] prod = new Producto[0];
    	Producto[] aux;
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select Producto.Clave, Producto.Nombre, Categoria.Nombre"
            		+", Paquete, Costo, Precio1, Precio2 from Producto_Proveedor join Producto on "
            			+"Producto_Proveedor.Clave = Producto.Clave join Categoria on "
            				+"Producto.Categoria = id_cat where id_pro = "+id_pro);
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
            	prod[c].setCategoriaNombre(results.getString(3));
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
    
    public Proveedor[] selectSupplier(){
    	createConnection();
    	Proveedor[] prov = new Proveedor[0];
    	Proveedor[] aux;
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from Proveedor");
            int c = 0;
            while(results.next()) {
            	aux = new Proveedor[prov.length];
            	for(int x = 0; x < prov.length; x++) {
            		aux[x] = prov[x];
            	}
            	prov = new Proveedor[c+1];
            	for(int x = 0; x < aux.length; x++) {
            		prov[x] = aux[x];
            	}
            	prov[c] = new Proveedor();
            	prov[c].setId(results.getInt(1));
            	prov[c].setNombre(results.getString(2));
            	prov[c].setDireccion(results.getString(3));
            	prov[c].setTelefono(results.getString(4));
            	prov[c].setEmail(results.getString(5));
            	c++;
            }
            aux = null;
            results.close();
            stmt.close();
            shutdown();
            return prov;
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return null;
        }
    }
    
    public String[][] selectProductSuppliers(String Clave){
    	createConnection();
    	String datos[][] = {};
    	String aux[][];
    	try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select id_pro, Proveedor.Nombre "
            		+"from Producto_Proveedor join Proveedor on id_pro = id_p where Clave = '" + Clave + "'");
            int c = 0;
            while(results.next()) {
            	aux = new String[datos.length][2];
            	aux = datos;
            	datos = new String [c+1][2];
            	for(int x = 0; x < aux.length; x++) {
            		datos[x][0] = aux[x][0];
            		datos[x][1] = aux[x][1];
            	}
            	datos[c][0] = Integer.toString(results.getInt(1));
            	datos[c][1] = results.getString(2);
            	c++;
            }
            results.close();
            stmt.close();
            shutdown();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return null;
        }
    	return datos;
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