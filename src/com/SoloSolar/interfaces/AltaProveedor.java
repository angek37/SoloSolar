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

import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;

public class AltaProveedor extends JPanel implements ActionListener {
	private JTextField nombre, calle, numero, colonia, cp, ciudad, estado, celular, telefono, correo;
	private JButton registrar;
	private JTable table;
	private JLabel titulo;
	
	public AltaProveedor() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4,30,4,4);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.ipadx = 200;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		titulo = new JLabel("Registrar nuevo Proveedor");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo, gbc);
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.ipadx = 0;
		add(new JLabel("Nombre:"), gbc);
		nombre = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(nombre, gbc);
		gbc.gridx++;
		gbc.ipadx = 0;
		add(new JLabel("Calle:"), gbc);
		calle = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(calle, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		gbc.ipadx = 0;
		add(new JLabel("Número:"), gbc);
		numero = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(numero, gbc);
		gbc.gridx++;
		gbc.ipadx = 0;
		add(new JLabel("Colonia:"), gbc);
		colonia = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(colonia, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.ipadx = 0;
		add(new JLabel("C.P.:"), gbc);
		cp = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(cp, gbc);
		gbc.gridx++;
		gbc.ipadx = 0;
		add(new JLabel("Ciudad:"), gbc);
		ciudad = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(ciudad, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.ipadx = 0;
		add(new JLabel("Estado:"), gbc);
		estado = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(estado, gbc);
		gbc.gridx++;
		gbc.ipadx = 0;
		add(new JLabel("Email:"), gbc);
		correo = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(correo, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.ipadx = 0;
		add(new JLabel("Cel.:"), gbc);
		celular = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(celular, gbc);
		gbc.gridx++;
		gbc.ipadx = 0;
		add(new JLabel("Tel.:"), gbc);
		telefono = new JTextField();
		gbc.gridx++;
		gbc.ipadx = 200;
		add(telefono, gbc);
		registrar = new JButton("Registrar proveedor");
		gbc.gridy++;
		gbc.ipadx = 50;
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
		gbc.ipady = 100;
		gbc.insets = new Insets(4,0,0,0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 4;
		add(scrollPane, gbc);
	}

	public void actionPerformed(ActionEvent e) {
		Validaciones v = new Validaciones();
		Insert in = new Insert();
		Proveedor prov;
		if(e.getSource() == registrar) {
			prov = new Proveedor(nombre.getText(), calle.getText(), numero.getText(), 
					colonia.getText(), ciudad.getText(), estado.getText(), correo.getText(), 
						celular.getText(), telefono.getText(), cp.getText());
			if(v.validarProveedores(prov)) {
				if(in.InsertSupplier(prov)) {
					nombre.setText("");
					calle.setText("");
					numero.setText("");
					colonia.setText("");
					cp.setText("");
					ciudad.setText("");
					estado.setText("");
					celular.setText("");
					telefono.setText("");
					correo.setText("");
					table.setModel(new SupplierModel());
					JOptionPane.showMessageDialog(null, "Proveedor registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "No ha sido posible registrar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public class SupplierModel extends AbstractTableModel {
		Consulta select = new Consulta();
		Proveedor[] prov = select.selectSupplier();
		public int getRowCount() {
			return prov.length;
		}

		public int getColumnCount() {
			return 3;
		}
		
		public String getColumnName(int col) {
			String aux = "";
		      switch(col) {
		      case 0: 
		    	  return aux = "Nombre";
		      case 1: 
		    	  return aux = "Dirección";
		      case 2:
		    	  return aux = "Teléfono";
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
			   case 2:
				   value = proveedor.getTelefono();
				   break;
			   }
	           return value;
		}
	}
	
}
