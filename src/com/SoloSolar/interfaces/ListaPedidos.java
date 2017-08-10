package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

import com.SoloSolar.Capsulas.Pedido;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;

public class ListaPedidos extends JPanel {
	JTable table;
	JFrame frame;
	JPanel principal;
	Consulta c = new Consulta();
	Pedido[] p = c.selectOrders();
	
	public ListaPedidos(JPanel principal, JFrame frame) {
		this.frame = frame;
		this.principal = principal;
		setLayout(new BorderLayout());
		JLabel titulo = new JLabel("Lista de Pedidos");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo, BorderLayout.NORTH);
		add(new TablePanel(), BorderLayout.CENTER);
		add(new OptionsPanel(), BorderLayout.SOUTH);
	}
	
	public class TablePanel extends JPanel {
		
		public TablePanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.gridx = 0;
			gbc.gridy = 0;
			table = new JTable(new TableOrderModel());
			table.setFillsViewportHeight(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.getTableHeader().setReorderingAllowed(false);
			FormatoTabla();
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, gbc);
		}
	}
	
	public void FormatoTabla() {
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.getColumnModel().getColumn(2).setMaxWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(200);
		table.getColumnModel().getColumn(4).setMinWidth(20);
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
			setLayout(new FlowLayout(FlowLayout.CENTER));
			pdf = new JButton("Exportar", pdfIco);
			pdf.setBackground(new Color(182, 182, 182));
			add(pdf);
			pdf.addActionListener(this);
			editar = new JButton("Editar", editIco);
			editar.setBackground(new Color(182, 182, 182));
			add(editar);
			editar.addActionListener(this);
			eliminar = new JButton("Eliminar", deleteIco);
			eliminar.setBackground(new Color(182, 182, 182));
			add(eliminar);
			eliminar.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			Insert in = new Insert();
			Consulta c = new Consulta();
			try {
				if(e.getSource() == editar) {
					Pedido pedido = c.selectOrder(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
					pedido.setTotal((Double)p[table.getSelectedRow()].getTotal());
					String[][] renglones = c.selectRowsOrder(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
					principal.removeAll();
					principal.add(new Venta(frame, pedido, renglones));
					principal.updateUI();
					principal.repaint();
					repaint();
				}else if(e.getSource() == eliminar) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el pedido '"+ table.getModel().getValueAt(table.getSelectedRow(), 0) +"'?", "Borrar Pedido", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(reply == JOptionPane.YES_OPTION) {
						if(in.DeleteOrder(table.getModel().getValueAt(table.getSelectedRow(), 0).toString())) {
							JOptionPane.showMessageDialog(null, "El pedido se ha eliminado de forma correcta", "¡Pedido Eliminado!", JOptionPane.INFORMATION_MESSAGE);
							table.setModel(new TableOrderModel());
							FormatoTabla();
						}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible eliminar el pedido", "¡Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				}else if(e.getSource() == pdf) {
					int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2) + "");
					int ped = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0) + "");
					System.out.println(id + " " + ped);
					Pedido p = Consulta.getPedido(ped);
					boolean iva = false;
					String ruta = "";
					int renglones = table.getRowCount();
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
					f.setSelectedFile(new File("Pedido " + ped));
					int opcion = f.showSaveDialog(frame);
					if(opcion == JFileChooser.APPROVE_OPTION) {
						String[][] dataPDF = Consulta.getPedidoCompleto(ped);
						File file = f.getSelectedFile();
						ruta = file.toString();
						GenerarPDFListas g = new GenerarPDFListas(ruta, renglones, dataPDF, id, ped, p);
					}
				}
			}catch(ArrayIndexOutOfBoundsException except) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado un pedido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class TableOrderModel extends AbstractTableModel {

		public int getRowCount() {
			return p.length;
		}

		public int getColumnCount() {
			return 5;
		}
		
		public String getColumnName(int col) {
			String name = "";
			switch(col) {
			case 0: name = "Pedido";
			break;
			case 1: name = "Fecha";
			break;
			case 2: name = "Cliente";
			break;
			case 3: name = "Nombre de Cliente";
			break;
			case 4: name = "Total";
			break;
			}
			return name;
		}
		
		public boolean isCellEditable(int row, int column) {
        	return false;
        }

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object ob = null;
			switch(columnIndex) {
			case 0: ob = p[rowIndex].getId();
			break;
			case 1: ob = p[rowIndex].getFecha();
			break;
			case 2: ob = p[rowIndex].getCustomer();
			break;
			case 3: ob = p[rowIndex].getClienteString();
			break;
			case 4: ob = "$ " + p[rowIndex].getTotal();
			break;
			}
			return ob;
		}
		
	}
}
