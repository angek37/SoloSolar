package com.SoloSolar.interfaces;

import javax.swing.JTable;

public class BuscarProducto {
	String[][] renglones;
	String[] head = {"Clave", "Nombre de Producto", "Cantidad", "Pack", "L", "Precio", "SubTotal"};
	JTable table;
	
	public BuscarProducto(String[][] renglones, JTable table) {
		this.renglones = renglones;
		this.table = table;
		
	}
}
