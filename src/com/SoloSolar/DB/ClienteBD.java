package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.SoloSolar.Capsulas.Cliente;

public class ClienteBD {
	private static String dbURL = "jdbc:derby:db/;user=admin;password=solosolo";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static void InsertCliente(Cliente cli) {
    	createConnection();
    	try
        {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO CLIENTE(RFC, FIRSTNAME, LASTNAME, CALLE, NUMERO, COLONIA, CP, CIUDAD, ESTADO, EMAIL,"
            		+ "TEL_CELULAR, TEL_EMPRESA) VALUES ('" + cli.getRFC() + "', '" + cli.getNombre() + "',"
            		+ "'" + cli.getApellidos() +  "', '" + cli.getCalle() + "', '" + cli.getNoDir() + "', '" 
            		+ cli.getColonia() + "', " + cli.getCP() + ", '" + cli.getCiudad() + "', '" 
            		+ cli.getEstado() + "', " + "'" + cli.getEmail() + "', '" + cli.getTelefono() + "', '"
        			+ cli.getTelEmp() + "')");
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    	shutdown();
    }
    
    public static Cliente getCliente(int id) {
    	Cliente c = new Cliente();
    	createConnection();
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT RFC, FIRSTNAME, LASTNAME, CALLE, NUMERO, COLONIA, CP, CIUDAD, "
					+ "ESTADO, EMAIL, TEL_CELULAR, TEL_EMPRESA FROM CLIENTE WHERE ID_CUS = " + id);
			while(rs.next()) {
				c.setRFC(rs.getString(1));
				c.setNombre(rs.getString(2));
				c.setApellidos(rs.getString(3));
				c.setCalle(rs.getString(4));
				c.setNoDir(rs.getString(5));
				c.setColonia(rs.getString(6));
				c.setCP(rs.getString(7));
				c.setCiudad(rs.getString(8));
				c.setEstado(rs.getString(9));
				c.setEmail(rs.getString(10));
				c.setTelefono(rs.getString(11));
				c.setTelEmp(rs.getString(12));
			}
			shutdown();
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return c;
    }
    
    public Cliente[] selectClientes(){
    	createConnection();
    	Cliente[] cl = new Cliente[0];
    	Cliente[] aux;
    	
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM CLIENTE ORDER BY LASTNAME ASC");
            int c = 0;
            while(results.next()) {
            	aux = new Cliente[cl.length];
            	for(int x = 0; x < cl.length; x++) {
            		aux[x] = cl[x];
            	}
            	cl = new Cliente[c + 1];
            	for(int x = 0; x < aux.length; x++) {
            		cl[x] = aux[x];
            	}
            	cl[c] = new Cliente();
            	cl[c].setId(results.getInt(1));
            	cl[c].setRFC(results.getString(2));            	
            	cl[c].setNombre(results.getString(3)); // NOMBRE
            	cl[c].setApellidos(results.getString(4)); // APELLIDOS
            	cl[c].setCalle(results.getString(5)); // CALLE
            	cl[c].setNoDir(results.getString(6)); // NUMERO
            	cl[c].setColonia(results.getString(7)); // COLONIA
            	cl[c].setCP(results.getString(8)); // CP 
            	cl[c].setCiudad(results.getString(9)); // CIUDAD
            	cl[c].setEstado(results.getString(10)); // ESTADO
            	cl[c].setEmail(results.getString(11));// EMAIL 
            	cl[c].setTelefono(results.getString(12));// TELEFONO
            	cl[c].setTelEmp(results.getString(13));// TELEFONO
            	c++;
            }
            aux = null;
            results.close();
            stmt.close();
            shutdown();
            return cl;
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return null;
        }
    }
    
    public boolean UpdateClient(Cliente cli) {
    	createConnection();
    	try {
    		stmt = conn.createStatement();
            stmt.execute("UPDATE CLIENTE SET RFC = '" + cli.getRFC() + "', FIRSTNAME = '" + cli.getNombre() + "',"
                    	+ "LASTNAME = '" + cli.getApellidos() +  "', CALLE = '" + cli.getCalle() + "', "
                    	+ "COLONIA = '" + cli.getColonia() + "', CP = " + Integer.parseInt(cli.getCP()) + ", "
                    	+ "CIUDAD = '" + cli.getCiudad() + "', ESTADO = '" + cli.getEstado() + "', "
                    	+ "EMAIL = '" + cli.getEmail() + "', TEL_CELULAR = '" + cli.getTelefono() + "', "
                    	+ "TEL_EMPRESA = '" + cli.getTelEmp() + "', NUMERO = '" + cli.getNoDir() 
                    	+ "' WHERE ID_CUS = "+ cli.getId());
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            shutdown();
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

    public boolean DeleteClient(int id_cus) {
    	createConnection();
    	try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM CLIENTE WHERE ID_CUS = " + id_cus);
            stmt.close();
            shutdown();
            return true;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            shutdown();
            return false;
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
