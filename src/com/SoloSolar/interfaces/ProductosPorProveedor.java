package com.SoloSolar.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.DB.Consulta;

public class ProductosPorProveedor extends JPanel implements ActionListener{
	private JLabel titulo, nombre, direccion, telefono, correo;
	private JComboBox<Proveedor> suppliers;
	Consulta c = new Consulta();
	Proveedor[] supplier = (Proveedor[]) c.selectSupplier();
	private JTable table;
	private JButton buscar;
	
	public ProductosPorProveedor() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 30, 0, 30);
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		titulo = new JLabel("Lista de productos por proveedor");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(titulo, gbc);
		suppliers = new JComboBox<Proveedor>(supplier);
		suppliers.setMaximumSize(new Dimension(200, 20));
		gbc.gridy++;
		gbc.gridwidth = 1;
		add(suppliers, gbc);
		buscar = new JButton("Buscar");
		buscar.addActionListener(this);
		gbc.gridx++;
		add(buscar, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(new ProductoDatosPanel(), gbc);		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setCellSelectionEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.ipady = 150;
		add(scrollPane, gbc);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buscar) {
			Proveedor prov = (Proveedor) suppliers.getSelectedItem();
			nombre.setText(prov.getNombre());
			direccion.setText(prov.getDireccion());
			telefono.setText(prov.getTelefono());
			correo.setText(prov.getEmail());
			table.setModel(new ProductobySupplierModel(prov.getId()));
		}
	}
	
	public class ProductoDatosPanel extends JPanel {
		public ProductoDatosPanel() {
			setLayout(new GridBagLayout());
			setBorder(new CompoundBorder(new TitledBorder("Datos del Proveedor"), new EmptyBorder(12, 0, 0, 0)));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(10, 20, 10, 20);
			gbc.weightx = 1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(new JLabel("Nombre:"), gbc);
			nombre = new JLabel("");
			nombre.setFont(new Font("Calibri", Font.BOLD, 14));
			gbc.gridx++;
			add(nombre, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Dirección:"), gbc);
			direccion = new JLabel("");
			gbc.gridx++;
			add(direccion, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Teléfono:"), gbc);
			telefono = new JLabel("");
			gbc.gridx++;
			add(telefono, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Email:"), gbc);
			correo = new JLabel("");
			gbc.gridx++;
			add(correo, gbc);
		}
	}
		
	public class ProductobySupplierModel extends AbstractTableModel {
		Producto[] product;
		Consulta select = new Consulta();
		
		public ProductobySupplierModel(int id) {
			product = select.SelectProductsBySupplier(id);
		}
		public int getRowCount() {
			return product.length;
		}

		public int getColumnCount() {
			return 7;
		}
		
		public String getColumnName(int col) {
			String head = "";
			switch(col) {
			case 0:
				head = "Clave";
				break;
			case 1:
				head = "Nombre";
				break;
			case 2:
				head = "Categoría";
				break;
			case 3:
				head = "Paquete";
				break;
			case 4:
				head = "Costo";
				break;
			case 5:
				head = "Precio 1";
				break;
			case 6:
				head = "Precio 2";
				break;
			}
			return head;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			   Producto producto = product[rowIndex];
			   switch (columnIndex) {
			   case 0:
				   value = producto.getClave();
				   break;
			   case 1:
				   value = producto.getNombre();
				   break;
			   case 2:
				   value = producto.getCategoriaNombre();
				   break;
			   case 3:
				   value = producto.getPaquete();
				   break;
			   case 4:
				   value = producto.getCosto();
				   break;
			   case 5:
				   value = producto.getPrecio1();
				   break;
			   case 6:
				   value = producto.getPrecio2();
				   break;
			   }
	           return value;
		}
			
	}

}
