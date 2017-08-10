package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.Capsulas.Pedido;
import com.SoloSolar.DB.Consulta;

public class PedidosPorCliente extends JPanel {
	JTable clientes, pedidos;
	Consulta c = new Consulta();
	Pedido[] pedidosDatos = new Pedido[0];
	
	public PedidosPorCliente() {
		setLayout(new BorderLayout());
		pedidos = new JTable(new PedidosModel());
		pedidos.setFillsViewportHeight(true);
		pedidos.setShowHorizontalLines(true);
		pedidos.setShowVerticalLines(true);
		pedidos.getTableHeader().setReorderingAllowed(false);
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
			gbc.gridheight = 1;
			gbc.gridy++;
			clientes = new JTable(new ClientesModel());
			clientes.setFillsViewportHeight(true);
			clientes.setShowHorizontalLines(true);
			clientes.setShowVerticalLines(true);
			clientes.getTableHeader().setReorderingAllowed(false);
			JScrollPane scrollPane = new JScrollPane(clientes);
			add(scrollPane, gbc);
			clientes.getColumnModel().getColumn(0).setMaxWidth(50);
			clientes.getColumnModel().getColumn(1).setMinWidth(170);
			clientes.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 1) {
						try {
							pedidosDatos = c.selectOrders(""+clientes.getModel().getValueAt(clientes.getSelectedRow(), 0));
							pedidos.setModel(new PedidosModel());
						}catch(ArrayIndexOutOfBoundsException exp) {
							
						}
					}
				}

				public void mousePressed(MouseEvent e) {
					
				}

				public void mouseReleased(MouseEvent e) {
					
				}

				public void mouseEntered(MouseEvent e) {
					
				}

				public void mouseExited(MouseEvent e) {
					
				}
				
			});
		}
	}
	
	public class PedidosTable extends JPanel {
		public PedidosTable() {	
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			JScrollPane scrollPane = new JScrollPane(pedidos);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridheight = 2;
			add(scrollPane, gbc);
		}
	}
	
	public class OptionsPanel extends JPanel implements ActionListener{
		JButton pdf;
		private ImageIcon pdfIco = new ImageIcon(
				new ImageIcon("assets/pdf.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		public OptionsPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(30, 20, 0, 20);
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridx = 0;
			gbc.gridy = 0;
			pdf = new JButton("Exportar", pdfIco);
			add(pdf, gbc);
			pdf.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == pdf) {
				
			}
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
			case 2: obj = "$ "+clientes[rowIndex].getSaldo();
			break;
			case 3: obj = clientes[rowIndex].getTelefono();
			break;
			case 4: obj = clientes[rowIndex].getTelEmp();
			break;
			}
			return obj;
		}
		
	}
	
	public class PedidosModel extends AbstractTableModel {
		
		public int getRowCount() {
			return pedidosDatos.length;
		}

		public int getColumnCount() {
			return 3;
		}
		
		public boolean isCellEditable(int row, int column) {
        	return false;
        }
		
		public String getColumnName(int col) {
			String name = "";
			switch(col) {
			case 0: name = "Pedido";
			break;
			case 1: name = "Fecha";
			break;
			case 2: name = "Total";
			break;
			}
			return name;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object obj = null;
			switch(columnIndex) {
			case 0: obj = pedidosDatos[rowIndex].getId();
			break;
			case 1: obj = pedidosDatos[rowIndex].getFecha();
			break;
			case 2: obj = "$ "+pedidosDatos[rowIndex].getTotal();
			break;
			}
			return obj;
		}
		
	}
	
}
