package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.DB.Consulta;

public class ModificarCategoria extends JPanel implements MouseListener {
	private JTextField id, nombre, descripcion;
	private JTable table;
	
	public ModificarCategoria() {
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
		titulo.setHorizontalAlignment(JLabel.CENTER);
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
		private JButton actualizar;
		
		public ModifyCategory() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			setBorder(new CompoundBorder(new TitledBorder("Modificar valores de categoría"), new EmptyBorder(12, 0, 0, 0)));
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(4,4,4,4);
			add(new JLabel("ID:"), gbc);
			id = new JTextField();
			id.setEnabled(false);
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			add(id, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			add(new JLabel("Nombre de categoría:"), gbc);
			nombre = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			add(nombre, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.weighty = 0;
			add(new JLabel("Descripción:"), gbc);
			descripcion = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			add(descripcion, gbc);
			actualizar = new JButton("Actualizar");
			gbc.gridy++;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			add(actualizar, gbc);
			actualizar.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == actualizar) {
				JOptionPane.showMessageDialog(null, "Button Pressed");
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			nombre.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 0));
			descripcion.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 1));
			id.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 2));
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
