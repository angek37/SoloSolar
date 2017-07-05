package com.SoloSolar.interfaces;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.DB.Insert;


public class AltaCategoria extends JPanel implements ActionListener{
		private JTextField nombre, descripcion;
		private JButton registrar;
		
		public AltaCategoria() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = 3;
			add(new JLabel("Registrar nueva categoría"), gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.weightx = 1;
			gbc.weighty = 0.5;
			add(new JLabel("Nombre de categoría:"), gbc);
			nombre = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0.5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			add(nombre, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.weightx = 1;
			gbc.weighty = 0.5;
			add(new JLabel("Descripción:"), gbc);
			descripcion = new JTextField();
			gbc.gridx+=2;
			gbc.weightx = 1;
			gbc.weighty = 0.5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			add(descripcion, gbc);
			registrar = new JButton("Registrar categoría");
			gbc.gridy++;
			gbc.weightx = 1;
			gbc.weighty = 0.5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			add(registrar, gbc);
			registrar.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			Categoria cat = new Categoria();
			if(e.getSource() == registrar) {
				cat.setNombre(nombre.getText());
				cat.setDescripcion(descripcion.getText());
				new Insert(cat);
			}
		}
		
	
	public static void main(String[] mr) {
		new AltaCategoria();
	}

}
