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
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean ChangeCategory(int old, int sustitute) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("update Producto set Categoria = " + sustitute + " where Categoria = "+ old);
            stmt.close();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }

    public boolean DeleteCategory(int id_cat) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("delete from Categoria where id_cat = "+id_cat);
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean InsertProduct(Producto prod) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("insert into Producto values('" +prod.getClave()+ "','" +prod.getNombre()+ "'," +prod.getCategoria()+ "," 
            		+prod.getPaquete()+ "," +prod.getCosto()+ "," +prod.getPrecio1()+ "," +prod.getPrecio2()+ ")");
            stmt.close();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    	
    }
    
    public boolean InsertProductSupplier(String clave, String[][] suppliers) {
    	try {
            stmt = conn.createStatement();
            for(int x = 0; x < suppliers.length; x++) {
            	stmt.execute("insert into Producto_Proveedor values('"+ clave +"', " + suppliers[x][0] + ")");
    		}
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean UpdateProductSupplier(String clave, String[][] suppliers) {
    	try {
    		createConnection();
            stmt = conn.createStatement();
            stmt.execute("delete from Producto_Proveedor where clave = '" + clave 
            		+"'");
            for(int x = 0; x < suppliers.length; x++) {
            	stmt.execute("insert into Producto_Proveedor values('"+ clave +"', " + suppliers[x][0] + ")");
    		}
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean UpdateProduct(Producto prod) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("update Producto set Nombre = '" + prod.getNombre() + "',"
            		+ "Categoria = " + prod.getCategoria() + ",Paquete = "+ 
            			prod.getPaquete()+",Costo = "+ prod.getCosto() +",Precio1 = "+
            				prod.getPrecio1()+",Precio2 = "+
            					prod.getPrecio2()+" where Clave = '"+ prod.getClave()+"'");
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteProduct(String clave) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("delete from Producto where Clave = '"+clave+"'");
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean InsertSupplier(Proveedor prov) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("insert into Proveedor(Nombre, Direccion, Telefono, Email) values ('" +
            		prov.getNombre() + "','" + prov.getDireccion() + "','" + prov.getTelefono() + "','" 
            			+ prov.getEmail() + "')");
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean UpdateSupplier(Proveedor p) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("update Proveedor set Nombre = '" + p.getNombre() + "',"
            		+ "Direccion = '" + p.getDireccion() + "',Telefono = '"+ 
            			p.getTelefono()+"',Email = '"+ p.getEmail() +"' where id_p = "+ p.getId());
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteProdProv(int id_pro) {
    	try {
            stmt = conn.createStatement();
            stmt.execute("delete from Producto_Proveedor where id_pro = " + id_pro);
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteSupplier(int id_pro) {
    	try {
    		createConnection();
            stmt = conn.createStatement();
            stmt.execute("delete from Proveedor where id_p = " + id_pro);
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return false;
        }
    }
    
     public int InsertOrder(Pedido p) {
    	 int id = -1;
    	 try {
    		 createConnection();
             stmt = conn.createStatement();
             stmt.executeUpdate("insert into Pedido(customer, Fecha, Observaciones) values"
            		 +"("+p.getCustomer()+",'"+p.getFecha()+"','"+p.getObservaciones()+"')", Statement.RETURN_GENERATED_KEYS);
             ResultSet r = stmt.getGeneratedKeys();
             while(r.next()) {
            	 id = Integer.parseInt(Long.toString(r.getLong(1)));
             }
             stmt.close();
             shutdown();
             return id;
    	 }catch(SQLException sqlExcept) {
    		 sqlExcept.printStackTrace();
    		 return -1;
    	 }
     }
     
     public boolean InsertRowsOrder(int id_pedido, String datos[][]) {
    	 try {
    		 createConnection();
    		 stmt = conn.createStatement();
    		 for(int x = 0; x < datos.length; x++) {
    			 if(datos[x][0] != null && datos[x][2] != null) {
    				 if(!datos[x][2].equals("")) {
    					 stmt.execute("insert into Renglon(Pedido, id_prod, Precio, Cantidad) "
        						 +"values("+id_pedido+",'"+datos[x][0]+"',"+
        						 	datos[x][5]+","+datos[x][2]+")");
    				 }
    			 }
    		 }
    		 stmt.close();
    		 shutdown();
    		 return true;
    	 }catch(SQLException sqlExcept) {
    		 sqlExcept.printStackTrace();
    		 return false;
    	 }
     }
     
     public boolean UpdateOrder(Pedido p) {
    	 try {
    		 createConnection();
    		 stmt = conn.createStatement();
    		 stmt.execute("update Pedido set customer = "+p.getCustomer()+", Fecha = '"
    				 +p.getFecha()+"', Observaciones = '"+p.getObservaciones()+"' where id_Pedido = "+p.getId());
    		 stmt.close();
    		 shutdown();
    		 return true;
    	 }catch(SQLException sqlExcept) {
    		 sqlExcept.printStackTrace();
    		 return false;
    	 }
     }
     
     public boolean UpdateRowsOrder(int id, String[][] datos) {
    	 try {
    		 createConnection();
    		 stmt = conn.createStatement();
    		 stmt.execute("delete from Renglon where Pedido = "+id);
    		 for(int x = 0; x < datos.length; x++) {
    			 if(datos[x][0] != null && datos[x][2] != null) {
    				 if(!datos[x][2].equals("")) {
    					 stmt.execute("insert into Renglon(Pedido, id_prod, Precio, Cantidad) "
        						 +"values("+id+",'"+datos[x][0]+"',"+
        						 	datos[x][5]+","+datos[x][2]+")");
    				 }
    			 }
    		 }
    		 stmt.close();
    		 shutdown();
    		 return true;
    	 }catch(SQLException sqlExcept) {
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