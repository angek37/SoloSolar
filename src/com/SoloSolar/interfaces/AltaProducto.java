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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import javax.swing.table.DefaultTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.Capsulas.Proveedor;
import com.SoloSolar.Capsulas.TextPrompt;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.DB.Insert;

public class AltaProducto extends JPanel implements ActionListener{
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
		gbc.insets = new Insets(0, 50, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
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
		registrar = new JButton("Registrar Producto");
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.ipadx = 40;
		gbc.ipady = 10;
		add(registrar, gbc);
		registrar.addActionListener(this);
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
			gbc.insets = new Insets(0, 4, 20, 0);
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
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.ipadx = 0;
			add(new JLabel("Categoría:"), gbc);
			categories = new JComboBox<Categoria>(category);
			categories.setMaximumSize(new Dimension(200, 20));
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx++;
			gbc.ipadx = 50;
			add(categories, gbc);
		}
	}
	
	public class DatosPanel extends JPanel implements KeyListener{
		public DatosPanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 4, 20, 5);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipady = 5;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridy++;
			add(new JLabel("Paquete:"), gbc);
			paquete = new JTextField();
			gbc.anchor = GridBagConstraints.EAST;
			gbc.ipadx = 160;
			gbc.gridx++;
			add(paquete, gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx = 0;
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Costo:"), gbc);
			costo = new JTextField();
			gbc.anchor = GridBagConstraints.EAST;
			gbc.ipadx = 160;
			gbc.gridx++;
			add(costo, gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx = 0;
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Precio 1:"), gbc);
			precio1 = new JTextField();
			gbc.anchor = GridBagConstraints.EAST;
			gbc.ipadx = 160;
			gbc.gridx++;
			add(precio1, gbc);
			por1 = new JTextField();
			TextPrompt percent = new TextPrompt("%", por1, TextPrompt.Show.FOCUS_LOST);
			percent.changeAlpha(0.75f);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx++;
			gbc.ipadx = 30;
			add(por1, gbc);
			por1.addKeyListener(this);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx = 0;
			gbc.gridx = 0;
			gbc.gridy++;
			add(new JLabel("Precio 2:"), gbc);
			precio2 = new JTextField();
			gbc.anchor = GridBagConstraints.EAST;
			gbc.ipadx = 160;
			gbc.gridx++;
			add(precio2, gbc);
			por2 = new JTextField();
			TextPrompt percent2 = new TextPrompt("%", por2, TextPrompt.Show.FOCUS_LOST);
			percent2.changeAlpha(0.75f);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx++;
			gbc.ipadx = 30;
			add(por2, gbc);
			por2.addKeyListener(this);
		}
		
		public double round(double value, int places) {
		    if (places < 0) throw new IllegalArgumentException();

		    long factor = (long) Math.pow(10, places);
		    value = value * factor;
		    long tmp = Math.round(value);
		    return (double) tmp / factor;
		}

		public void keyTyped(KeyEvent e) {
			
		}

		public void keyPressed(KeyEvent e) {
			
		}

		public void keyReleased(KeyEvent e) {
			double r;
			if(e.getSource() == por1 && !costo.getText().isEmpty()) {
				try {
					r = round(Double.parseDouble(costo.getText())*((Double.parseDouble(por1.getText())/100)+1),1);
					precio1.setText(Double.toString(r));
				}catch(NumberFormatException ex) {
					por1.setText("");
				}
			}else if(e.getSource() == por2 && !costo.getText().isEmpty()) {
				try {
					r = round(Double.parseDouble(costo.getText())*((Double.parseDouble(por2.getText())/100)+1),1);
					precio2.setText(Double.toString(r));
				}catch(NumberFormatException ex) {
					por2.setText("");
				}
			}
		}
	}
	
	public class SuppliersPanel extends JPanel implements ActionListener, MouseListener{
		JButton addB;
		private ImageIcon addico = new ImageIcon(
				new ImageIcon("assets/plusR.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
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
			table = new JTable(datos, head) {
		        public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
		    };
			table.setFillsViewportHeight(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.getTableHeader().setReorderingAllowed(false);
			table.setToolTipText("Doble click para eliminar proveedor");
			table.addMouseListener(this);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(150, 0));
			gbc.ipady = 120;
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridheight = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.BOTH;
			add(scrollPane, gbc);
		}
		
		public boolean isRepeat(int id) {
			boolean reply = false;
			for(int x = 0; x < datos.length; x++) {
				if(datos[x][0].equals(Integer.toString(id))) {
					reply = true;
				}
			}
			return reply;
		}
		
		public void deleteSupplier(int r) {
			datos[r][0] = "";
			datos[r][1] = "";
			String aux[][] = new String[datos.length-1][2];
			int c = 0;
			for(int x = 0; x < datos.length; x++) {
				if(!datos[x][0].equals("")) {
					aux[c][0] = datos[x][0];
					aux[c][1] = datos[x][1];
					c++;
				}
			}
			datos = new String[aux.length][2];
			datos = aux;
			aux = null;
		}

		public void actionPerformed(ActionEvent e) {
			DefaultTableModel dtm;
			if(e.getSource() == addB) {
				Proveedor prov = (Proveedor) suppliers.getSelectedItem();
				if(!isRepeat(prov.getId())) {
					try {
						if(!datos[0][0].equals("")) {
							String aux[][] = new String[datos.length][2];
							aux = datos;
							cont++;
							datos = new String[cont][2];
							for(int x = 0; x < aux.length; x++) {
								datos[x][0] = aux[x][0];
								datos[x][1] = aux[x][1];
							}
							aux = null;
							datos[cont-1][0] = Integer.toString(prov.getId());
							datos[cont-1][1] = prov.getNombre();
							dtm = new DefaultTableModel(datos, head);
							table.setModel(dtm);
						}else {
							datos[0][0] = Integer.toString(prov.getId());
							datos[0][1] = prov.getNombre();
							dtm = new DefaultTableModel(datos, head);
							table.setModel(dtm);
						}
					}catch(ArrayIndexOutOfBoundsException ex) {
						cont = 1;
						datos = new String[1][2];
						datos[0][0] = "";
						datos[0][1] = "";
						datos[0][0] = Integer.toString(prov.getId());
						datos[0][1] = prov.getNombre();
						dtm = new DefaultTableModel(datos, head);
						table.setModel(dtm);
					}
				}
			}
		}

		public void mouseClicked(MouseEvent e) {
			DefaultTableModel dtm;
			try {
				if (e.getClickCount() == 2) {
					deleteSupplier(table.getSelectedRow());
					dtm = new DefaultTableModel(datos, head);
					table.setModel(dtm);
				}
			}catch(ArrayIndexOutOfBoundsException | NullPointerException expt) {
				
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
	
	public boolean isEmpty() {
		boolean r = false;
		for(int x = 0; x < datos.length; x++) {
			for(int y = 0; y < datos[x].length; y++) {
				if(datos[x][y] != null) {
					if(datos[x][y].equals("")) {
						r = true;
					}
				}else {
					r = true;
				}
			}
		}
		return r;
	}

	public void actionPerformed(ActionEvent e) {
		Validaciones v = new Validaciones();
		Insert i = new Insert();
		Categoria c;
		Producto p;
		if(e.getSource() == registrar) {
			c = (Categoria) categories.getSelectedItem();
			p = new Producto(clave.getText(), nombre.getText(), c.getId(), 
					Integer.parseInt(paquete.getText()), Double.parseDouble(costo.getText()), 
					Double.parseDouble(precio1.getText()), Double.parseDouble(precio2.getText()));
			if(v.validarProducto(p)) {
				if(i.InsertProduct(p)){
					if(!datos[0][0].equals("")) {
						if(!isEmpty()) {
							JOptionPane.showMessageDialog(null, "Producto registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
							clave.setText("");
							nombre.setText("");
							paquete.setText("");
							costo.setText("");
							precio1.setText("");
							precio2.setText("");
							por1.setText("");
							por2.setText("");
							datos = null;
							datos = new String[1][2];
							datos[0][0] = "";
							datos[0][1] = "";
							table.setModel(new DefaultTableModel(datos, head));
						}
					}else {
						JOptionPane.showMessageDialog(null, "Producto registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
						clave.setText("");
						nombre.setText("");
						paquete.setText("");
						costo.setText("");
						precio1.setText("");
						precio2.setText("");
						por1.setText("");
						por2.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null, "No ha sido posible registrar el producto", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
