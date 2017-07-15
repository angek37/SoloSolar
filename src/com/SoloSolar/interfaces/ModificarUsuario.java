package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.SoloSolar.Capsulas.Usuario;
import com.SoloSolar.DB.UsuarioBD;

public class ModificarUsuario extends JPanel implements ActionListener, MouseListener {
	private JLabel nombreLBL, usuarioLBL, passwordLBL, titulo;
	private JTextField nombreTF, usuarioTF;
	private JPasswordField passwordTF;
	private JPanel panelN, panelC, panelS;
	private JButton guardar, activarCambios, verPass;
	private char defaultEchoChar;
	private ImageIcon ver = new ImageIcon(new ImageIcon("assets/eye.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	
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
		verPass = new JButton(ver);
		verPass.setBorder(null);
		
		nombreTF.setEditable(false);
		usuarioTF.setEditable(false);
		passwordTF.setEditable(false);
		defaultEchoChar = passwordTF.getEchoChar();
		panelN = new JPanel();
		panelC = new JPanel();
		panelS = new JPanel();
		passwordTF.setText(UsuarioBD.getPass());
		nombreTF.setText(UsuarioBD.getNombre());
		usuarioTF.setText(UsuarioBD.getUsuario());
		
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
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(verPass, gbc);
		verPass.addMouseListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Usuario u = new Usuario();
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
			 u.setNombre(nombreTF.getText());
			 u.setUsuario(usuarioTF.getText());
			 u.setPassword(String.valueOf(passwordTF.getPassword()));
			 UsuarioBD.ModificarUsuario(u);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == verPass) {
			passwordTF.setEchoChar((char) 0); 
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		passwordTF.setEchoChar(defaultEchoChar);
	}

}