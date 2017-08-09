package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.DB.ClienteBD;

public class AltaCliente extends JPanel implements ActionListener {
	private JLabel titulo;
	private JLabel rfcLbl, nombreLbl, apellidosLbl, calleLbl, coloniaLbl, cpLbl, 
					ciudadLbl, estadoLbl, emailLbl, celLbl, telEmpLbl, noDirLbl;
	private JTextField rfcTF, nombreTF, apellidosTF, calleTF, coloniaTF, cpTF, 
					ciudadTF, estadoTF, emailTF, celTF, telEmpTF, noDirTF;
	private JPanel panelN, panelC, panelS;
	private JTable table;
	private JButton guardar;
		
	public AltaCliente () {
		titulo = new JLabel("Alta de cliente");
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
		noDirLbl = new JLabel("No. Dirección: ");
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
		noDirTF = new JTextField();
		guardar = new JButton("Guardar");
		panelN = new JPanel();
		panelC = new JPanel();
		panelS = new JPanel();
		
		setLayout(new BorderLayout());
		add(panelN, BorderLayout.NORTH);
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		panelN.add(titulo);
		
		add(panelC, BorderLayout.CENTER);
		panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
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
		panelC.add(noDirLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(noDirTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(estadoLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(estadoTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(ciudadLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(ciudadTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(cpLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(cpTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(telEmpLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(telEmpTF, gbc);
		
		gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		panelC.add(celLbl, gbc);
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		panelC.add(celTF, gbc);
		
		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		panelC.add(guardar, gbc);
		guardar.addActionListener(this);
		
		table = new JTable(new ClientModel());
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				if (row > -1) {
					table.clearSelection();
					table.setRowSelectionInterval(row, row);
				} else {
					table.clearSelection();
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 4;
		panelC.add(scrollPane, gbc);
		
	}

	public void actionPerformed(ActionEvent e) {
		Validaciones v = new Validaciones();
		Cliente c = new Cliente();
		if(e.getSource() == guardar) {
			c.setNombre(nombreTF.getText());
			c.setApellidos(apellidosTF.getText());
			c.setRFC(rfcTF.getText());
			c.setEmail(emailTF.getText());
			c.setCalle(calleTF.getText());
			c.setColonia(coloniaTF.getText());
			c.setEstado(estadoTF.getText());
			c.setCiudad(ciudadTF.getText());
			c.setCP(cpTF.getText());
			c.setTelefono(celTF.getText());
			c.setTelEmp(telEmpTF.getText());
			c.setNoDir(noDirTF.getText());
			if(v.validarCliente(c)) {
				ClienteBD.InsertCliente(c);
				nombreTF.setText("");
				apellidosTF.setText("");
				rfcTF.setText("");
				emailTF.setText("");
				calleTF.setText("");
				coloniaTF.setText("");
				estadoTF.setText("");
				ciudadTF.setText("");
				cpTF.setText("");
				celTF.setText("");
				telEmpTF.setText("");
				noDirTF.setText("");
				table.setModel(new ClientModel());
			}
		}
	}
	
	public class ClientModel extends AbstractTableModel {
		ClienteBD select = new ClienteBD();
		Cliente[] client = select.selectClientes();
		public int getRowCount() {
			return client.length;
		}

		public int getColumnCount() {
			return 4;
		}
		
		public String getColumnName(int col) {
			String aux = "";
		      switch(col) {
		      case 0: 
		    	  return aux = "Nombre";
		      case 1: 
		    	  return aux = "Apellido";
		      case 2:
		    	  return aux = "RFC";
		      case 3:
		    	  return aux = "Email";
		    	  
		      }
			return aux;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			   Object value = null;
			   Cliente cliente = client[rowIndex];
			   switch (columnIndex) {
			   case 0:
				   value = cliente.getNombre();
				   break;
			   case 1:
				   value = cliente.getApellidos();
				   break;
			   case 2:
				   value = cliente.getRFC();
				   break;
			   case 3:
				   value = cliente.getEmail();
				   break;
			   }
	           return value;
		}
	}

}