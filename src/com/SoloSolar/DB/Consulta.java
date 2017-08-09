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
			ResultSet results = stmt.executeQuery("SELECT PR.NOMBRE " 
					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO " 
					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
					+ "GROUP BY PR.NOMBRE");
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
    	String datos[][] = new String[cantidadVentas()][5];
    	Statement stmt2;
    	int count = 0;
    	createConnection();
    	try {
    		stmt = conn.createStatement();
    		stmt2 = conn.createStatement();
    		ResultSet results = stmt.executeQuery("SELECT PR.NOMBRE, SUM(R.CANTIDAD) " 
					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO " 
					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
					+ "GROUP BY PR.NOMBRE ORDER BY SUM(R.CANTIDAD) DESC");
    		double precio = 0;
    		while(results.next()) {
    			precio = 0;
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2);
    			ResultSet data = stmt2.executeQuery("SELECT SUM(R.CANTIDAD), PR.NOMBRE, PR.COSTO , R.PRECIO "  
    					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO "  
    					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
    					+ "WHERE PR.NOMBRE = '" + results.getString(1) + "' "
    					+ "GROUP BY PR.NOMBRE, PR.COSTO, R.PRECIO");
    			while(data.next()) {
    				datos[count][2] = data.getString(3);
    				precio = precio + (Double.parseDouble(data.getString(1)) * Double.parseDouble(data.getString(4)));
    			}
    			data.close();
    			datos[count][3] = round(precio, 1) + "";
    			datos[count][4] = round((Double.parseDouble(datos[count][3]) - 
    					(Double.parseDouble(datos[count][1]) * Double.parseDouble(datos[count][2]))), 1) + "";
    			count++;
    		}
    		results.close();
			shutdown();
			return datos;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			shutdown();
		}
    	return datos;
    }
    
    public static int cantidadVentasFechas(String inicio, String fin) {
    	int cantidad = 0;
    	createConnection();
    	try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT PR.NOMBRE, SUM(R.CANTIDAD) " 
					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO " 
					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
					+ "WHERE P.FECHA >= '" + inicio + "' AND P.FECHA <= '" + fin + "'"
					+ "GROUP BY PR.NOMBRE ORDER BY SUM(R.CANTIDAD) DESC");
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
    
    public static String[][] dataVentasFecha(String inicio, String fin) {
    	String datos[][] = new String[cantidadVentasFechas(inicio, fin)][5];
    	Statement stmt2;
    	int count = 0;
    	createConnection();
    	try {
    		stmt = conn.createStatement();
    		stmt2 = conn.createStatement();
    		ResultSet results = stmt.executeQuery("SELECT PR.NOMBRE, SUM(R.CANTIDAD) " 
					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO " 
					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
					+ "WHERE P.FECHA >= '" + inicio + "' AND P.FECHA <= '" + fin + "'"
					+ "GROUP BY PR.NOMBRE ORDER BY SUM(R.CANTIDAD) DESC");
    		double precio = 0;
    		while(results.next()) {
    			precio = 0;
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2);
    			ResultSet data = stmt2.executeQuery("SELECT SUM(R.CANTIDAD), PR.NOMBRE, PR.COSTO , R.PRECIO "  
    					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO "  
    					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
    					+ "WHERE PR.NOMBRE = '" + results.getString(1) + "' "
    							+ "AND P.FECHA >= '" + inicio + "' AND P.FECHA <= '" + fin + "'"
    					+ "GROUP BY PR.NOMBRE, PR.COSTO, R.PRECIO");
    			while(data.next()) {
    				datos[count][2] = data.getString(3);
    				precio = precio + (Double.parseDouble(data.getString(1)) * Double.parseDouble(data.getString(4)));
    			}
    			data.close();
    			datos[count][3] = round(precio, 1) + "";
    			datos[count][4] = round((Double.parseDouble(datos[count][3]) - 
    					(Double.parseDouble(datos[count][1]) * Double.parseDouble(datos[count][2]))), 1) + "";
    			count++;
    		}
    		results.close();
			shutdown();
			return datos;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			shutdown();
		}
    	return datos;
    }
    
    public static int cantidadVentasPedido(String inicio, String fin) {
    	int cantidad = 0;
    	createConnection();
    	try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT PR.NOMBRE, SUM(R.CANTIDAD) " 
					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO " 
					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
					+ "WHERE P.ID_PEDIDO >= " + Integer.parseInt(inicio) + " "
						+ "AND P.ID_PEDIDO <= " + Integer.parseInt(fin) + " "
					+ "GROUP BY PR.NOMBRE ORDER BY SUM(R.CANTIDAD) DESC");
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
    
    public static String[][] dataVentasPedido(String inicio, String fin) {
    	String datos[][] = new String[cantidadVentasPedido(inicio, fin)][5];
    	Statement stmt2;
    	int count = 0;
    	createConnection();
    	try {
    		stmt = conn.createStatement();
    		stmt2 = conn.createStatement();
    		ResultSet results = stmt.executeQuery("SELECT PR.NOMBRE, SUM(R.CANTIDAD) " 
					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO " 
					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
					+ "WHERE P.ID_PEDIDO >= " + Integer.parseInt(inicio) + " "
						+ "AND P.ID_PEDIDO <= " + Integer.parseInt(fin) + " "
					+ "GROUP BY PR.NOMBRE ORDER BY SUM(R.CANTIDAD) DESC");
    		double precio = 0;
    		while(results.next()) {
    			precio = 0;
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2);
    			ResultSet data = stmt2.executeQuery("SELECT SUM(R.CANTIDAD), PR.NOMBRE, PR.COSTO , R.PRECIO "  
    					+ "FROM PEDIDO AS P JOIN RENGLON AS R ON P.ID_PEDIDO = R.PEDIDO "  
    					+ "JOIN PRODUCTO AS PR ON PR.CLAVE = R.ID_PROD "
    					+ "WHERE PR.NOMBRE = '" + results.getString(1) + "' "
    					+ "AND P.ID_PEDIDO >= " + Integer.parseInt(inicio) + " "
    					+ "AND P.ID_PEDIDO <= " + Integer.parseInt(fin) + " "
    					+ "GROUP BY PR.NOMBRE, PR.COSTO, R.PRECIO");
    			while(data.next()) {
    				datos[count][2] = data.getString(3);
    				precio = precio + (Double.parseDouble(data.getString(1)) * Double.parseDouble(data.getString(4)));
    			}
    			data.close();
    			datos[count][3] = round(precio, 1) + "";
    			datos[count][4] = round((Double.parseDouble(datos[count][3]) - 
    					(Double.parseDouble(datos[count][1]) * Double.parseDouble(datos[count][2]))), 1) + "";
    			count++;
    		}
    		results.close();
			shutdown();
			return datos;
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			shutdown();
		}
    	return datos;
    }
    
    public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
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
    		ResultSet results = stmt.executeQuery("SELECT ID_CUS, FIRSTNAME, LASTNAME, CIUDAD FROM CLIENTE");
    		while(results.next()) {
    			datos[count][0] = results.getString(1);
    			datos[count][1] = results.getString(2) + " " + results.getString(3);
    			datos[count][2] = results.getString(4);
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
    
    public Pedido[] selectOrders() {
    	createConnection();
    	Pedido[] p = new Pedido[0];
    	Pedido[] aux;
    	try {
    		stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select id_Pedido, Fecha, customer, CLIENTE.FIRSTNAME || ' ' || CLIENTE.LASTNAME, IVA "
            		+"from PEDIDO join CLIENTE on customer = id_cus order by Fecha desc");
            while(results.next()) {
            	aux = p;
            	p = new Pedido[aux.length+1];
            	for(int x = 0; x < aux.length; x++) {
            		p[x] = aux[x];
            	}
            	p[aux.length] = new Pedido();
            	p[aux.length].setId(results.getInt(1));
            	p[aux.length].setFecha(results.getString(2));
            	p[aux.length].setCustomer(results.getInt(3));
            	p[aux.length].setClienteString(results.getString(4));
            	p[aux.length].setIva(results.getBoolean(5));
            }
            results.close();
            
            for(int x = 0; x < p.length; x++) {
            	results = stmt.executeQuery("select SUM(Cantidad*Precio) from Renglon where Pedido = "+p[x].getId());
                while(results.next()) {
                	if(p[x].getIva()) {
                		p[x].setTotal(round(1.16*results.getDouble(1),1));
                	}else {
                		p[x].setTotal(round(results.getDouble(1),1));
                	}
                }
                results.close();
            }
                                    
            stmt.close();
            shutdown();
            return p;
    	}catch(SQLException sqlExcept) {
    		sqlExcept.printStackTrace();
            return null;
    	}
    }
    
    public Pedido selectOrder(String id_pedido) {
    	Pedido p = new Pedido();
    	try {
    		createConnection();
    		stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select customer, Fecha, IVA, Observaciones,"+
            		" Cliente.FirstName || ' ' || Cliente.LastName from Pedido join Cliente on customer = id_cus where id_Pedido = "+id_pedido);
            while(results.next()) {
            	p.setCustomer(results.getInt(1));
            	p.setFecha(results.getString(2));
            	p.setIva(results.getBoolean(3));
            	p.setObservaciones(results.getString(4));
            	p.setClienteString(results.getString(5));
            }
            p.setId(Integer.parseInt(id_pedido));
            results.close();        
            stmt.close();
            shutdown();
            return p;
    	}catch(SQLException sqlExcept) {
    		sqlExcept.printStackTrace();
            return null;
    	}
    }
    
    public String[][] selectRowsOrder(String id_pedido) {
    	String[][] renglones = new String[0][0];
    	String[][] aux;
    	try {
    		createConnection();
    		stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select id_prod, Nombre, Cantidad, Paquete, Lista, Precio, Cantidad*Precio"+
            		" from Renglon join Producto on id_prod = Clave where Pedido = "+id_pedido);
            while(results.next()) {
            	aux = renglones;
            	renglones = new String[aux.length+1][7];
            	for(int x = 0; x < aux.length; x++) {
            		for(int y = 0; y < aux[x].length; y++) {
            			renglones[x][y] = aux[x][y];
            		}
            	}
            	renglones[aux.length][0] = results.getString(1);
            	renglones[aux.length][1] = results.getString(2);
            	renglones[aux.length][2] = Integer.toString(results.getInt(3));
            	renglones[aux.length][3] = Integer.toString(results.getInt(4));
            	renglones[aux.length][4] = Integer.toString(results.getInt(5));
            	renglones[aux.length][5] = Double.toString(results.getDouble(6));
            	renglones[aux.length][6] = Double.toString(round(results.getDouble(7), 1));
            }
            results.close();        
            stmt.close();
            shutdown();
            return renglones;
    	}catch(SQLException sqlExcept) {
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