package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.DB.Consulta;

public class ModificarCategoria extends JPanel {
	private JTextField nombre, descripcion;
	private JTable table;
	
	public ModificarCategoria() {
		setLayout(new BorderLayout());
		table = new JTable(new CategoryModel());
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(150, 0));
		add(scrollPane, BorderLayout.WEST);
		add(new ModifyCategory(), BorderLayout.CENTER);
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
			Categoria categoria = category[rowIndex];
			return categoria.getNombre();
		}
		
	}
	
	public class ModifyCategory extends JPanel implements ActionListener{
		private JButton actualizar;
		
		public ModifyCategory() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weighty = 0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.insets = new Insets(4,4,4,4);
			add(new JLabel("Actualizar categoría"), gbc);
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
			
			
		}
	}

}
