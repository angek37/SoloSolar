package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.Capsulas.Pedido;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;

public class PedidosPorCliente extends JPanel {
	JTable clientes, pedidos;
	JFrame frame;
	JPanel principal;
	Consulta c = new Consulta();
	Pedido[] pedidosDatos = new Pedido[0];
	
	public PedidosPorCliente(JPanel principal, JFrame frame) {
		this.principal = principal;
		this.frame = frame;
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
			gbc.gridy++;
			clientes = new JTable(new ClientesModel());
			clientes.setFillsViewportHeight(true);
			clientes.setShowHorizontalLines(true);
			clientes.setShowVerticalLines(true);
			clientes.getTableHeader().setReorderingAllowed(false);
			JScrollPane scrollPane = new JScrollPane(clientes);
			scrollPane.setPreferredSize(new Dimension(200, 300));
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
		JButton pdf, editar, eliminar;
		private ImageIcon pdfIco = new ImageIcon(
				new ImageIcon("assets/pdf.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		private ImageIcon editIco = new ImageIcon(
				new ImageIcon("assets/editOrder.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		private ImageIcon deleteIco = new ImageIcon(
				new ImageIcon("assets/deleteOrder.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		public OptionsPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(10, 20, 0, 20);
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridx = 0;
			gbc.gridy = 0;
			pdf = new JButton("Exportar", pdfIco);
			pdf.setPreferredSize(new Dimension(100, 70));
			pdf.setBackground(new Color(182, 182, 182));
			add(pdf, gbc);
			pdf.addActionListener(this);
			editar = new JButton(" Editar   ", editIco);
			editar.setMaximumSize(new Dimension(100, 70));
			editar.setBackground(new Color(182, 182, 182));
			gbc.gridy++;
			gbc.insets = new Insets(5, 20, 0, 20);
			add(editar, gbc);
			editar.addActionListener(this);
			eliminar = new JButton("Eliminar ", deleteIco);
			eliminar.setMaximumSize(new Dimension(100, 70));
			eliminar.setBackground(new Color(182, 182, 182));
			gbc.gridy++;
			add(eliminar, gbc);
			eliminar.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getSource() == editar){
					Pedido pedido = c.selectOrder(pedidos.getModel().getValueAt(pedidos.getSelectedRow(), 0).toString());
					pedido.setTotal((Double)pedidosDatos[pedidos.getSelectedRow()].getTotal());
					String[][] renglones = c.selectRowsOrder(pedidos.getModel().getValueAt(pedidos.getSelectedRow(), 0).toString());
					principal.removeAll();
					principal.add(new Venta(frame, pedido, renglones));
					principal.updateUI();
					principal.repaint();
					repaint();
				} else if(e.getSource() == eliminar) {
					Insert in = new Insert();
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el pedido '"+ pedidos.getModel().getValueAt(pedidos.getSelectedRow(), 0) +"'?", "Borrar Pedido", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(reply == JOptionPane.YES_OPTION) {
						if(in.DeleteOrder(pedidos.getModel().getValueAt(pedidos.getSelectedRow(), 0).toString())) {
							JOptionPane.showMessageDialog(null, "El pedido se ha eliminado de forma correcta", "¡Pedido Eliminado!", JOptionPane.INFORMATION_MESSAGE);
							pedidosDatos = c.selectOrders(""+clientes.getModel().getValueAt(clientes.getSelectedRow(), 0));
							pedidos.setModel(new PedidosModel());
						}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible eliminar el pedido", "¡Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else if(e.getSource() == pdf) {
					String ruta = "";
					int renglones = clientes.getRowCount();
					if(rengReales(renglones) >= 1) {
						JFileChooser f = new JFileChooser() {
							@Override
							public void approveSelection() {
								File f = getSelectedFile();
				                if (f.exists() && getDialogType() == SAVE_DIALOG) {
				                	int result = JOptionPane.showConfirmDialog(this,
				                		String.format("%s ya existe.%n ¿Desea Sobreescribirlo?", f.getName()),
				                		"El archivo ya existe", JOptionPane.YES_NO_OPTION);
	
				                    switch (result){
				                    	case JOptionPane.YES_OPTION:
				                    		super.approveSelection();
				                    		return;
				                    	case JOptionPane.NO_OPTION:
				                    		return;
				                    	case JOptionPane.CLOSED_OPTION:
				                    		return;
				                    	case JOptionPane.CANCEL_OPTION:
				                    		cancelSelection();
				                    		return;
				                    }
				                }
				                super.approveSelection();
							}
						};
						f.setSelectedFile(new File("Reporte Ventas"));
						int opcion = f.showSaveDialog(frame);
						if(opcion == JFileChooser.APPROVE_OPTION) {
							String[][] dataPDF = dataPDF(clientes.getRowCount());
							File file = f.getSelectedFile();
							ruta = file.toString();
							String data[][] = dataPDF(renglones);
							GenerarPDFListas g = new GenerarPDFListas(ruta, renglones, data);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "No hay datos para exportar", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}catch(ArrayIndexOutOfBoundsException excep){
				JOptionPane.showMessageDialog(null, "Seleccione un pedido", "No hay pedido seleccionado", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public String[][] dataPDF(int renglones) {
		int reng = rengReales(renglones);
		String data[][] = new String[reng][7];
		int index = 0;
		boolean aumentar = false;
		for(int i = 0; i < renglones; i++) {
			aumentar = false;
			for(int j = 0; j < 7; j++) {
				data[index][j] = clientes.getModel().getValueAt(i, j) + "";
			}
		}
		return data;
	}
	
	public int rengReales(int reng) {
		int rengReales = 0;
		for(int i = 0; i < reng; i++) {
			for(int j = 0; j < 7; j++) {
				if(clientes.getModel().getValueAt(i, 0) != null && 
					!String.valueOf(clientes.getModel().getValueAt(i, 0)).equals("") ) {
					rengReales++;
					j = 7;
				}
			}
		}
		return rengReales;
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
