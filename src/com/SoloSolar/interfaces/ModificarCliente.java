package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.DB.ClienteBD;

public class ModificarCliente extends JPanel implements ItemListener {
	private JComboBox personas;
	private JLabel titulo;
	private JLabel rfcLbl, nombreLbl, apellidosLbl, calleLbl, coloniaLbl, cpLbl, 
		ciudadLbl, estadoLbl, emailLbl, celLbl, telEmpLbl;
	private JTextField rfcTF, nombreTF, apellidosTF, calleTF, coloniaTF, cpTF, 
		ciudadTF, estadoTF, emailTF, celTF, telEmpTF;
	private JPanel panelN, panelC, panelS;
	private JButton guardar;
		
	public ModificarCliente() {
		String nose[] = ClienteBD.ClientesExistentes();
		personas = new JComboBox(nose);
		titulo = new JLabel("Modificar cliente");
		rfcLbl = new JLabel("RFC: ");
		nombreLbl = new JLabel("Nombre: ");
		apellidosLbl = new JLabel("Apellidos: ");
		calleLbl = new JLabel("Calle: ");
		coloniaLbl = new JLabel("Colonia: ");
		cpLbl = new JLabel("Codigo Postal: ");
		ciudadLbl = new JLabel("Ciudad: ");
		estadoLbl = new JLabel("Estado: ");
		emailLbl = new JLabel("Email: ");
		celLbl = new JLabel("Telefono Celular: ");
		telEmpLbl = new JLabel("Telefono Empresa: ");
		rfcTF = new JTextField();
		nombreTF = new JTextField();
		apellidosTF = new JTextField();
		calleTF = new JTextField();
		coloniaTF = new JTextField();
		cpTF = new JTextField();
		ciudadTF = new JTextField();
		estadoTF = new JTextField();
		emailTF = new JTextField();
		celTF = new JTextField();
		telEmpTF = new JTextField();
		guardar = new JButton("Guardar");
		panelN = new JPanel();
		panelC = new JPanel();
		panelS = new JPanel();
		guardar.setEnabled(false);
		
		setLayout(new BorderLayout());
		add(panelN, BorderLayout.NORTH);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titulo.setForeground(Color.RED);
		panelN.add(titulo);
		
		add(panelC, BorderLayout.CENTER);
		panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panelC.add(personas, gbc);
		personas.addItemListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelC.add(nombreLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(nombreTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(apellidosLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(apellidosTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(rfcLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(rfcTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(emailLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(emailTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(calleLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(calleTF, gbc);

		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(coloniaLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(coloniaTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(estadoLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(estadoTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(ciudadLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(ciudadTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(cpLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(cpTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(telEmpLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(telEmpTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(celLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(celTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(new JLabel(" "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(new JLabel(" "), gbc);
		
		add(panelS, BorderLayout.SOUTH);
		panelS.add(guardar);
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() == personas) {
			if (personas.getSelectedIndex() == 0) {
				rfcTF.setText("");
				nombreTF.setText("");
				apellidosTF.setText("");
				calleTF.setText("");
				coloniaTF.setText("");
				cpTF.setText("");
				ciudadTF.setText("");
				estadoTF.setText("");
				emailTF.setText("");
				celTF.setText("");
				telEmpTF.setText("");
				guardar.setEnabled(false);
			} else {
				String[] val;
				val = personas.getSelectedItem().toString().split(":");
				Cliente c = ClienteBD.ClienteSeleccionado(Integer.parseInt(val[0]));
				rfcTF.setText(c.getRFC());
				nombreTF.setText(c.getNombre());
				apellidosTF.setText(c.getApellidos());
				calleTF.setText(c.getCalle());
				coloniaTF.setText(c.getColonia());
				cpTF.setText(c.getCP());
				ciudadTF.setText(c.getCiudad());
				estadoTF.setText(c.getEstado());
				emailTF.setText(c.getEmail());
				celTF.setText(c.getTelefono());
				telEmpTF.setText(c.getTelEmp());
				guardar.setEnabled(true);
			}
		}
	}
}