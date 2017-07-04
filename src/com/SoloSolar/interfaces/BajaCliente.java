package com.SoloSolar.interfaces;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class BajaCliente extends JPanel {
	private JTable tabla;
	
	public BajaCliente() {
		add(new JLabel("Tabla :'v"));
		tabla = new JTable();
		add(tabla);
	}
}
