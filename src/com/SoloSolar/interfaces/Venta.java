package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Venta extends JPanel {
	
	public Venta() {
		setLayout(new BorderLayout());
		add(new DatosP(), BorderLayout.NORTH);
		add(new TablaP(), BorderLayout.CENTER);
		add(new BotonesP(), BorderLayout.SOUTH);
	}
	
	public class DatosP extends JPanel {
		JTextField pedido, idCliente, nombreCliente, fecha;
		JButton buscarCliente;
		private ImageIcon customerIco = new ImageIcon(
				new ImageIcon("assets/searchCustomer.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		public DatosP() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(10, 10, 0, 10);
			gbc.weightx = 1;
			gbc.anchor = GridBagConstraints.EAST;
			gbc.ipady = 8;
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(new JLabel("Pedido:"), gbc);
			pedido = new JTextField();
			pedido.setEnabled(false);
			gbc.ipadx = 80;
			gbc.gridx++;
			add(pedido, gbc);
			gbc.gridx++;
			gbc.ipadx = 0;
			add(new JLabel("Cliente:"), gbc);
			buscarCliente = new JButton(customerIco);
			buscarCliente.setBorder(null);
			buscarCliente.setBackground(null);
			buscarCliente.setFocusable(false);
			gbc.ipadx = 30;
			gbc.gridx++;
			add(buscarCliente, gbc);
			idCliente = new JTextField();
			idCliente.setEnabled(false);
			gbc.gridx++;
			gbc.ipadx = 80;
			add(idCliente, gbc);
			nombreCliente = new JTextField();
			nombreCliente.setEnabled(false);
			gbc.ipadx = 300;
			gbc.gridx++;
			add(nombreCliente, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 0;
			add(new JLabel("Fecha:"), gbc);
			fecha = new JTextField(dateFormat.format(date));
			gbc.ipadx = 80;
			gbc.gridx++;
			add(fecha, gbc);
		}
		
	}
	
	public class TablaP extends JPanel {
		
	}
	
	public class BotonesP extends JPanel {
		
	}
}
