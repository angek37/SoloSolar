package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.DB.Consulta;

public class PedidosPorCliente extends JPanel {
	JTable clientes;
	
	public PedidosPorCliente() {
		setLayout(new BorderLayout());
		add(new ClientesTable(), BorderLayout.NORTH);
		add(new PedidosTable(), BorderLayout.CENTER);
		add(new OptionsPanel(), BorderLayout.EAST);
	}
	
	public class ClientesTable extends JPanel {
		JLabel titulo;
		
		public ClientesTable() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.WEST;
			titulo = new JLabel("Pedidos por Cliente");
			titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
			titulo.setForeground(Color.BLUE);
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(titulo, gbc);
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridy++;
			clientes = new JTable(new ClientesModel());
			clientes.setFillsViewportHeight(true);
			clientes.setShowHorizontalLines(true);
			clientes.setShowVerticalLines(true);
			clientes.getTableHeader().setReorderingAllowed(false);
			add(clientes, gbc);
		}
	}
	
	public class PedidosTable extends JPanel {
		
		public PedidosTable() {
			setLayout(new BorderLayout());
		}
	}
	
	public class OptionsPanel extends JPanel {
		
		public OptionsPanel() {
			setLayout(new GridBagLayout());
		}
	}
	
	public class ClientesModel extends AbstractTableModel {
		Consulta c = new Consulta();
		Cliente[] clientes = c.selectCustomers();
		
		public int getRowCount() {
			return clientes.length;
		}

		public int getColumnCount() {
			return 5;
		}
		
		public boolean isCellEditable(int row, int column) {
        	return false;
        }
		
		public String getColumnName(int col) {
			String name = "";
			switch(col) {
			case 0: name = "Cliente";
			break;
			case 1: name = "Nombre";
			break;
			case 2: name = "Saldo";
			break;
			case 3: name = "Celular";
			break;
			case 4: name = "Teléfono Empresa";
			break;
			}
			return name;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object obj = null;
			switch(columnIndex) {
			case 0: obj = clientes[rowIndex].getId();
			break;
			case 1: obj = clientes[rowIndex].getNombre();
			break;
			case 2: obj = clientes[rowIndex].getSaldo();
			break;
			case 3: obj = clientes[rowIndex].getTelefono();
			break;
			case 4: obj = clientes[rowIndex].getTelEmp();
			break;
			}
			return obj;
		}
		
	}
}
