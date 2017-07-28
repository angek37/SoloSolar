package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.AbstractList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;
import com.SoloSolar.interfaces.AdministrarCategorias.CategoryModel;
import com.SoloSolar.interfaces.AdministrarCategorias.ProductModel;

public class AdministrarProducto extends JPanel implements MouseListener {
	private JTextField clave, nombre, paquete, costo, precio1, precio2;
	private JComboBox<Categoria> categories;
	private JTable table;
	
	public AdministrarProducto() {
		setLayout(new BorderLayout());
		table = new JTable(new ProductModel());
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(150, 0));
		add(scrollPane, BorderLayout.WEST);
		add(new ModifyProduct(), BorderLayout.CENTER);
		
		JLabel titulo = new JLabel("Administrar Productos");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo, BorderLayout.NORTH);
	}
	
	public class ProductModel extends AbstractTableModel {
		Consulta select = new Consulta();
		Producto[] product = select.selectProducts();
		
		public int getRowCount() {
			return product.length;
		}

		public int getColumnCount() {
			return 1;
		}
		
		public String getColumnName(int col) {
			return "Nombre";
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			   Producto producto = product[rowIndex];
			   switch (columnIndex) {
			   case 1:
				   value = producto.getClave();
				   break;
			   case 0:
				   value = producto.getNombre();
				   break;
			   case 2:
				   value = producto.getCategoria();
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
	
	public class ModifyProduct extends JPanel implements ActionListener{
		private JButton actualizar, eliminar;
		private JPanel updateP, deleteP;
		
		public ModifyProduct() {
			setLayout(new BorderLayout());
			updateP = new JPanel();
			deleteP = new JPanel();
			
			updateP.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			updateP.setBorder(new CompoundBorder(new TitledBorder("Modificar valores de Producto"), new EmptyBorder(12, 0, 0, 0)));
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(4,4,4,4);
			updateP.add(new JLabel("Clave:"), gbc);
			clave = new JTextField();
			clave.setEnabled(false);
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(clave, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			updateP.add(new JLabel("Nombre de producto:"), gbc);
			nombre = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(nombre, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.weighty = 0;
			updateP.add(new JLabel("Categoría:"), gbc);
			categories = new JComboBox<Categoria>(new ProductCombo());
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(categories, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.weighty = 0;
			updateP.add(new JLabel("Paquete:"), gbc);
			paquete = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(paquete, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.weighty = 0;
			updateP.add(new JLabel("Costo:"), gbc);
			costo = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(costo, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.weighty = 0;
			updateP.add(new JLabel("Precio 1:"), gbc);
			precio1 = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(precio1, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.weighty = 0;
			updateP.add(new JLabel("Precio 2:"), gbc);
			precio2 = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(precio2, gbc);
			
			actualizar = new JButton("Actualizar");
			gbc.gridy++;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(actualizar, gbc);
			actualizar.addActionListener(this);
			
//			deleteP.setLayout(new GridBagLayout());
//			GridBagConstraints gbc2 = new GridBagConstraints();
//			deleteP.setBorder(new CompoundBorder(new TitledBorder("Eliminar producto"), new EmptyBorder(12, 12, 12, 12)));
//			prod = new JLabel("Productos dentro de esta categoría: ");
//			gbc2.gridx = 0;
//			gbc2.gridy = 0;
//			gbc2.anchor = GridBagConstraints.WEST;
//			gbc2.gridwidth = 2;
//			gbc2.fill = GridBagConstraints.HORIZONTAL;
//			deleteP.add(prod, gbc2);
//			gbc2.gridwidth = 1;
//			gbc2.gridy++;
//			gbc2.weightx = 1;
//			gbc2.anchor = GridBagConstraints.WEST;
//			gbc2.insets = new Insets(20, 0, 20, 0);
//			deleteP.add(new JLabel("Transferir a: "), gbc2);
//			
//			categories = new JComboBox<Categoria>(new ProductModel());
//			gbc2.gridx++;
//			deleteP.add(categories, gbc2);
//			
//			eliminar = new JButton("Eliminar");
//			gbc2.insets = new Insets(0, 0, 0, 0);
//			gbc2.gridy++;
//			deleteP.add(eliminar, gbc2);
//			eliminar.addActionListener(this);
//			
			add(updateP, BorderLayout.CENTER);
//			add(deleteP, BorderLayout.SOUTH);
		}
		

		public void actionPerformed(ActionEvent e) {
			
		}
	}


	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getClickCount() == 1) {
				Object ob = (Object) 2;
				clave.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 1));
				nombre.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 0));
				categories.setSelectedItem(ob);
				paquete.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 3));
				costo.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 4));
				precio1.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 5));
				precio2.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 6));
			}
		}catch(ArrayIndexOutOfBoundsException expt) {
			
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
	
	public class ProductCombo<Categoria> extends AbstractList implements ComboBoxModel {
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
