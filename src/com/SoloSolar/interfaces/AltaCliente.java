package com.SoloSolar.interfaces;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AltaCliente extends JPanel {
	private JLabel nombreLbl, apellidosLbl, calleLbl, coloniaLbl, ciudadLbl, estadoLbl, celLbl, telEmpLbl;
	private JTextField nombreTF, apellidosTF, calleTF, coloniaTF, ciudadTF, estadoTF, celTF, telEmpTF;
	private JPanel panel;
		
	public AltaCliente () {
		nombreLbl = new JLabel("Nombre: ");
		apellidosLbl = new JLabel("Apellidos: ");
		calleLbl = new JLabel("Calle: ");
		coloniaLbl = new JLabel("Colonia: ");
		ciudadLbl = new JLabel("Ciudad: ");
		estadoLbl = new JLabel("Estado: ");
		celLbl = new JLabel("Telefono Celular: ");
		telEmpLbl = new JLabel("Telefono Empresa: ");
		nombreTF = new JTextField(10);
		apellidosTF = new JTextField(10);
		calleTF = new JTextField(20);
		coloniaTF = new JTextField(20);
		ciudadTF = new JTextField(20);
		estadoTF = new JTextField(20);
		celTF = new JTextField(20);
		telEmpTF = new JTextField(20);
		panel = new JPanel();
		add(panel);

		setLayout(new GridLayout(1, 4, 5, 5));
        add(nombreLbl);
        add(nombreTF);
        add(apellidosLbl);
        add(apellidosTF);
		
	}

}