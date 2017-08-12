package com.SoloSolar.interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Acerca {
	private ImageIcon loading = new ImageIcon(new ImageIcon("assets/sun-loading.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
	
	public Acerca(JFrame padre) {
		JDialog j = new JDialog(padre);
		j.setLocationRelativeTo(padre);
		j.setTitle("Acerca de Solo - Solar");
		j.setVisible(true);
		j.setMinimumSize(new Dimension(320, 250));
		j.setResizable(false);
		j.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		JLabel icono = new JLabel(loading);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		j.add(icono, gbc);
		JLabel titulo = new JLabel("Solo - Solar Software tool");
		titulo.setFont(new Font("Arial", Font.BOLD, 15));
		gbc.gridy++;
		j.add(titulo, gbc);
		gbc.gridy++;
		j.add(new JLabel("Versión: 1.0.3 Release"), gbc);
		JLabel developers = new JLabel("Desarrollador por:");
		developers.setFont(new Font("Arial", Font.BOLD, 15));
		gbc.gridy++;
		j.add(developers, gbc);
		gbc.gridy++;
		j.add(new JLabel("Damián Desiderio López Lira"), gbc);
		gbc.gridy++;
		j.add(new JLabel("Miguel Ángel Rodríguez González"), gbc);
		JLabel copyright = new JLabel("Copyright © 2017, López Lira & Rodríguez González, All rights reserved.");
		copyright.setFont(new Font("Arial", Font.PLAIN, 8));
		gbc.gridy++;
		j.add(copyright, gbc);
	}

}
