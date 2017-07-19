package com.SoloSolar.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataListener;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.DB.Consulta;

public class AltaProducto extends JPanel implements ActionListener{
	private JLabel titulo;
	private JTextField clave, nombre, paquete, costo, precio1, precio2;
	private JComboBox<Categoria> categories;
	private JButton registrar;
	
	public AltaProducto() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//Fila 1
		gbc.insets = new Insets(4,10,10,4);
		gbc.ipady = 5;
		gbc.weightx = 0.05;
		titulo = new JLabel("Registrar nuevo producto");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		add(titulo, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(new JLabel("Clave:"), gbc);
		clave = new JTextField();
		gbc.ipadx = 200;
		gbc.gridx++;
		add(clave, gbc);
		//Fila 2
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Nombre:"), gbc);
		nombre = new JTextField();
		gbc.ipadx = 300;
		gbc.gridx++;
		add(nombre, gbc);
		//Fila 3
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Categoría:"), gbc);
		categories = new JComboBox<Categoria>(new ProductModel());
		categories.setMaximumSize(new Dimension(200, 20));
		gbc.gridx++;
		add(categories, gbc);
		//Fila 4
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Paquete:"), gbc);
		paquete = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(paquete, gbc);
		//Fila 5
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Costo:"), gbc);
		costo = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(costo, gbc);
		//Fila 6
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Precio 1:"), gbc);
		precio1 = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(precio1, gbc);
		//Fila 7
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Precio 2:"), gbc);
		precio2 = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(precio2, gbc);
		//Fila 8
		registrar = new JButton("Guardar Producto");
		gbc.ipadx = 160;
		gbc.gridy++;
		add(registrar, gbc);
		registrar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Categoria c;
		c = (Categoria) categories.getSelectedItem();
		if(e.getSource() == registrar) {
			JOptionPane.showMessageDialog(null, "Registrao'" + c.getId());
		}
	}
	
	public class ProductModel<Categoria> extends AbstractList implements ComboBoxModel {
		Consulta c = new Consulta();
		Categoria[] category = (Categoria[]) c.selectCategories();
		Object ob;
		
		public int getSize() {
			return category.length;
		}

		public Object getElementAt(int index) {
			ob = (Object) category[index];
			return ob;
		}

		public void setSelectedItem(Object anItem) {
			
		}

		public Object getSelectedItem() {
			return ob;
		}

		public Object get(int index) {
			return null;
		}

		public int size() {
			
			return 0;
		}

		@Override
		public void addListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
