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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Pedido;
import com.SoloSolar.DB.Consulta;

public class ListaPedidos extends JPanel {
	JTable table;
	
	public ListaPedidos() {
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
		JButton editar, eliminar;
		private ImageIcon editIco = new ImageIcon(
				new ImageIcon("assets/editOrder.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		private ImageIcon deleteIco = new ImageIcon(
				new ImageIcon("assets/deleteOrder.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		public OptionsPanel() {
			setLayout(new FlowLayout(FlowLayout.CENTER));
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
			if(e.getSource() == editar) {
				
			}else if(e.getSource() == eliminar) {
				
			}
		}
	}
	
	public class TableOrderModel extends AbstractTableModel {
		Consulta c = new Consulta();
		Pedido[] p = c.selectOrders();

		public int getRowCount() {
			return p.length;
		}

		public int getColumnCount() {
			return 5;
		}
		
		public String getColumnName(int col) {
			String name = "";
			switch(col) {
			case 0: name = "Clave";
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
			case 0: ob = (Object) p[rowIndex].getId();
			break;
			case 1: ob = (Object) p[rowIndex].getFecha();
			break;
			case 2: ob = (Object) p[rowIndex].getCustomer();
			break;
			case 3: ob = (Object) p[rowIndex].getClienteString();
			break;
			case 4: ob = (Object) p[rowIndex].getTotal();
			break;
			}
			return ob;
		}
		
	}
}
