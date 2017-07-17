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
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;

public class AdministrarCategorias extends JPanel implements MouseListener {
	private JTextField id, nombre, descripcion;
	private JComboBox<Categoria> categories;
	private JTable table;
	private JLabel prod;
	Consulta select = new Consulta();
	Categoria[] category = select.selectCategories();
	
	public AdministrarCategorias() {
		setLayout(new BorderLayout());
		table = new JTable(new CategoryModel());
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(150, 0));
		add(scrollPane, BorderLayout.WEST);
		add(new ModifyCategory(), BorderLayout.CENTER);
		
		JLabel titulo = new JLabel("Administrar categorías");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo, BorderLayout.NORTH);
	}
	
	public class CategoryModel extends AbstractTableModel {
		Consulta select = new Consulta();
		Categoria[] category = select.selectCategories();
		
		public int getRowCount() {
			return category.length;
		}

		public int getColumnCount() {
			return 1;
		}
		
		public String getColumnName(int col) {
			return "Nombre";
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			   Categoria categoria = category[rowIndex];
			   switch (columnIndex) {
			   case 0:
				   value = categoria.getNombre();
				   break;
			   case 1:
				   value = categoria.getDescripcion();
				   break;
			   case 2:
				   value = categoria.getId();
				   break;
			   }
	           return value;
		}
		
	}
	
	public class ModifyCategory extends JPanel implements ActionListener{
		private JButton actualizar, eliminar;
		private JPanel updateP, deleteP;
		
		public ModifyCategory() {
			setLayout(new BorderLayout());
			updateP = new JPanel();
			deleteP = new JPanel();
			
			updateP.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			updateP.setBorder(new CompoundBorder(new TitledBorder("Modificar valores de categoría"), new EmptyBorder(12, 0, 0, 0)));
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(4,4,4,4);
			updateP.add(new JLabel("ID:"), gbc);
			id = new JTextField();
			id.setEnabled(false);
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(id, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			updateP.add(new JLabel("Nombre de categoría:"), gbc);
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
			updateP.add(new JLabel("Descripción:"), gbc);
			descripcion = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(descripcion, gbc);
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
			deleteP.setBorder(new CompoundBorder(new TitledBorder("Eliminar categoría"), new EmptyBorder(12, 12, 12, 12)));
			prod = new JLabel("Productos dentro de esta categoría: ");
			gbc2.gridx = 0;
			gbc2.gridy = 0;
			gbc2.anchor = GridBagConstraints.WEST;
			gbc2.gridwidth = 2;
			gbc2.fill = GridBagConstraints.HORIZONTAL;
			deleteP.add(prod, gbc2);
			gbc2.gridwidth = 1;
			gbc2.gridy++;
			gbc2.weightx = 1;
			gbc2.anchor = GridBagConstraints.WEST;
			gbc2.insets = new Insets(20, 0, 20, 0);
			deleteP.add(new JLabel("Transferir a: "), gbc2);
			
			categories = new JComboBox<Categoria>(category);
			gbc2.gridx++;
			deleteP.add(categories, gbc2);
			
			eliminar = new JButton("Eliminar");
			gbc2.insets = new Insets(0, 0, 0, 0);
			gbc2.gridy++;
			deleteP.add(eliminar, gbc2);
			eliminar.addActionListener(this);
			
			add(updateP, BorderLayout.CENTER);
			add(deleteP, BorderLayout.SOUTH);
		}
		

		public void actionPerformed(ActionEvent e) {
			Insert in = new Insert();
			Categoria cat;
			try {
				if(e.getSource() == actualizar) {
					cat = new Categoria(Integer.parseInt(id.getText()), nombre.getText(), descripcion.getText());
					if(in.UpdateCategory(cat)) {
						JOptionPane.showMessageDialog(null, "Categoria modificada exitosamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "No ha sido posible modificar la categoria", "Error", JOptionPane.ERROR_MESSAGE);
					}
					nombre.setText("");
					descripcion.setText("");
					id.setText("");
					table.setModel(new CategoryModel());
				}else if (e.getSource() == eliminar) {
					Categoria substituteCat = (Categoria) categories.getSelectedItem();
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar la categoría '"+ table.getModel().getValueAt(table.getSelectedRow(), 0) +"'?", "Cerrar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(reply == JOptionPane.YES_OPTION) {
						if(in.ChangeCategory((int)table.getModel().getValueAt(table.getSelectedRow(), 2), substituteCat.getId())) {
							if(in.DeleteCategory((int)table.getModel().getValueAt(table.getSelectedRow(), 2))) {
								in.shutdown();
								JOptionPane.showMessageDialog(null, "Categoria eliminada exitosamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
								table.setModel(new CategoryModel());
								nombre.setText("");
								descripcion.setText("");
								id.setText("");
							}else {
								in.shutdown();
								JOptionPane.showMessageDialog(null, "No ha sido posible eliminar la categoria", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible transferir los productos", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}catch(NumberFormatException | ArrayIndexOutOfBoundsException exp) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado una categoría", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getClickCount() == 1) {
				nombre.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 0));
				descripcion.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 1));
				id.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 2));
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
