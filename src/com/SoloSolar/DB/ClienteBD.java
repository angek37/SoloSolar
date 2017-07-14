package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.jdbc.*;

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
            stmt.execute("INSERT INTO Cliente(RFC, FIRSTNAME, SECONDNAME, CALLE, COLONIA, CP, CIUDAD, ESTADO, EMAIL,"
            		+ "TEL_CELULAR, TEL_EMPRESA) VALUES ('" + cli.getRFC() + "', '" + cli.getNombre() + "',"
            		+ "'" + cli.getApellidos() +  "', '" + cli.getCalle() + "', '" + cli.getColonia() + "', "
            				+ Integer.parseInt(cli.getCP()) + ", '" + cli.getCiudad() + "', '" + cli.getEstado() + "', "
            				+ "'" + cli.getEmail() + "', '" + cli.getTelefono() + "', '"
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
    
    public static String[] ClientesExistentes() {
    	String datos[] = new String[CantidadClientes()];
    	createConnection();
    	int i = 0;
    	try {
    		stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE");
			while(rs.next()) {
				datos[i] = rs.getString(1) + ": " + rs.getString(3);
				i++;
			}
	    } catch (SQLException e) {
			e.printStackTrace();
		}
    	shutdown();
    	return datos;
    }
    
    public static int CantidadClientes() {
    	createConnection();
    	int tamano = 0;
    	try {
    		stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTE");
			while(rs.next()) {
				tamano++;
			}
	    } catch (SQLException e) {
			e.printStackTrace();
		}
    	shutdown();
    	return tamano;
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
