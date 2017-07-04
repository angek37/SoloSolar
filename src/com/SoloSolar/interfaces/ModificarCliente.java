package com.SoloSolar.interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ModificarCliente extends JPanel {
	private JLabel nombreLbl, apellidosLbl, calleLbl, coloniaLbl, ciudadLbl, estadoLbl, celLbl, telEmpLbl;
	private JTextField nombreTF, apellidosTF, calleTF, coloniaTF, ciudadTF, estadoTF, celTF, telEmpTF; 
		
	public ModificarCliente () {
		nombreLbl = new JLabel("Nombre: ");
		apellidosLbl = new JLabel("Apellidos: ");
		calleLbl = new JLabel("Calle: ");
		coloniaLbl = new JLabel("Colonia: ");
		ciudadLbl = new JLabel("Ciudad: ");
		estadoLbl = new JLabel("Estado: ");
		celLbl = new JLabel("Telefono Celular: ");
		telEmpLbl = new JLabel("Telefono Empresa: ");
		nombreTF = new JTextField();
		apellidosTF = new JTextField();
		calleTF = new JTextField();
		coloniaTF = new JTextField();
		ciudadTF = new JTextField();
		estadoTF = new JTextField();
		celTF = new JTextField();
		telEmpTF = new JTextField();

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(apellidosLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		add(nombreTF, gbc);
	}

}