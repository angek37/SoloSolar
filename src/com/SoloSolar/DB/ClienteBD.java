package com.SoloSolar.DB;

import java.sql.Connection;
import java.sql.DriverManager;
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
            		+ "'" + cli.getApellidos() +  "', '" + cli.getCalle() + "', '" + cli.getColonia() + "',"
            				+ Integer.parseInt(cli.getCP()) + ", '" + cli.getCiudad() + "', '" + cli.getEstado() + "',"
            				+ "'" + cli.getEmail() + "', " + Integer.parseInt(cli.getTelefono()) + ","
            				+ Integer.parseInt(cli.getTelEmp()) + ")");
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    	shutdown();
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
