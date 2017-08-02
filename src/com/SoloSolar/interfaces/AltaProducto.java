package com.SoloSolar.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.Capsulas.TextPrompt;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;

public class AltaProducto extends JPanel{
	private JLabel titulo;
	private JTextField clave, nombre, paquete, costo, precio1, precio2, por1, por2;
	private JComboBox<Categoria> categories;
	private JComboBox<Proveedor> suppliers;
	Consulta c = new Consulta();
	Categoria[] category = (Categoria[]) c.selectCategories();
	Proveedor[] supplier = (Proveedor[]) c.selectSupplier();
	private JButton registrar;
	private JTable table;
	private String[][] datos = {{"", ""}};
	private String[] head = {"ID", "Proveedor"};
	private int cont = 1;
	
	public AltaProducto() { 
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(new TituloPanel(), gbc);
		gbc.gridwidth = 1;
		gbc.gridy++;
		add(new DatosPanel(), gbc);
		gbc.gridx++;
		gbc.insets = new Insets(0, 30, 0, 0);
		add(new SuppliersPanel(), gbc);
	}
	
	public class TituloPanel extends JPanel {
		public TituloPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			titulo = new JLabel("Registrar nuevo producto");
			titulo.setFont(new Font("Calibri", Font.ITALIC, 16));
			titulo.setForeground(Color.BLUE);
			gbc.insets = new Insets(0, 4, 20, 0);
			gbc.ipady = 5;
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
		}
	}
	
	public class DatosPanel extends JPanel implements KeyListener{
		public DatosPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 4, 20, 0);
			gbc.ipady = 5;
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(new JLabel("Categoría:"), gbc);
			categories = new JComboBox<Categoria>(category);
			categories.setMaximumSize(new Dimension(200, 20));
			gbc.gridx++;
			gbc.ipadx = 50;
			add(categories, gbc);
			gbc.ipadx = 0;
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Paquete:"), gbc);
			paquete = new JTextField();
			gbc.ipadx = 160;
			gbc.gridx++;
			add(paquete, gbc);
			gbc.ipadx = 0;
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Costo:"), gbc);
			costo = new JTextField();
			gbc.ipadx = 160;
			gbc.gridx++;
			add(costo, gbc);
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
			add(por1, gbc);
			por1.addKeyListener(this);
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
	
	public class SuppliersPanel extends JPanel implements ActionListener{
		JButton addB;
		private ImageIcon addico = new ImageIcon(
				new ImageIcon("assets/plus.png").getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		
		public SuppliersPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 4, 10, 0);
			gbc.ipady = 5;
			setBorder(new CompoundBorder(new TitledBorder("Agregar Proveedores"), new EmptyBorder(12, 0, 0, 0)));
			suppliers = new JComboBox<Proveedor>(supplier);
			suppliers.setMaximumSize(new Dimension(200, 20));
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.ipadx = 50;
			add(suppliers, gbc);
			addB = new JButton(addico);
			addB.setBorder(null);
			addB.setBackground(null);
			gbc.gridx++;
			add(addB, gbc);
			addB.addActionListener(this);
			table = new JTable(datos, head);
			table.setFillsViewportHeight(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.getTableHeader().setReorderingAllowed(false);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(150, 0));
			gbc.ipady = 120;
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridheight = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.BOTH;
			add(scrollPane, gbc);
			printArray();
		}
		
		public void printArray() {
			for(int x = 0; x < datos.length; x++) {
				for(int y = 0; y < datos[x].length; y++) {
					System.out.print(datos[x][y]+" | ");
				}
				System.out.print("\n");
			}
			System.out.print("______________________________\n");
		}

		public void actionPerformed(ActionEvent e) {
			DefaultTableModel dtm;
			if(e.getSource() == addB) {
				Proveedor prov = (Proveedor) suppliers.getSelectedItem();
				if(!datos[0][0].equals("")) {
					String aux[][] = new String[datos.length][2];
					aux = datos;
//					for(int x = 0; x < datos.length; x++) {
//						aux[x][0] = datos[x][0];
//						aux[x][1] = datos[x][1];
//					}
					cont++;
					datos = new String[cont][2];
					for(int x = 0; x < aux.length; x++) {
						datos[x][0] = aux[x][0];
						datos[x][1] = aux[x][1];
					}
					datos[cont-1][0] = Integer.toString(prov.getId());
					datos[cont-1][1] = prov.getNombre();
					dtm = new DefaultTableModel(datos, head);
					table.setModel(dtm);
					//table.repaint();
					//table = new JTable(datos, head);
				}else {
					datos[0][0] = Integer.toString(prov.getId());
					datos[0][1] = prov.getNombre();
					dtm = new DefaultTableModel(datos, head);
					table.setModel(dtm);
					//table.repaint();
					//table = new JTable(datos, head);
				}
				printArray();
			}
		}
	}
	
}
