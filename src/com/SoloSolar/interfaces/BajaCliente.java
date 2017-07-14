package com.SoloSolar.interfaces;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.SoloSolar.interfaces.Render;

public class BajaCliente extends JPanel {
	private JScrollPane jsp;
	private JTable tabla;
	
	public BajaCliente() {
		jsp = new JScrollPane();
		tabla = new JTable(new Object[][]{{"1", "Pedro"}, {"2", "Juan"}, 
			   {"3", "Rosa"}, {"4", "Maria"}},
				new Object[]{"Codigo", "Nombre", "M", "E"});
		//ver_tabla(tabla);
		add(new JLabel("das"));
		add(jsp);
		jsp.add(tabla);
	}
	
	private void ver_tabla(JTable table) {
		table.setDefaultRenderer(Object.class, new Render());
		
		JButton btn1 = new JButton("Modificar");
		JButton btn2 = new JButton("Eliminar");
		
		DefaultTableModel d = new DefaultTableModel(
			new Object[][]{{"1", "Pedro", btn1, btn2}, {"2", "Juan", btn1, btn2}, 
						   {"3", "Rosa", btn1, btn2}, {"4", "Maria", btn1, btn2}},
			new Object[]{"Codigo", "Nombre", "M", "E"}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(d);
	}
}
