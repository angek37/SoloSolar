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
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;
import com.SoloSolar.interfaces.AdministrarCategorias.CategoryModel;
import com.SoloSolar.interfaces.AdministrarCategorias.ProductModel;


public class AdministrarProveedor extends JPanel implements MouseListener {
	private JTextField id, nombre, calle, numero, colonia, cp, ciudad, estado, celular, telefono, correo;
	private JTable table;
	private JLabel prov;
	
	public AdministrarProveedor() {
		setLayout(new BorderLayout());
		table = new JTable(new SupplierModel());
		table.setFillsViewportHeight(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(150, 0));
		add(scrollPane, BorderLayout.WEST);
		add(new ModifySupplier(), BorderLayout.CENTER);
		
		JLabel titulo = new JLabel("Administrar proveedores");
		titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
		titulo.setForeground(Color.BLUE);
		add(titulo, BorderLayout.NORTH);
	}
	
	public class ModifySupplier extends JPanel implements ActionListener{
		private JButton actualizar, eliminar;
		private JPanel updateP, deleteP;
		
		public ModifySupplier() {
			setLayout(new BorderLayout());
			updateP = new JPanel();
			deleteP = new JPanel();
			
			updateP.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			updateP.setBorder(new CompoundBorder(new TitledBorder("Modificar valores de Proveedor"), new EmptyBorder(12, 0, 0, 0)));
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(4,4,4,4);
			updateP.add(new JLabel("ID:"), gbc);
			id = new JTextField();
			id.setEnabled(false);
			gbc.gridx++;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.ipadx = 200;
			updateP.add(id, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.gridy++;
			gbc.gridwidth = 1;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Nombre:"), gbc);
			nombre = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(nombre, gbc);
			gbc.gridx++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Calle:"), gbc);
			calle = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(calle, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Número:"), gbc);
			numero = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(numero, gbc);
			gbc.gridx++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Colonia:"), gbc);
			colonia = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(colonia, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("C.P.:"), gbc);
			cp = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(cp, gbc);
			gbc.gridx++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Ciudad:"), gbc);
			ciudad = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(ciudad, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Estado:"), gbc);
			estado = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(estado, gbc);
			gbc.gridx++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Email:"), gbc);
			correo = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(correo, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Cel.:"), gbc);
			celular = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(celular, gbc);
			gbc.gridx++;
			gbc.ipadx = 0;
			updateP.add(new JLabel("Tel.:"), gbc);
			telefono = new JTextField();
			gbc.gridx++;
			gbc.ipadx = 200;
			updateP.add(telefono, gbc);
			actualizar = new JButton("Actualizar");
			gbc.gridy++;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.ipadx = 130;
			updateP.add(actualizar, gbc);
			actualizar.addActionListener(this);
			
			deleteP.setLayout(new GridBagLayout());
			GridBagConstraints gbc2 = new GridBagConstraints();
			deleteP.setBorder(new CompoundBorder(new TitledBorder("Eliminar Proveedor"), new EmptyBorder(12, 12, 12, 12)));
			gbc2.gridy = 0;
			gbc2.gridx = 0;
			gbc2.gridwidth = 1;
			gbc2.insets = new Insets(0, 0, 0, 30);
			gbc2.anchor = GridBagConstraints.WEST;
			deleteP.add(new JLabel("Eliminar:"), gbc2);
			prov = new JLabel("Elija un proveedor");
			prov.setFont(new Font("Calibri", Font.ITALIC, 12));
			prov.setForeground(Color.RED);
			gbc2.gridx++;
			gbc2.anchor = GridBagConstraints.WEST;
			gbc2.gridwidth = GridBagConstraints.REMAINDER;
			gbc2.fill = GridBagConstraints.HORIZONTAL;
			gbc2.weightx = 1;
			gbc2.insets = new Insets(20, 0, 20, 0);
			deleteP.add(prov, gbc2);
			eliminar = new JButton("Eliminar");
			gbc2.gridwidth = 1;
			gbc2.gridx++;
			gbc2.insets = new Insets(0, 0, 0, 0);
			gbc2.gridy++;
			deleteP.add(eliminar, gbc2);
			eliminar.addActionListener(this);	
			add(updateP, BorderLayout.CENTER);
			add(deleteP, BorderLayout.SOUTH);
		}
		
		public void actionPerformed(ActionEvent e) {
			Validaciones v = new Validaciones();
			Insert i = new Insert();
			Proveedor p;
			try {
				if(e.getSource() == actualizar) {
					p = new Proveedor(Integer.parseInt(id.getText()), nombre.getText(), 
							calle.getText(), numero.getText(), colonia.getText(), 
								ciudad.getText(), estado.getText(), correo.getText(), celular.getText(), telefono.getText(), cp.getText());
					if(v.validarProveedores(p)) {
						if(i.UpdateSupplier(p)) {
							JOptionPane.showMessageDialog(null, "El proveedor ha sido actualizado", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
							table.setModel(new SupplierModel());
							id.setText("");
							nombre.setText("");
							calle.setText("");
							numero.setText("");
							colonia.setText("");
							cp.setText("");
							ciudad.setText("");
							estado.setText("");
							celular.setText("");
							telefono.setText("");
							correo.setText("");
							prov.setText("Elija un proveedor");
						}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible actualizar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}else if (e.getSource() == eliminar) {
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro de borrar el proveedor '"+ table.getModel().getValueAt(table.getSelectedRow(), 0) +"'?", "Borrar Proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(reply == JOptionPane.YES_OPTION) {
						if(i.DeleteProdProv(Integer.parseInt(id.getText()))) {
							if(i.DeleteSupplier(Integer.parseInt(id.getText()))) {
								JOptionPane.showMessageDialog(null, "El proveedor se ha eliminado correctamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
								table.setModel(new SupplierModel());
								id.setText("");
								nombre.setText("");
								calle.setText("");
								numero.setText("");
								colonia.setText("");
								cp.setText("");
								ciudad.setText("");
								estado.setText("");
								celular.setText("");
								telefono.setText("");
								correo.setText("");
								prov.setText("Elija un proveedor");
							}else {
								JOptionPane.showMessageDialog(null, "No ha sido posible eliminar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
							}
	 					}else {
							JOptionPane.showMessageDialog(null, "No ha sido posible eliminar las dependencias del proveedor", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}catch(NumberFormatException | ArrayIndexOutOfBoundsException exp) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado un proveedor", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getClickCount() == 1) {
				id.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 1));
				nombre.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 0));
				calle.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 2));
				numero.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 3));
				colonia.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 4));
				cp.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 5));
				ciudad.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 6));
				estado.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 7));
				celular.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 9));
				telefono.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 10));
				correo.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 8));
				prov.setText(""+table.getModel().getValueAt(table.getSelectedRow(), 0));
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
	
	public class SupplierModel extends AbstractTableModel {
		Consulta select = new Consulta();
		Proveedor[] prov = select.selectSupplier();
		public int getRowCount() {
			return prov.length;
		}

		public int getColumnCount() {
			return 1;
		}
		
		public String getColumnName(int col) {
			return "Nombre";
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			   Object value = null;
			   Proveedor proveedor = prov[rowIndex];
			   switch (columnIndex) {
			   case 0:
				   value = proveedor.getNombre();
				   break;
			   case 1:
				   value = proveedor.getId();
				   break;
			   case 2:
				   value = proveedor.getCalle();
				   break;
			   case 3:
				   value = proveedor.getNumero();
				   break;
			   case 4:
				   value = proveedor.getColonia();
				   break;
			   case 5:
				   value = proveedor.getCp();
				   break;
			   case 6:
				   value = proveedor.getCiudad();
				   break;
			   case 7:
				   value = proveedor.getEstado();
				   break;
			   case 8:
				   value = proveedor.getEmail();
				   break;
			   case 9:
				   value = proveedor.getTelefono();
				   break;
			   case 10:
				   value = proveedor.getCelular();
				   break;
			   }
	           return value;
		}
	}
}
