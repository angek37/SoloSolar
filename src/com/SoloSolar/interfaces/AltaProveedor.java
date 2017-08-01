package com.SoloSolar.interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;
import com.SoloSolar.interfaces.AltaCategoria.CategoryModel;

public class AltaProveedor extends JPanel implements ActionListener {
	private JTextField nombre, direccion, telefono, correo;
	private JButton registrar;
	private JTable table;
	private JLabel titulo;
	
	public AltaProveedor() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(4,4,4,4);
		titulo = new JLabel("Registrar nuevo Proveedor");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		add(new JLabel("Nombre de proveedor:"), gbc);
		nombre = new JTextField();
		gbc.gridx+=2;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		add(nombre, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		add(new JLabel("Dirección:"), gbc);
		direccion = new JTextField();
		gbc.gridx+=2;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		add(direccion, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		add(new JLabel("Teléfono:"), gbc);
		telefono = new JTextField();
		gbc.gridx+=2;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		add(telefono, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		add(new JLabel("Email:"), gbc);
		correo = new JTextField();
		gbc.gridx+=2;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		add(correo, gbc);
		
		registrar = new JButton("Registrar proveedor");
		gbc.gridy++;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		add(registrar, gbc);
		registrar.addActionListener(this);
		
		table = new JTable(new SupplierModel());
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setCellSelectionEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 4;
		add(scrollPane, gbc);
	}

	public void actionPerformed(ActionEvent e) {
		Insert in = new Insert();
		Proveedor prov;
		if(e.getSource() == registrar) {
			prov = new Proveedor(nombre.getText(), direccion.getText(), telefono.getText(), correo.getText());
			if(in.InsertSupplier(prov)) {
				JOptionPane.showMessageDialog(null, "Proveedor registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "No ha sido posible registrar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
			}
			nombre.setText("");
			direccion.setText("");
			telefono.setText("");
			correo.setText("");
			table.setModel(new SupplierModel());
		}
	}
	
	public class SupplierModel extends AbstractTableModel {
		Consulta select = new Consulta();
		Proveedor[] prov = select.selectSupplier();
		public int getRowCount() {
			return prov.length;
		}

		public int getColumnCount() {
			return 2;
		}
		
		public String getColumnName(int col) {
			String aux = "";
		      switch(col) {
		      case 0: 
		    	  return aux = "Nombre";
		      case 1: 
		    	  return aux = "Dirección";
		      }
			return aux;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			   Object value = null;
			   Proveedor proveedor = prov[rowIndex];
			   switch (columnIndex) {
			   case 0:
				   value = proveedor.getNombre();
				   break;
			   case 1:
				   value = proveedor.getDireccion();
				   break;
			   }
	           return value;
		}
	}
	
}
