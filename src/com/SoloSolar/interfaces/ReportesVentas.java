package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.SoloSolar.DB.Consulta;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class ReportesVentas {
	TableModel tm;
    TableRowSorter<TableModel> tr;
    
	public ReportesVentas(JFrame padre) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JDialog dialog = new JDialog(padre, "Reporte Ganancias");
				dialog.setMinimumSize(new Dimension(750, 545));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.add(new SearchDialog(dialog));
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.setResizable(false);
				/*dialog.setModal(true);
				dialog.setAlwaysOnTop(true);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);*/
				dialog.getRootPane().registerKeyboardAction(e -> {
					dialog.dispose();
				}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
			}
		});
	}
	
	public class SearchDialog extends JPanel implements KeyListener, ActionListener, ItemListener {
		private JDialog dg;
		private JDateChooser dateChooserI, dateChooserF;
		private JPanel panelBuscar;
		private JTable table;
		private JScrollPane jsp;
		private JTextField pedidoI, pedidoF;
		private JLabel inicio, fin, filtro;
		private JButton pdf;
		private JComboBox filtros;
		private ImageIcon pdfIcon = new ImageIcon(
				new ImageIcon("assets/pdfnew.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelBuscar = new JPanel();
			dateChooserI = new JDateChooser();
			dateChooserF = new JDateChooser();
			pedidoI = new JTextField();
			pedidoF = new JTextField();
			inicio = new JLabel("Inicio: ");
			fin = new JLabel("Fin: ");
			filtro = new JLabel("Filtro por: ");
			filtros = new JComboBox(new String[] {"Fecha", "No. Pedido"});
			//panelBuscar.setBackground(new Color(239, 228, 176));
			panelBuscar.setBackground(new Color(153, 217, 234));
			table = new javax.swing.JTable() {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
		        };
			};

			tm = new DefaultTableModel(new String[][] {{"", "", "", "", "", ""}}, 
					new String[]{"Producto", "Cantidad", "Costo", "Precio", "Total", "Ganancia"}) {
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
	        tr = new TableRowSorter<>(tm);
	        table.setRowSorter(tr);
			pdf = new JButton("", pdfIcon);
			pdf.setMaximumSize(new Dimension(85, 60));
			pdf.setVerticalTextPosition(SwingConstants.BOTTOM);
			pdf.setHorizontalTextPosition(SwingConstants.CENTER);
			pdf.setBorder(null);
			pdf.setToolTipText("Descargar PDF");
			pdf.setContentAreaFilled(false);
			pdf.setFocusable(false);
			pdf.addActionListener(this);
			add(pdf);
			
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
			armado(true);
		}
		
		public void armado(boolean fecha) {
			setLayout(new BorderLayout());
			add(panelBuscar, BorderLayout.CENTER);
			panelBuscar.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panelBuscar.add(inicio, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1;
			gbc.ipady = 6;
			if(fecha) {
				panelBuscar.add(dateChooserI, gbc);
			} else {
				panelBuscar.add(pedidoI, gbc);
			}
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0;
			gbc.ipady = 6;
			panelBuscar.add(fin, gbc);
			pedidoI.addKeyListener(this);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1;
			gbc.ipady = 6;
			if(fecha) {
				panelBuscar.add(dateChooserF, gbc);
			} else {
				panelBuscar.add(pedidoF, gbc);
			}
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0;
			gbc.ipady = 6;
			panelBuscar.add(filtro, gbc);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1;
			panelBuscar.add(filtros, gbc);
			filtros.addItemListener(this);
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0;
			panelBuscar.add(pdf, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			panelBuscar.add(jsp, gbc);
		}
		
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			String ruta = "";
			if(e.getSource() == pdf) {
				int renglones = table.getRowCount();
				if(renglones >= 1) {
					JFileChooser f = new JFileChooser() {
						@Override
						public void approveSelection() {
							File f = getSelectedFile();
			                if (f.exists() && getDialogType() == SAVE_DIALOG) {
			                	int result = JOptionPane.showConfirmDialog(this,
			                		String.format("%s ya existe.%n ¿Desea Sobreescribirlo?", f.getName()),
			                		"El archivo ya existe", JOptionPane.YES_NO_OPTION);
	
			                    switch (result){
			                    	case JOptionPane.YES_OPTION:
			                    		super.approveSelection();
			                    		return;
			                    	case JOptionPane.NO_OPTION:
			                    		return;
			                    	case JOptionPane.CLOSED_OPTION:
			                    		return;
			                    	case JOptionPane.CANCEL_OPTION:
			                    		cancelSelection();
			                    		return;
			                    }
			                }
			                super.approveSelection();
						}
					};
					f.setSelectedFile(new File("Reporte"));
					int opcion = f.showSaveDialog(this);
					if(opcion == JFileChooser.APPROVE_OPTION) {
						File file = f.getSelectedFile();
						ruta = file.toString();
						String data[][] = dataPDF(renglones);
						GenerarPDF g = new GenerarPDF(ruta, renglones, data);
					}
				} else {
					JOptionPane.showMessageDialog(dg, "No hay datos para crear PDF", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		public String[][] dataPDF(int renglones) {
			String data[][] = new String[renglones][5];
			for(int i = 0; i < renglones; i++) {
				for(int j = 0; j < 5; j++) {
					data[i][j] = table.getModel().getValueAt(i, j).toString();
				}
			}
			return data;
		}

		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
		}

		@Override
		public void keyReleased(java.awt.event.KeyEvent e) {
			/*String filtro = buscar.getText();
	        if(!filtro.equals("")){
	            tr.setRowFilter(RowFilter.regexFilter("(?i)" + filtro, 0, 1, 2));
	        }else{
	            tr.setRowFilter(null);
	        }*/
		}

		@Override
		public void keyTyped(java.awt.event.KeyEvent e) {
		}

		@Override
		public void itemStateChanged(ItemEvent e) {	
			if(e.getSource() == filtros) {
				panelBuscar.removeAll();
				if (filtros.getSelectedIndex() == 0) {
					armado(true);
				} else {
					armado(false);
				}
				panelBuscar.updateUI();
				panelBuscar.repaint();
				repaint();
			}
		}
	}
}
