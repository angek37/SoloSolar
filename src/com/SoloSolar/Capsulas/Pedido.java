package com.SoloSolar.Capsulas;

public class Pedido {
	int id, customer;
	boolean iva;
	String fecha, observaciones;
	
	public Pedido(int customer, String fecha, boolean iva, String observaciones) {
		super();
		this.customer = customer;
		this.fecha = fecha;
		this.observaciones = observaciones;
	}
	
	public Pedido(int id, int customer, String fecha, boolean iva, String observaciones) {
		super();
		this.id = id;
		this.customer = customer;
		this.fecha = fecha;
		this.observaciones = observaciones;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCustomer() {
		return customer;
	}
	
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public boolean getIva() {
		return iva;
	}

	public void setIva(boolean iva) {
		this.iva = iva;
	}

	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
