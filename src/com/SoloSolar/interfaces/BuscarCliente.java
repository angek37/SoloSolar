package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
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

public class BuscarCliente {
	TableModel tm;
    TableRowSorter<TableModel> tr;
	JTextField idCliente, nombreCliente;
	
	public BuscarCliente(JTextField idCliente, JTextField nombreCliente) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JDialog dialog = new JDialog();
				dialog.setMinimumSize(new Dimension(500, 530));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.add(new SearchDialog(dialog));
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.setAlwaysOnTop(true);
				dialog.getRootPane().registerKeyboardAction(e -> {
					dialog.dispose();
				}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
			}
		});
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
	}
	
	public class SearchDialog extends JPanel implements KeyListener, MouseListener {
		private JDialog dg;
		private JPanel panelBuscar;
		private JTable table;
		private JScrollPane jsp;
		private JTextField buscar;
		private JLabel buscarLBL;
		
		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelBuscar = new JPanel();
			panelBuscar.setBackground(new Color(153, 217, 234));
			buscarLBL = new JLabel("Buscar: ");
			buscar = new JTextField();
			table = new javax.swing.JTable() {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
		        };
			};
			tm = new DefaultTableModel(Consulta.dataClientes(), 
					new String[]{"ID", "Nombre", "Ciudad"}) {
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
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			panelBuscar.add(jsp, gbc);
		}

		@Override
		public void keyPressed(java.awt.event.KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(java.awt.event.KeyEvent arg0) {
			String filtro = buscar.getText();
	        if(!filtro.equals("")){
	            tr.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, 0, 1, 2));
	        }else{
	            tr.setRowFilter(null);
	        }
		}

		@Override
		public void keyTyped(java.awt.event.KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 && !e.isConsumed()) {
				e.consume();
				idCliente.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 0));
				nombreCliente.setText("" + table.getModel().getValueAt(table.getSelectedRow(), 1));
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
	}
}
