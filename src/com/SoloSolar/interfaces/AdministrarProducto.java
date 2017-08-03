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
import java.util.AbstractList;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;
import com.SoloSolar.interfaces.AdministrarCategorias.CategoryModel;
import com.SoloSolar.interfaces.AdministrarCategorias.ProductModel;

public class AdministrarProducto extends JPanel implements MouseListener {
	private JTextField clave, nombre, paquete, costo, precio1, precio2;
	Consulta c = new Consulta();
	Categoria[] category = (Categoria[]) c.selectCategories();
	Proveedor[] supplier = (Proveedor[]) c.selectSupplier();
	private JComboBox<Categoria> categories;
	private JComboBox<Proveedor> suppliers;
	private JTable table;
	private JTable tableS;
	private JLabel prod;
	private String[][] datos = {{"", ""}};
	private String[] head = {"ID", "Proveedor"};
	private int cont = 1;
	
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
			categories = new JComboBox<Categoria>(category);
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
			
			deleteP.setLayout(new GridBagLayout());
			GridBagConstraints gbc2 = new GridBagConstraints();
			deleteP.setBorder(new CompoundBorder(new TitledBorder("Eliminar producto"), new EmptyBorder(12, 12, 12, 12)));
			gbc2.gridy = 0;
			gbc2.gridx = 0;
			gbc2.gridwidth = 1;
			gbc2.insets = new Insets(0, 0, 0, 30);
			gbc2.anchor = GridBagConstraints.WEST;
			deleteP.add(new JLabel("Eliminar:"), gbc2);
			prod = new JLabel("Elija un producto");
			prod.setFont(new Font("Calibri", Font.ITALIC, 12));
			prod.setForeground(Color.RED);
			gbc2.gridx++;
			gbc2.anchor = GridBagConstraints.WEST;
			gbc2.gridwidth = GridBagConstraints.REMAINDER;
			gbc2.fill = GridBagConstraints.HORIZONTAL;
			gbc2.weightx = 1;
			gbc2.insets = new Insets(20, 0, 20, 0);
			deleteP.add(prod, gbc2);
			eliminar = new JButton("Eliminar");
			gbc2.gridwidth = 1;
			gbc2.gridx++;
			gbc2.insets = new Insets(0, 0, 0, 0);
			gbc2.gridy++;
			deleteP.add(eliminar, gbc2);
			eliminar.addActionListener(this);	
			add(updateP, BorderLayout.NORTH);
			add(deleteP, BorderLayout.CENTER);
			add(new SuppliersPanel(), BorderLayout.EAST);
		}

		public void actionPerformed(ActionEvent e) {
			Insert in = new Insert();
			Producto producto = new Producto();
			try {
				if(e.getSource() == actualizar) {
					Categoria cat = (Categoria) categories.getSelectedItem();
					producto.setClave(clave.getText());
					producto.setNombre(nombre.getText());
					producto.setCategoria(cat.getId());
					producto.setPaquete(Integer.parseInt(paquete.getText()));
					producto.setCosto(Double.parseDouble(costo.getText()));
					producto.setPrecio1(Double.parseDouble(precio1.getText()));
					producto.setPrecio2(Double.parseDouble(precio2.getText()));
					if(in.UpdateProduct(producto)) {
						JOptionPane.showMessageDialog(null, "Producto modificado exitosamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
						clave.setText("");
						nombre.setText("");
						paquete.setText("");
						costo.setText("");
						precio1.setText("");
						precio2.setText("");
						table.setModel(new ProductModel());
						prod.setText("Elija Producto");
					}else {
						JOptionPane.showMessageDialog(null, "No ha sido posible modificar el producto", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else if (e.getSource() == eliminar) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el producto '"+ table.getModel().getValueAt(table.getSelectedRow(), 0) +"'?", "Borrar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(reply == JOptionPane.YES_OPTION) {
						if(in.DeleteProduct(clave.getText())) {
							JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
							clave.setText("");
							nombre.setText("");
							paquete.setText("");
							costo.setText("");
							precio1.setText("");
							precio2.setText("");
							table.setModel(new ProductModel());
							prod.setText("Elija Producto");
						}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible eliminar el producto", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException exp) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado un producto", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class SuppliersPanel extends JPanel implements ActionListener, MouseListener{
		JButton addB;
		private ImageIcon addico = new ImageIcon(
				new ImageIcon("assets/plus.png").getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		
		public SuppliersPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 4, 10, 0);
			gbc.ipady = 5;
			setBorder(new CompoundBorder(new TitledBorder("Administrar Proveedores"), new EmptyBorder(12, 0, 0, 0)));
			suppliers = new JComboBox<Proveedor>(supplier);
			suppliers.setMaximumSize(new Dimension(200, 20));
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.ipadx = 50;
			add(suppliers, gbc);
			addB = new JButton(addico);
			addB.setBorder(null);
			addB.setBackground(null);
			gbc.gridx++;
			add(addB, gbc);
			addB.addActionListener(this);
			tableS = new JTable(datos, head) {
		        public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
		    };
		    tableS.setFillsViewportHeight(true);
		    tableS.setShowHorizontalLines(true);
		    tableS.setShowVerticalLines(true);
		    tableS.getTableHeader().setReorderingAllowed(false);
		    tableS.setToolTipText("Doble click para eliminar proveedor");
		    tableS.addMouseListener(this);
			JScrollPane scrollPane = new JScrollPane(tableS);
			scrollPane.setPreferredSize(new Dimension(150, 0));
			gbc.ipady = 100;
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridheight = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.BOTH;
			add(scrollPane, gbc);
		}
		
		public boolean isRepeat(int id) {
			boolean reply = false;
			for(int x = 0; x < datos.length; x++) {
				if(datos[x][0].equals(Integer.toString(id))) {
					reply = true;
				}
			}
			return reply;
		}
		
		public void deleteSupplier(int r) {
			datos[r][0] = "";
			datos[r][1] = "";
			String aux[][] = new String[datos.length-1][2];
			int c = 0;
			for(int x = 0; x < datos.length; x++) {
				if(!datos[x][0].equals("")) {
					aux[c][0] = datos[x][0];
					aux[c][1] = datos[x][1];
					c++;
				}
			}
			datos = new String[aux.length][2];
			datos = aux;
			aux = null;
		}

		public void actionPerformed(ActionEvent e) {
			DefaultTableModel dtm;
			if(e.getSource() == addB) {
				Proveedor prov = (Proveedor) suppliers.getSelectedItem();
				if(!isRepeat(prov.getId())) {
					try {
						if(!datos[0][0].equals("")) {
							String aux[][] = new String[datos.length][2];
							aux = datos;
							cont++;
							datos = new String[cont][2];
							for(int x = 0; x < aux.length; x++) {
								datos[x][0] = aux[x][0];
								datos[x][1] = aux[x][1];
							}
							aux = null;
							datos[cont-1][0] = Integer.toString(prov.getId());
							datos[cont-1][1] = prov.getNombre();
							dtm = new DefaultTableModel(datos, head);
							tableS.setModel(dtm);
						}else {
							datos[0][0] = Integer.toString(prov.getId());
							datos[0][1] = prov.getNombre();
							dtm = new DefaultTableModel(datos, head);
							tableS.setModel(dtm);
						}
					}catch(ArrayIndexOutOfBoundsException ex) {
						cont = 1;
						datos = new String[1][2];
						datos[0][0] = "";
						datos[0][1] = "";
						datos[0][0] = Integer.toString(prov.getId());
						datos[0][1] = prov.getNombre();
						dtm = new DefaultTableModel(datos, head);
						tableS.setModel(dtm);
					}
				}
			}
		}

		public void mouseClicked(MouseEvent e) {
			DefaultTableModel dtm;
			try {
				if (e.getClickCount() == 2) {
					deleteSupplier(table.getSelectedRow());
					dtm = new DefaultTableModel(datos, head);
					tableS.setModel(dtm);
				}
			}catch(ArrayIndexOutOfBoundsException | NullPointerException expt) {
				
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
	}
	
	public int NumCategory(int ca) {
		int r = 0;
		for(int x = 0; x < category.length; x++) {
			if(category[x].getId() == ca) {
				r = x;
				x = category.length;
				return r;
			}
		}
		return r;
	}
	
	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getClickCount() == 1) {
				datos = c.selectProductSuppliers(""+table.getModel().getValueAt(table.getSelectedRow(), 1));
				categories.setSelectedIndex(NumCategory((Integer)table.getModel().getValueAt(table.getSelectedRow(), 2)));
				clave.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 1));
				nombre.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 0));
				paquete.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 3));
				costo.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 4));
				precio1.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 5));
				precio2.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 6));
				prod.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 0));
				tableS.setModel(new DefaultTableModel(datos, head));
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
}
