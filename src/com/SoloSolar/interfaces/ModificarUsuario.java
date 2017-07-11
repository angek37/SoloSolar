package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class ModificarUsuario extends JPanel implements ActionListener {
	private JLabel nombreLBL, usuarioLBL, passwordLBL, titulo;
	private JTextField nombreTF, usuarioTF;
	private JPasswordField passwordTF;
	private JPanel panelN, panelC, panelS;
	private JButton guardar, activarCambios;
	
	public ModificarUsuario () {
		titulo = new JLabel("Modificar Usuario");
		nombreLBL = new JLabel("Nombre: ");
		usuarioLBL = new JLabel("Usuario: ");
		passwordLBL = new JLabel("Contraseña: ");
		nombreTF = new JTextField();
		usuarioTF = new JTextField();
		passwordTF = new JPasswordField();
		guardar = new JButton("Modificar");
		activarCambios = new JButton("Habilitar Campos");
		nombreTF.setEditable(false);
		usuarioTF.setEditable(false);
		passwordTF.setEditable(false);
		panelN = new JPanel();
		panelC = new JPanel();
		panelS = new JPanel();
		
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titulo.setForeground(Color.RED);
		
		setLayout(new BorderLayout());
		add(panelN, BorderLayout.NORTH);
		panelN.add(titulo);
		
		add(panelS, BorderLayout.SOUTH);
		panelS.add(activarCambios);
		panelS.add(guardar);
		activarCambios.addActionListener(this);
		guardar.addActionListener(this);
		
		add(panelC, BorderLayout.CENTER);
		panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelC.add(nombreLBL, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(nombreTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(usuarioLBL, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(usuarioTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(passwordLBL, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(passwordTF, gbc);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == activarCambios) {
			if (activarCambios.getText().equals("Habilitar Campos")) {
				activarCambios.setText("Deshabilitar Campos");
				nombreTF.setEditable(true);
				usuarioTF.setEditable(true);
				passwordTF.setEditable(true);
			} else {
				activarCambios.setText("Habilitar Campos");
				nombreTF.setEditable(false);
				usuarioTF.setEditable(false);
				passwordTF.setEditable(false);
			}
		} else if(e.getSource() == guardar) {
			
		}
	}

}