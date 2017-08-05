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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Venta extends JPanel {
	JLabel total;
	Double totalC;
	JTable table;
	String[] head = {"Clave", "Nombre de Producto", "Cantidad", "Pack", "L", "Precio", "SubTotal"};
	String[][] renglones = new String[12][7];
	
	public Venta() {
		setLayout(new BorderLayout());
		add(new DatosP(), BorderLayout.NORTH);
		add(new TablaP(), BorderLayout.CENTER);
		add(new BotonesP(), BorderLayout.SOUTH);
	}
	
	public class DatosP extends JPanel implements ActionListener {
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
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public class TablaP extends JPanel {
		
		public TablaP() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 2, 0 ,2);
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.BOTH;
			DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
		    singleclick.setClickCountToStart(1);
			table = new JTable(renglones ,head){
		        private static final long serialVersionUID = 1L;

		        public boolean isCellEditable(int row, int column) {
		        	boolean r = true;
		        	switch(column) {
		        	case 0: r = false;
		        	break;
		        	case 1: r = false;
		        	break;
		        	case 3: r = false;
		        	break;
		        	case 4: r = false;
		        	break;
		        	case 5: r = false;
		        	break;
		        	case 6: r = false;
		        	break;
		        	}
		        	return r;
		        };
			};
			table.getModel().addTableModelListener(new TableModelListener() {
				
				public void Total() {
					double sum = 0;
					for(int x = 0; x < renglones.length; x++) {
						if(renglones[x][6] != null) {
							sum += Double.parseDouble(renglones[x][6]);
						}
					}
					totalC = round(sum, 1);
					total.setText(Double.toString(round(sum, 1)));
				}
				
				public void tableChanged(TableModelEvent e) {
					double r;
					if(e.getType() == TableModelEvent.UPDATE && e.getColumn() == 2) {
						try {
							if(!renglones[e.getFirstRow()][2].equalsIgnoreCase("")) {
								r = Integer.parseInt(renglones[e.getFirstRow()][2]) * Double.parseDouble(renglones[e.getFirstRow()][5]);
								renglones[e.getFirstRow()][6] = Double.toString(round(r, 1));
								Total();
							}
						}catch(NumberFormatException | NullPointerException exp) {
							renglones[e.getFirstRow()][2] = null;
							JOptionPane.showMessageDialog(null, "Error con el campo 'Cantidad'", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			table.setFillsViewportHeight(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.setToolTipText("Doble clic para buscar un producto");
			table.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2 && !e.isConsumed()) {
						e.consume();
						new BuscarProducto(renglones, table, table.getSelectedRow());
					}
				}
			});
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, gbc);
			//Ancho de columnas
			table.getColumnModel().getColumn(0).setPreferredWidth(27);
			table.getColumnModel().getColumn(1).setMinWidth(200);
			table.getColumnModel().getColumn(2).setMaxWidth(60);
			table.getColumnModel().getColumn(3).setMaxWidth(80);
			table.getColumnModel().getColumn(4).setMaxWidth(30);
			//Editar con un clic
			table.setDefaultEditor(table.getColumnClass(0), singleclick);
			table.setDefaultEditor(table.getColumnClass(2), singleclick);
			table.setDefaultEditor(table.getColumnClass(4), singleclick);
		}
	}
	
	public class BotonesP extends JPanel {
		JCheckBox iva;
		JButton nuevo, guardar, exportar;
		
		public BotonesP() {
			setLayout(new BorderLayout());
			add(new TotalPanel(), BorderLayout.EAST);
			add(new EditRows(), BorderLayout.WEST);
			add(new OpcionesPanel(), BorderLayout.SOUTH);
		}
		
		public class TotalPanel extends JPanel implements ActionListener {
			public TotalPanel() {
				setLayout(new FlowLayout(FlowLayout.RIGHT));
				iva = new JCheckBox("IVA");
				iva.addActionListener(this);
				add(iva);
				add(new JLabel("Total: $"));
				total = new JLabel();
				total.setPreferredSize(new Dimension(100, 30));
				add(total);
			}

			public void actionPerformed(ActionEvent e) {
				Double totalIVA;
				if(e.getSource() == iva) {
					if(iva.isSelected()) {
						try {
							totalIVA = totalC;
							totalIVA *= 1.16;
							total.setText(Double.toString(round(totalIVA, 1)));
						}catch(NumberFormatException | NullPointerException exp) {
							JOptionPane.showMessageDialog(null, "Debe existir un total previamente", "No hay total", JOptionPane.INFORMATION_MESSAGE);
							iva.setSelected(false);
						}
					} else {
						total.setText(Double.toString(totalC));
					}
				}
			}
		}
		
		public class EditRows extends JPanel implements ActionListener {
			JButton addR, subR;
			private ImageIcon addRico = new ImageIcon(
					new ImageIcon("assets/plusR.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			private ImageIcon subRico = new ImageIcon(
					new ImageIcon("assets/minusR.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			
			public EditRows() {
				setLayout(new FlowLayout(FlowLayout.LEFT));
				add(new JLabel("Renglones:"));
				addR = new JButton(addRico);
				addR.setBorder(null);
				addR.setBackground(null);
				addR.setFocusable(false);
				addR.setToolTipText("Agregar Renglón");
				addR.addActionListener(this);
				add(addR);
				subR = new JButton(subRico);
				subR.setBorder(null);
				subR.setBackground(null);
				subR.setFocusable(false);
				subR.setToolTipText("Quitar Renglón");
				subR.addActionListener(this);
				add(subR);
			}
			
			public void AgregaRenglon() {
				String[][] aux;
				aux = renglones;
				renglones = new String[aux.length+1][7];
				for(int x = 0; x < aux.length; x++) {
					for(int y = 0; y < aux[x].length; y++) {
						renglones[x][y] = aux[x][y];
					}
				}
				aux = null;
			}
			
			public void QuitarRenglon() {
				String[][] aux;
				aux = renglones;
				renglones = new String[aux.length-1][7];
				for(int x = 0; x < renglones.length; x++) {
					for(int y = 0; y < aux[x].length; y++) {
						renglones[x][y] = aux[x][y];
					}
				}
				aux = null;
			}
			
			public void FormatoTabla() {
				table.setModel(new DefaultTableModel(renglones, head));
				table.getColumnModel().getColumn(0).setPreferredWidth(27);
				table.getColumnModel().getColumn(1).setMinWidth(200);
				table.getColumnModel().getColumn(2).setMaxWidth(60);
				table.getColumnModel().getColumn(3).setMaxWidth(80);
				table.getColumnModel().getColumn(4).setMaxWidth(30);
			}

			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == addR) {
					if(renglones.length < 25) {
						AgregaRenglon();
						FormatoTabla();
					}
				} else if(e.getSource() == subR) {
					if(renglones.length > 1) {
						QuitarRenglon();
						FormatoTabla();
					}
				}
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
			
			public void Imprimir() {
				for(int x = 0; x < renglones.length; x++) {
					System.out.print(x+ " ");
					for(int y = 0; y < renglones[x].length; y++) {
						System.out.print(renglones[x][y] + "\t");
					}
					System.out.print("\n");
				}
				System.out.println("\n\n");
			}
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == guardar) {
					Imprimir();
				}
			}
		}
	}
}
