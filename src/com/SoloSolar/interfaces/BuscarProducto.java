package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.SoloSolar.DB.Consulta;
import com.sun.glass.events.KeyEvent;

public class BuscarProducto {
	TableModel tm;
    TableRowSorter<TableModel> tr;
    int renglon;
	String[][] renglones;
	String[] head = {"Clave", "Nombre de Producto", "Cantidad", "Pack", "L", "Precio", "SubTotal"};
	JTable tabla;
	
	public BuscarProducto(String[][] renglones, JTable tableT, int renglon, JFrame frame) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JDialog dialog = new JDialog(frame, "Buscar Producto");
				dialog.setMinimumSize(new Dimension(750, 530));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.add(new SearchDialog(dialog));
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.setResizable(false);
				dialog.getRootPane().registerKeyboardAction(e -> {
					dialog.dispose();
				}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
			}
		});
		this.renglones = renglones;
		this.tabla = tableT;
		this.renglon = renglon;
	}
	
	public class SearchDialog extends JPanel implements KeyListener, MouseListener {
		private JDialog dg;
		private JPanel panelBuscar;
		private JTable table;
		private JScrollPane jsp;
		private JTextField buscar;
		private JLabel buscarLBL;
		String opciones[] = {"1", "2"};
		private JComboBox combo; 
		
		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelBuscar = new JPanel();
			panelBuscar.setBackground(new Color(153, 217, 234));
			buscarLBL = new JLabel("Buscar: ");
			buscar = new JTextField();
			combo = new JComboBox(opciones);
			table = new javax.swing.JTable() {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
		        };
			};
			tm = new DefaultTableModel(Consulta.dataProductsP(), 
					new String[]{"Clave", "Nombre", "Categoria", "Pack", "Precio 1", "Precio2"}) {
	            public Class getColumnClass(int column) {
	                Class Value;
	                if (column >= 0 && column < getColumnCount()) {
	                    Value = getValueAt(0, column).getClass();
	                } else {
	                    Value = Object.class;
	                }
	                return Value;
	            }
	        };
	        table.setModel(tm);
	        table.addMouseListener(this);
	        tr = new TableRowSorter<>(tm);
	        table.setRowSorter(tr);
	        table.setFillsViewportHeight(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.getTableHeader().setReorderingAllowed(false);
			table.setValueAt("aosidjjo", 0, 1);
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
			jsp = new JScrollPane(table);
			
			setLayout(new BorderLayout());
			add(panelBuscar, BorderLayout.CENTER);
			panelBuscar.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panelBuscar.add(buscarLBL, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 2;
			gbc.ipady = 6;
			panelBuscar.add(buscar, gbc);
			buscar.addKeyListener(this);
			
			gbc.gridx++;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panelBuscar.add(new JLabel("Precio: "), gbc);
			
			gbc.gridx++;
			gbc.weightx = 0.5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panelBuscar.add(combo, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			panelBuscar.add(jsp, gbc);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 && !e.isConsumed()) {
				String sel = combo.getSelectedItem() + "";
				e.consume();
				tabla.setValueAt(table.getModel().getValueAt(table.getSelectedRow(), 0), renglon, 0);
				tabla.setValueAt(table.getModel().getValueAt(table.getSelectedRow(), 1), renglon, 1);
				tabla.setValueAt(table.getModel().getValueAt(table.getSelectedRow(), 3), renglon, 3);
				tabla.setValueAt(sel, renglon, 4);
				tabla.setValueAt(sel.equals("1") 
						?  table.getModel().getValueAt(table.getSelectedRow(), 4) 
						: table.getModel().getValueAt(table.getSelectedRow(), 5), renglon, 5);
				dg.dispose();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(java.awt.event.KeyEvent e) {
			String filtro = buscar.getText();
	        if(!filtro.equals("")){
	            tr.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, 0, 1, 2));
	        }else{
	            tr.setRowFilter(null);
	        }
		}

		@Override
		public void keyTyped(java.awt.event.KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
