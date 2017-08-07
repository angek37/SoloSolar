package com.SoloSolar.interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.SoloSolar.Capsulas.Cliente;

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