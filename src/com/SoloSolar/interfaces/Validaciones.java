package com.SoloSolar.interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.Capsulas.Usuario;

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
				mensajeErrores += "\nEl RFC debe contener al menos 10 a 13 caracteres";
				clienteCorrecto = false;
			}
		}
		if(!c.getEmail().equals("")) {
			if(validarEmail(c.getEmail())) {
				mensajeErrores += "\nEl email debe ser (Ejemplo): email@example.com";
				clienteCorrecto = false;
			}
		}
		if(!c.getCP().equals("") && !c.getCP().equals("0")) {
			if(validarCP(c.getCP())) {
				mensajeErrores += "\nEl codigo postal debe ser de 5 digitos numéricos";
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
		c.setRFC(c.getRFC().toUpperCase());
		c.setNombre(c.getNombre().toUpperCase());
		c.setApellidos(c.getApellidos().toUpperCase());
		c.setCalle(c.getCalle().toUpperCase());
		c.setNoDir(c.getNoDir().toUpperCase());
		c.setColonia(c.getColonia().toUpperCase());
		c.setCiudad(c.getCiudad().toUpperCase());
		c.setEstado(c.getEstado().toUpperCase());
		c.setEmail(c.getEmail().toUpperCase());
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
		p.setClave(p.getClave().toUpperCase());
		p.setNombre(p.getNombre().toUpperCase());
		p.setNombre(p.getNombre().replace("'", "\""));
		return clienteCorrecto;
	}
	
	public boolean validarCategorias(Categoria c) {
		boolean clienteCorrecto = true;
		String mensajeErrores = "Favor de corregir los siguientes datos: ";
		
		if(validarCamposNoVacios(c.getNombre())) {
			mensajeErrores += "\nEl nombre no puede estar vacio";
			clienteCorrecto = false;
		}
		if(!clienteCorrecto) {
			JOptionPane.showMessageDialog(null, mensajeErrores, "¡Error!", JOptionPane.ERROR_MESSAGE);
		}
		c.setNombre(c.getNombre().toUpperCase());
		c.setDescripcion(c.getDescripcion().toUpperCase());
		c.setNombre(c.getNombre().replace("'", "\""));
		c.setDescripcion(c.getDescripcion().replace("'", "\""));
		return clienteCorrecto;
	}
	
	public boolean validarProveedores(Proveedor p) {
		boolean clienteCorrecto = true;
		String mensajeErrores = "Favor de corregir los siguientes datos: ";
		
		if(validarCamposNoVacios(p.getNombre())) {
			mensajeErrores += "\nEl nombre no puede estar vacio";
			clienteCorrecto = false;
		}
		if(!p.getCp().equals("")) {
			if(validarCP(p.getCp())) {
				mensajeErrores += "\nEl codigo postal debe ser de 5 digitos numéricos";
				clienteCorrecto = false;
			}
		}else {
			p.setCp("0");
		}
		if(!p.getCiudad().equals("")) {
			if(validarSoloLetrasNoVacio(p.getCiudad())) {
				mensajeErrores += "\nEl nombre de la ciudad solo puede contener letras";
				clienteCorrecto = false;
			}
		}
		if(!p.getEstado().equals("")) {
			if(validarSoloLetrasNoVacio(p.getEstado())) {
				mensajeErrores += "\nEl nombre del estado solo puede contener letras";
				clienteCorrecto = false;
			}
		}
		if(!p.getEmail().equals("")) {
			if(validarEmail(p.getEmail())) {
				mensajeErrores += "\nEl email debe ser (Ejemplo): email@example.com";
				clienteCorrecto = false;
			}
		}
		if(!p.getCelular().equals("")) {
			if(validarSoloEnterosVacios(p.getCelular())) {
				mensajeErrores += "\nEl Celular no puede contener letras.";
				clienteCorrecto = false;
			}
		}
		if(!p.getTelefono().equals("")) {
			if(validarSoloEnterosVacios(p.getTelefono())) {
				mensajeErrores += "\nEl telefono no puede contener letras.";
				clienteCorrecto = false;
			}
		}
		if(!clienteCorrecto) {
			JOptionPane.showMessageDialog(null, mensajeErrores, "¡Error!", JOptionPane.ERROR_MESSAGE);
		}
		p.setNombre(p.getNombre().toUpperCase());
		p.setCalle(p.getCalle().toUpperCase());
		p.setNumero(p.getNumero().toUpperCase());
		p.setColonia(p.getColonia().toUpperCase());
		p.setCiudad(p.getCiudad().toUpperCase());
		p.setEstado(p.getEstado().toUpperCase());
		p.setEmail(p.getEmail().toUpperCase());
		return clienteCorrecto;
	}
	
	
	public boolean validarUsuario(Usuario u) {
		boolean clienteCorrecto = true;
		String mensajeErrores = "Favor de corregir los siguientes datos: ";
		if(u.getUsuario().equals("")) {
			mensajeErrores += "\nEl nombre de usuario no puede estar vacio.";
			clienteCorrecto = false;
		}
		if(u.getPassword().equals("")) {
			mensajeErrores += "\nLa contraseña no puede estar vacia.";
			clienteCorrecto = false;
		}
		if(validarSoloLetrasNoVacio(u.getNombre())) {
			mensajeErrores += "\nEl nombre debe contener únicamente letras.";
			clienteCorrecto = false;
		}
		if(!u.getRFC().equals("")) {
			if(!RangoLongitud(u.getRFC(), 10, 13)) {
				mensajeErrores += "\nEl RFC debe contener al menos 10 a 13 caracteres";
				clienteCorrecto = false;
			}
		}
		if(!clienteCorrecto) {
			JOptionPane.showMessageDialog(null, mensajeErrores, "¡Error!", JOptionPane.ERROR_MESSAGE);
		}
		u.setNombre(u.getNombre().toUpperCase());
		u.setRFC(u.getRFC().toUpperCase());
		return clienteCorrecto;
	}
	
	public boolean validarCamposNoVacios(String cadena) {
		return cadena.equals("") ? true : false;
	}
	
	public boolean validarSoloDecimales(String num) {
		if(!num.equals("")) {
			try {
				Double.parseDouble(num);
				return false;
			}catch(NumberFormatException exp) {
				return true;
			}
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
	
	public boolean validarCP(String cadena) {
		if(cadena.length() == 5) {
			String expReg = "[0-9]+";
			Pattern pattern = Pattern.compile(expReg);
			Matcher matcher = pattern.matcher(cadena);
			return !matcher.matches();
		}else {
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