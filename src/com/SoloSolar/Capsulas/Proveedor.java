package com.SoloSolar.Capsulas;

public class Proveedor {
	int id;
	String nombre, calle, numero, colonia, ciudad, estado, email, celular, telefono, cp;
	String direccion;
	
	public Proveedor() {
		
	}

	public Proveedor(int id, String nombre, String calle, String numero, String colonia, String ciudad, String estado,
			String email, String celular, String telefono, String cp) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.calle = calle;
		this.numero = numero;
		this.colonia = colonia;
		this.ciudad = ciudad;
		this.estado = estado;
		this.email = email;
		this.celular = celular;
		this.telefono = telefono;
		this.cp = cp;
	}

	public Proveedor(String nombre, String calle, String numero, String colonia, String ciudad, String estado,
			String email, String celular, String telefono, String cp) {
		super();
		this.nombre = nombre;
		this.calle = calle;
		this.numero = numero;
		this.colonia = colonia;
		this.ciudad = ciudad;
		this.estado = estado;
		this.email = email;
		this.celular = celular;
		this.telefono = telefono;
		this.cp = cp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String toString() {
		return nombre;
	}
	
}
