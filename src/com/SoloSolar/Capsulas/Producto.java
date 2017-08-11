package com.SoloSolar.Capsulas;

public class Producto {
	private String Clave, Nombre, categoriaNombre;
	private int Categoria;
	private String Paquete, Costo, Precio1, Precio2;
	
	public Producto(String clave, String nombre, int categoria, String paquete, String costo, String precio1, String precio2) {
		super();
		Clave = clave;
		Nombre = nombre;
		Categoria = categoria;
		Paquete = paquete;
		Costo = costo;
		Precio1 = precio1;
		Precio2 = precio2;
	}
	
	public Producto() {
		super();
	}

	public String getClave() {
		return Clave;
	}
	public void setClave(String clave) {
		Clave = clave;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getCategoria() {
		return Categoria;
	}
	public void setCategoria(int categoria) {
		Categoria = categoria;
	}
	public String getPaquete() {
		return Paquete;
	}
	public void setPaquete(String paquete) {
		Paquete = paquete;
	}
	public String getCosto() {
		return Costo;
	}
	public void setCosto(String costo) {
		Costo = costo;
	}
	public String getPrecio1() {
		return Precio1;
	}
	public void setPrecio1(String precio1) {
		Precio1 = precio1;
	}
	public String getPrecio2() {
		return Precio2;
	}
	public void setPrecio2(String precio2) {
		Precio2 = precio2;
	}
	public void setCategoriaNombre(String cat) {
		this.categoriaNombre = cat;
	}
	
	public String getCategoriaNombre() {
		return categoriaNombre;
	}

	public String toString() {
		return Nombre;
	}
}
