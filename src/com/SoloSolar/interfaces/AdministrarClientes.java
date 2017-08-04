package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.DB.ClienteBD;

public class AdministrarClientes extends JPanel implements MouseListener {
	private JTable table;
	private JLabel titulo, idLbl, rfcLbl, nombreLbl, apellidosLbl, calleLbl, coloniaLbl, cpLbl, 
			ciudadLbl, estadoLbl, emailLbl, celLbl, telEmpLbl;
	private JTextField idTF, rfcTF, nombreTF, apellidosTF, calleTF, coloniaTF, cpTF, 
			ciudadTF, estadoTF, emailTF, celTF, telEmpTF;
	private JLabel clientes;
	ClienteBD select  = new ClienteBD();
	Cliente[] client = select.selectClientes();
	private int ID = 0;
	
	public AdministrarClientes() {
		setLayout(new BorderLayout());
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
		table.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(150, 0));
		add(scrollPane, BorderLayout.WEST);
		add(new ModificarClientes(), BorderLayout.CENTER);
		
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo, BorderLayout.NORTH);
	}
	
	public class ClientModel extends AbstractTableModel {
		ClienteBD select  = new ClienteBD();
		Cliente[] client = select.selectClientes();
		
		public int getRowCount() {
			return client.length;
		}

		public int getColumnCount() {
			return 1;
		}
		
		public String getColumnName(int col) {
			return "Nombre";
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			   Cliente cliente = client[rowIndex];
			   switch (columnIndex) {
				   case 0:
					   value = cliente.getNombre();
					   break;
				   case 1:
					   value = cliente.getId();
					   break;
				   case 2:
					   value = cliente.getApellidos();
					   break;
				   case 3:
					   value = cliente.getRFC();
					   break;
				   case 4:
					   value = cliente.getEmail();
					   break;
				   case 5:
					   value = cliente.getCalle();
					   break;
				   case 6:
					   value = cliente.getColonia();
					   break;
				   case 7:
					   value = cliente.getEstado();
					   break;
				   case 8:
					   value = cliente.getCiudad();
					   break;
				   case 9:
					   value = cliente.getCP();
					   break;
				   case 10:
					   value = cliente.getTelEmp();
					   break;
				   case 11:
					   value = cliente.getTelefono();
					   break;
			   }
	           return value;
		}
		
	}
	
	public class ModificarClientes extends JPanel implements ActionListener{
		private JButton actualizar, eliminar;
		private JPanel updateP, deleteP;
		
		public ModificarClientes() {
			setLayout(new BorderLayout());
			updateP = new JPanel();
			deleteP = new JPanel();
			titulo = new JLabel("Administrar clientes");
			idLbl = new JLabel("ID: ");
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
			actualizar = new JButton("Actualizar");
			idTF = new JTextField();
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
			idTF.setEditable(false);
				
			updateP.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			updateP.setBorder(new CompoundBorder(new TitledBorder("Modificar valores de cliente"), new EmptyBorder(12, 0, 0, 0)));
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			updateP.add(idLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(idTF, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(nombreLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(nombreTF, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(apellidosLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(apellidosTF, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(rfcLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(rfcTF, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(emailLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(emailTF, gbc);

			gbc.gridx++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(calleLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(calleTF, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(coloniaLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(coloniaTF, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(estadoLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(estadoTF, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(ciudadLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(ciudadTF, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(cpLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(cpTF, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(telEmpLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(telEmpTF, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			updateP.add(celLbl, gbc);
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			updateP.add(celTF, gbc);
			
			gbc.gridy++;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			updateP.add(actualizar, gbc);
			actualizar.addActionListener(this);
			
			deleteP.setLayout(new GridBagLayout());
			GridBagConstraints gbc2 = new GridBagConstraints();
			deleteP.setBorder(new CompoundBorder(new TitledBorder("Eliminar cliente"), new EmptyBorder(12, 12, 12, 12)));
			//prod = new JLabel("Productos dentro de esta categoría: ");
			gbc2.gridx = 0;
			gbc2.gridy = 0;
			gbc2.anchor = GridBagConstraints.WEST;
			gbc2.gridwidth = 2;
			gbc2.fill = GridBagConstraints.HORIZONTAL;
			//deleteP.add(prod, gbc2);
			gbc2.gridwidth = 1;
			gbc2.gridy++;
			gbc2.weightx = 1;
			gbc2.anchor = GridBagConstraints.WEST;
			gbc2.insets = new Insets(20, 0, 20, 0);
			deleteP.add(new JLabel("Eliminar cliente: "), gbc2);
			
			clientes = new JLabel("Elija un cliente");
			clientes.setFont(new Font("Calibri", Font.ITALIC, 12));
			clientes.setForeground(Color.RED);
			gbc2.gridx++;
			deleteP.add(clientes, gbc2);
			
			eliminar = new JButton("Eliminar");
			gbc2.insets = new Insets(0, 0, 0, 0);
			gbc2.gridy++;
			deleteP.add(eliminar, gbc2);
			eliminar.addActionListener(this);
			
			add(updateP, BorderLayout.CENTER);
			add(deleteP, BorderLayout.SOUTH);
		}
		

		public void actionPerformed(ActionEvent e) {
			ClienteBD in = new ClienteBD();
			Cliente cl;
			try {
				if(e.getSource() == actualizar) {
					cl = new Cliente(Integer.parseInt(idTF.getText()), rfcTF.getText(), nombreTF.getText(), 
							apellidosTF.getText(), calleTF.getText(), coloniaTF.getText(), cpTF.getText(),
						ciudadTF.getText(), estadoTF.getText(), emailTF.getText(), celTF.getText(), telEmpTF.getText());
					if(in.UpdateClient(cl)) {
						JOptionPane.showMessageDialog(null, "Cliente modificado exitosamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "No ha sido posible modificar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
					}
					idTF.setText("");
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
					clientes.setText("Elija un cliente");
					table.setModel(new ClientModel());
				} else if (e.getSource() == eliminar) {
					if(ID != 0) {
						int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el cliente '" + clientes.getText() + "'?", "Cerrar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(reply == JOptionPane.YES_OPTION) {
							if(in.DeleteClient(ID)) {
								JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
								table.setModel(new ClientModel());
								clientes.setText("Elija un cliente");
								ID = 0;
							}else {
								JOptionPane.showMessageDialog(null, "No ha sido posible eliminar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado un cliente", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException exp) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado un cliente", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getClickCount() == 1) {
				nombreTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 0));
				idTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 1));
				apellidosTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 2));
				rfcTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 3));
				emailTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 4));
				calleTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 5));
				coloniaTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 6));
				estadoTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 7));
				ciudadTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 8));
				cpTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 9));
				telEmpTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 10));
				celTF.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 11));
				clientes.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 0));
				ID = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
			}
		}catch(ArrayIndexOutOfBoundsException expt) {
			
		}
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
}
