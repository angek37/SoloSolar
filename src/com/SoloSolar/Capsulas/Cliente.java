package com.SoloSolar.Capsulas;

public class Cliente {
	private int id;
	private String rfc, nombre, apellidos, calle, colonia, cp, ciudad, estado, email, telefono, telEmp, noDir;
	private Double saldo;
	
	public Cliente() {
		
	}
	
	public Cliente(String rfc, String nombre, String apellidos, String calle, String colonia, String cp, String ciudad,
			String estado, String email, String telefono, String telEmp, String noDir) {
		super();
		this.rfc = rfc;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.calle = calle;
		this.colonia = colonia;
		this.cp = cp;
		this.ciudad = ciudad;
		this.estado = estado;
		this.email = email;
		this.telefono = telefono;
		this.telEmp = telEmp;
		this.noDir = noDir;
	}



	public Cliente(int id, String rfc, String nombre, String apellidos, String calle, String colonia, String cp,
			String ciudad, String estado, String email, String telefono, String telEmp, String noDir) {
		super();
		this.id = id;
		this.rfc = rfc;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.calle = calle;
		this.colonia = colonia;
		this.cp = cp;
		this.ciudad = ciudad;
		this.estado = estado;
		this.email = email;
		this.telefono = telefono;
		this.telEmp = telEmp;
		this.noDir = noDir;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setRFC(String rfc) {
		this.rfc = rfc;
	}
	
	public String getRFC() {
		return rfc;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public String getCalle() {
		return calle;
	}
	
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	public String getColonia() {
		return colonia;
	}
	
	public void setCP(String cp) {
		this.cp = cp;
	}
	
	public String getCP() {
		return cp;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelEmp(String telEmp) {
		this.telEmp = telEmp;
	}
	
	public String getTelEmp() {
		return telEmp;
	}
	
	public void setNoDir(String noDir) {
		this.noDir = noDir;
	}
	
	public String getNoDir() {
		return noDir;
	}
	
	public String toString() {
		return id + ": " +  nombre;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
}
