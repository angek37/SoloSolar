package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Venta extends JPanel {
	JTextField total;
	String[] head = {"Clave", "Nombre de Producto", "Cantidad", "Pack", "L", "Precio", "SubTotal"};
	String[][] renglones = new String[7][10];
	
	public Venta() {
		setLayout(new BorderLayout());
		add(new DatosP(), BorderLayout.NORTH);
		add(new TablaP(), BorderLayout.CENTER);
		add(new BotonesP(), BorderLayout.SOUTH);
	}
	
	public class DatosP extends JPanel implements ActionListener{
		JTextField pedido, idCliente, nombreCliente, observaciones;
		JButton buscarCliente;
		private ImageIcon customerIco = new ImageIcon(
				new ImageIcon("assets/searchCustomer.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		private JDateChooser datePicker;
		
		public DatosP() {
			setLayout(new GridBagLayout());
			setBorder(new CompoundBorder(new TitledBorder("Datos de Venta"), new EmptyBorder(0, 0, 0, 0)));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(3, 10, 3, 10);
			gbc.weightx = 1;
			gbc.anchor = GridBagConstraints.WEST;
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
			gbc.ipadx = 20;
			add(new JLabel("Cliente:"), gbc);
			buscarCliente = new JButton(customerIco);
			buscarCliente.setBorder(null);
			buscarCliente.setBackground(null);
			buscarCliente.setFocusable(false);
			buscarCliente.setToolTipText("Buscar un Cliente");
			gbc.ipadx = 30;
			gbc.gridx++;
			add(buscarCliente, gbc);
			buscarCliente.addActionListener(this);
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
			datePicker = new JDateChooser(new Date());
			gbc.ipadx = 90;
			gbc.gridx++;
			add(datePicker, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.ipadx = 20;
			add(new JLabel("Observaciones:"), gbc);
			observaciones = new JTextField();
			gbc.gridx++;
			gbc.gridwidth = 4;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(observaciones, gbc);
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buscarCliente) {
				new BuscarCliente(idCliente, nombreCliente);
			}
			
		}
		
	}
	
	public class TablaP extends JPanel {
		JTable table;
		
		public TablaP() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 2, 0 ,2);
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.BOTH;
			table = new JTable(renglones ,head);
			table.setFillsViewportHeight(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, gbc);
		}
	}
	
	public class BotonesP extends JPanel {
		JCheckBox iva;
		JButton nuevo, guardar, exportar;
		
		public BotonesP() {
			setLayout(new BorderLayout());
			add(new TotalPanel(), BorderLayout.NORTH);
			add(new OpcionesPanel(), BorderLayout.SOUTH);
		}
		
		public class TotalPanel extends JPanel {
			public TotalPanel() {
				setLayout(new FlowLayout(FlowLayout.RIGHT));
				iva = new JCheckBox("IVA");
				add(iva);
				add(new JLabel("Total:"));
				total = new JTextField();
				total.setEnabled(false);
				total.setPreferredSize(new Dimension(100, 30));
				add(total);
			}
		}
		
		public class OpcionesPanel extends JPanel implements ActionListener {
			private ImageIcon newD = new ImageIcon(
					new ImageIcon("assets/newDocument.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
			private ImageIcon save = new ImageIcon(
					new ImageIcon("assets/Save.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
			private ImageIcon pdf = new ImageIcon(
					new ImageIcon("assets/pdf.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
			
			public OpcionesPanel() {
				setLayout(new FlowLayout(FlowLayout.CENTER));
				setBorder(new CompoundBorder(new TitledBorder(""), new EmptyBorder(0, 0, 0, 0)));
				nuevo = new JButton("Nuevo", newD);
				nuevo.addActionListener(this);
				add(nuevo);
				exportar = new JButton("Exportar", pdf);
				exportar.addActionListener(this);
				add(exportar);
				guardar = new JButton("Guardar", save);
				guardar.addActionListener(this);
				add(guardar);
			}
			
			public void actionPerformed(ActionEvent e) {
				
			}
		}
	}
}
