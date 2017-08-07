package com.SoloSolar.interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.Capsulas.Proveedor;

public class Validaciones {
	
	public boolean validarCliente(Cliente c) {
		boolean clienteCorrecto = true;
		String mensajeErrores = "Favor de corregir los siguientes datos: ";
		
		if(validarSoloLetrasNoVacio(c.getNombre())) {
			mensajeErrores += "\nEl nombre debe ser solo letras (No vacio)";
			clienteCorrecto = false;
		}
		if(validarSoloLetrasNoVacio(c.getApellidos())) {
			mensajeErrores += "\nEl apellido debe ser solo letras (No vacio)";
			clienteCorrecto = false;
		}
		if(validarLongitud(c.getRFC(), 13)) {
			mensajeErrores += "\nEl RFC debe contener 13 caracteres";
			clienteCorrecto = false;
		}
		if(validarEmail(c.getEmail())) {
			mensajeErrores += "\nEl email debe ser (Ejemplo): email@example.com";
			clienteCorrecto = false;
		}
		if(validarLongitud(c.getCP(), 5)) {
			mensajeErrores += "\nEl codigo postal debe ser de 5 digitos";
			clienteCorrecto = false;
		}
		if(validarSoloNumerosVacios(c.getTelEmp())) {
			mensajeErrores += "\nEl telefono no puede contener letras";
			clienteCorrecto = false;
		}
		if(validarSoloNumerosVacios(c.getTelefono())) {
			mensajeErrores += "\nEl celular no puede contener letras";
			clienteCorrecto = false;
		}
		if(!clienteCorrecto) {
			JOptionPane.showMessageDialog(null, mensajeErrores, "¡Error!", JOptionPane.ERROR_MESSAGE);
		}
		return clienteCorrecto;
	}
	
	public boolean validarProducto(Producto p) {
		boolean clienteCorrecto = true;
		String mensajeErrores = "Favor de corregir los siguientes datos: ";
		
		if(validarCamposNoVacios(p.getClave())) {
			mensajeErrores += "\nLa clave no puede estar vacia";
			clienteCorrecto = false;
		}
		if(validarCamposNoVacios(p.getNombre())) {
			mensajeErrores += "\nEl producto no puede estar vacio";
			clienteCorrecto = false;
		}
		if(validarSoloNumerosNoVacios(String.valueOf(p.getPaquete()))) {
			mensajeErrores += "\nEl paquete solo puede ser numeros (No vacios)";
			clienteCorrecto = false;
		}
		if(validarSoloNumerosNoVacios(String.valueOf(p.getCosto()))) {
			mensajeErrores += "\nEl costo solo puede ser numeros (No vacios)";
			clienteCorrecto = false;
		}
		if(validarSoloNumerosNoVacios(String.valueOf(p.getPrecio1()))) {
			mensajeErrores += "\nEl precio 1 solo puede ser numeros (No vacios)";
			clienteCorrecto = false;
		}
		if(validarSoloNumerosNoVacios(String.valueOf(p.getPrecio2()))) {
			mensajeErrores += "\nEl precio 2 solo puede ser numeros (No vacios)";
			clienteCorrecto = false;
		}
		if(!clienteCorrecto) {
			JOptionPane.showMessageDialog(null, mensajeErrores, "¡Error!", JOptionPane.ERROR_MESSAGE);
		}
		return clienteCorrecto;
	}
	
	public boolean validarCategorias(Categoria c) {
		boolean clienteCorrecto = true;
		String mensajeErrores = "Favor de corregir los siguientes datos: ";
		
		if(validarCamposNoVacios(c.getNombre())) {
			mensajeErrores += "\nEl nombre no puede estar vacio";
			clienteCorrecto = false;
		}
		if(validarCamposNoVacios(c.getDescripcion())) {
			mensajeErrores += "\nLa descripcion no puede estar vacia";
			clienteCorrecto = false;
		}
		if(!clienteCorrecto) {
			JOptionPane.showMessageDialog(null, mensajeErrores, "¡Error!", JOptionPane.ERROR_MESSAGE);
		}
		return clienteCorrecto;
	}
	
	public boolean validarProveedores(Proveedor p) {
		boolean clienteCorrecto = true;
		String mensajeErrores = "Favor de corregir los siguientes datos: ";
		
		if(validarCamposNoVacios(p.getNombre())) {
			mensajeErrores += "\nEl nombre no puede estar vacio";
			clienteCorrecto = false;
		}
		if(validarCamposNoVacios(p.getDireccion())) {
			mensajeErrores += "\nLa direccion no puede estar vacia";
			clienteCorrecto = false;
		}
		if(validarSoloNumerosVacios(p.getTelefono())) {
			mensajeErrores += "\nEl telefono no puede contener letras.";
			clienteCorrecto = false;
		}
		if(validarEmail(p.getEmail())) {
			mensajeErrores += "\nEl email debe ser (Ejemplo): email@example.com";
			clienteCorrecto = false;
		}
		if(!clienteCorrecto) {
			JOptionPane.showMessageDialog(null, mensajeErrores, "¡Error!", JOptionPane.ERROR_MESSAGE);
		}
		return clienteCorrecto;
	}
	
	public boolean validarCamposNoVacios(String cadena) {
		return cadena.equals("") ? true : false;
	}
	
	public boolean validarSoloNumerosNoVacios(String num) {
		if(!num.equals("")) {
			String expReg = "[0-9.]+";
			
			Pattern pattern = Pattern.compile(expReg);
			
			Matcher matcher = pattern.matcher(num);
			return !matcher.matches();
		} else {
			return true;
		}
	}
	
	public boolean validarSoloNumerosVacios(String num) {
		if(!num.equals("")) {
			String expReg = "[0-9]+";
			
			Pattern pattern = Pattern.compile(expReg);
			
			Matcher matcher = pattern.matcher(num);
			return !matcher.matches();
		} else {
			return false;
		}
	}
	
	public boolean validarSoloLetrasNoVacio(String nombre) {
		if(!nombre.equals("")) {
			String expReg = "[a-zA-Z]+";
			
			Pattern pattern = Pattern.compile(expReg);
				
			Matcher matcher = pattern.matcher(nombre);
			return !matcher.matches();
		} else {
			return true;
		}
	}
	
	public boolean validarEmail(String email) {
		if(!email.equals("")) {
			String expReg = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(expReg);
	
			Matcher matcher = pattern.matcher(email);
	        return !matcher.matches();
		} else {
			return true;
		}
	}
	
	public boolean validarLongitud(String cadena, int tamaño) {
		if(!cadena.equals("")) {
			if(cadena.length() != tamaño) 
				return true;
			else
				return false;
		} else { 
			return false;
		}
	}
	
}