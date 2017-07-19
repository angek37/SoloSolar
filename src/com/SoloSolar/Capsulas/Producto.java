package com.SoloSolar.Capsulas;

public class Producto {
	private String Clave, Nombre;
	private int Categoria, Paquete, Costo, Precio1, Precio2;
	
	public Producto(String clave, String nombre, int categoria, int paquete, int costo, int precio1, int precio2) {
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
	public int getPaquete() {
		return Paquete;
	}
	public void setPaquete(int paquete) {
		Paquete = paquete;
	}
	public int getCosto() {
		return Costo;
	}
	public void setCosto(int costo) {
		Costo = costo;
	}
	public int getPrecio1() {
		return Precio1;
	}
	public void setPrecio1(int precio1) {
		Precio1 = precio1;
	}
	public int getPrecio2() {
		return Precio2;
	}
	public void setPrecio2(int precio2) {
		Precio2 = precio2;
	}

	public String toString() {
		return Nombre;
	}
	
}
