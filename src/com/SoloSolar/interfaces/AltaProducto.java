package com.SoloSolar.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.Capsulas.TextPrompt;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;

public class AltaProducto extends JPanel implements ActionListener, KeyListener{
	private JLabel titulo;
	private JTextField clave, nombre, paquete, costo, precio1, precio2, por1, por2;
	private JComboBox<Categoria> categories;
	Consulta c = new Consulta();
	Categoria[] category = (Categoria[]) c.selectCategories();
	private JButton registrar;
	
	public AltaProducto() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//Fila 1
		gbc.insets = new Insets(4,10,10,4);
		gbc.ipady = 5;
		gbc.weightx = 0.05;
		titulo = new JLabel("Registrar nuevo producto");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		add(titulo, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(new JLabel("Clave:"), gbc);
		clave = new JTextField();
		gbc.ipadx = 200;
		gbc.gridx++;
		add(clave, gbc);
		//Fila 2
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Nombre:"), gbc);
		nombre = new JTextField();
		gbc.ipadx = 300;
		gbc.gridx++;
		add(nombre, gbc);
		//Fila 3
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Categoría:"), gbc);
		categories = new JComboBox<Categoria>(category);
		categories.setMaximumSize(new Dimension(200, 20));
		gbc.gridx++;
		add(categories, gbc);
		//Fila 4
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Paquete:"), gbc);
		paquete = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(paquete, gbc);
		//Fila 5
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Costo:"), gbc);
		costo = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(costo, gbc);
		//Fila 6
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Precio 1:"), gbc);
		precio1 = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(precio1, gbc);
		por1 = new JTextField();
		TextPrompt percent = new TextPrompt("%", por1, TextPrompt.Show.FOCUS_LOST);
		percent.changeAlpha(0.75f);
		gbc.gridx++;
		gbc.ipadx = 30;
		por1.addKeyListener(this);
		add(por1, gbc);
		//Fila 7
		gbc.ipadx = 0;
		gbc.gridx = 0;
		gbc.gridy++;
		add(new JLabel("Precio 2:"), gbc);
		precio2 = new JTextField();
		gbc.ipadx = 160;
		gbc.gridx++;
		add(precio2, gbc);
		por2 = new JTextField();
		TextPrompt percent2 = new TextPrompt("%", por2, TextPrompt.Show.FOCUS_LOST);
		percent2.changeAlpha(0.75f);
		gbc.gridx++;
		gbc.ipadx = 30;
		add(por2, gbc);
		por2.addKeyListener(this);
		//Fila 8
		gbc.gridx--;
		registrar = new JButton("Guardar Producto");
		gbc.ipadx = 160;
		gbc.gridy++;
		add(registrar, gbc);
		registrar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Insert i = new Insert();
		Categoria c;
		Producto p;
		if(e.getSource() == registrar) {
			c = (Categoria) categories.getSelectedItem();
			p = new Producto(clave.getText(), nombre.getText(), c.getId(), 
					Integer.parseInt(paquete.getText()), Double.parseDouble(costo.getText()), 
					Double.parseDouble(precio1.getText()), Double.parseDouble(precio2.getText()));
			if(i.InsertProduct(p)) {
				JOptionPane.showMessageDialog(null, "Producto registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
				clave.setText("");
				nombre.setText("");
				paquete.setText("");
				costo.setText("");
				precio1.setText("");
				precio2.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "No ha sido posible registrar el producto", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		double r;
		if(e.getSource() == por1 && !costo.getText().isEmpty()) {
			try {
				r = Double.parseDouble(costo.getText())*((Double.parseDouble(por1.getText())/100)+1);
				precio1.setText(Double.toString(r));
			}catch(NumberFormatException ex) {
				por1.setText("");
			}
		}else if(e.getSource() == por2 && !costo.getText().isEmpty()) {
			try {
				r = Double.parseDouble(costo.getText())*((Double.parseDouble(por2.getText())/100)+1);
				precio2.setText(Double.toString(r));
			}catch(NumberFormatException ex) {
				por2.setText("");
			}
		}
	}
}
