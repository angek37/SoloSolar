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
		if(!c.getRFC().equals("")) {
			if(!RangoLongitud(c.getRFC(), 10, 13)) {
				mensajeErrores += "\nEl RFC debe contener al menos 10 caracteres";
				clienteCorrecto = false;
			}
		}
		if(!c.getEmail().equals("")) {
			if(validarEmail(c.getEmail())) {
				mensajeErrores += "\nEl email debe ser (Ejemplo): email@example.com";
				clienteCorrecto = false;
			}
		}
		if(!c.getNoDir().equals("")) {
			if(validarSoloEnterosVacios(c.getNoDir())) {
				mensajeErrores += "\nEl numero de direccion debe ser numero";
				clienteCorrecto = false;
			}
		}
		if(!c.getCP().equals("") && !c.getCP().equals("0")) {
			if(validarLongitud(c.getCP(), 5)) {
				mensajeErrores += "\nEl codigo postal debe ser de 5 digitos";
				clienteCorrecto = false;
			}
		}else {
			c.setCP("0");
		}
		if(!c.getTelEmp().equals("")) {
			if(validarSoloEnterosVacios(c.getTelEmp())) {
				mensajeErrores += "\nEl telefono no puede contener letras";
				clienteCorrecto = false;
			}
		}
		if(!c.getTelefono().equals("")) {
			if(validarSoloEnterosVacios(c.getTelefono())) {
				mensajeErrores += "\nEl celular no puede contener letras";
				clienteCorrecto = false;
			}
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
		if(!p.getPaquete().equals("")) {
			if(validarSoloEnterosVacios(String.valueOf(p.getPaquete()))) {
				mensajeErrores += "\nEl paquete solo puede ser numeros";
				clienteCorrecto = false;
			}
		}else {
			p.setPaquete("0");
		}
		if(validarSoloDecimales(String.valueOf(p.getCosto()))) {
			mensajeErrores += "\nEl costo solo puede ser numeros (No vacios)";
			clienteCorrecto = false;
		}
		if(validarSoloDecimales(String.valueOf(p.getPrecio1()))) {
			mensajeErrores += "\nEl precio 1 solo puede ser numeros (No vacios)";
			clienteCorrecto = false;
		}
		if(!p.getPrecio2().equals("")) {
			if(validarSoloDecimales(String.valueOf(p.getPrecio2()))) {
				mensajeErrores += "\nEl precio 2 solo puede ser numeros";
				clienteCorrecto = false;
			}
		}else {
			p.setPrecio2("0.0");
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
		if(validarCamposNoVacios(p.getCalle())) {
			mensajeErrores += "\nLa direccion no puede estar vacia";
			clienteCorrecto = false;
		}
		if(validarSoloEnterosVacios(p.getTelefono())) {
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
	
	public boolean validarSoloDecimales(String num) {
		if(!num.equals("")) {
			String expReg = "[0-9.]+";
			
			Pattern pattern = Pattern.compile(expReg);
			
			Matcher matcher = pattern.matcher(num);
			return !matcher.matches();
		} else {
			return true;
		}
	}
	
	public boolean validarSoloEnterosVacios(String num) {
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
			String expReg = "[a-zA-ZáéíóúÁÉÍÓÚ ]+";
			
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
	
	public boolean RangoLongitud(String cadena, int min, int max) {
		if(!cadena.equals("")) {
			if(cadena.length() >= min && cadena.length() <= max) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
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