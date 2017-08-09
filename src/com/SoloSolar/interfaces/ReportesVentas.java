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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.Calendar;

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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.SoloSolar.DB.Consulta;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class ReportesVentas {
	DefaultTableModel dm;
	double ivaReport = 0;
    
	public ReportesVentas(JFrame padre) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JDialog dialog = new JDialog(padre, "Reporte Ganancias");
				dialog.setMinimumSize(new Dimension(800, 550));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.add(new SearchDialog(dialog));
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				//dialog.setResizable(false);
				/*dialog.setModal(true);
				dialog.setAlwaysOnTop(true);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);*/
				dialog.getRootPane().registerKeyboardAction(e -> {
					dialog.dispose();
				}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
			}
		});
	}
	
	public class SearchDialog extends JPanel implements ActionListener, ItemListener {
		private String reporte = "Sin filtro.";
		private JDialog dg;
		private JDateChooser dateChooserI, dateChooserF;
		private JPanel panelBuscar;
		private JTable table;
		private JScrollPane jsp;
		private JTextField pedidoI, pedidoF;
		private JLabel inicio, fin, filtro, preciol;
		private JButton pdf, filter;
		private JComboBox filtros, precio;
		private ImageIcon pdfIcon = new ImageIcon(
				new ImageIcon("assets/pdfnew.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		private ImageIcon filterIcon = new ImageIcon(
				new ImageIcon("assets/filter.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));

		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelBuscar = new JPanel();
			dateChooserI = new JDateChooser();
			dateChooserF = new JDateChooser();
			dateChooserI.getDateEditor().setEnabled(false);
			dateChooserF.getDateEditor().setEnabled(false);
			pedidoI = new JTextField();
			pedidoF = new JTextField();
			preciol = new JLabel("Precio: ");
			inicio = new JLabel("Inicio: ");
			fin = new JLabel("Fin: ");
			filtro = new JLabel("Filtro por: ");
			precio = new JComboBox(new String[] {"1", "2"});
			filtros = new JComboBox(new String[] {"Fecha", "No. Pedido"});
			filtros.addItemListener(this);
			//panelBuscar.setBackground(new Color(239, 228, 176));
			panelBuscar.setBackground(new Color(153, 217, 234));
			table = new javax.swing.JTable(Consulta.dataVentas(), 
					new String[]{"Producto", "Cantidad", "Costo", "Total", "Ganancia"}) {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
		        };
			};			
	        filter = new JButton("Filtrar", filterIcon);
			filter.setMaximumSize(new Dimension(65, 40));
			filter.setVerticalTextPosition(SwingConstants.BOTTOM);
			filter.setHorizontalTextPosition(SwingConstants.CENTER);
			filter.setBorder(null);
			filter.setToolTipText("Descargar PDF");
			filter.setContentAreaFilled(false);
			filter.setFocusable(false);
			filter.addActionListener(this);
			
			pdf = new JButton("", pdfIcon);
			pdf.setMaximumSize(new Dimension(85, 60));
			pdf.setVerticalTextPosition(SwingConstants.BOTTOM);
			pdf.setHorizontalTextPosition(SwingConstants.CENTER);
			pdf.setBorder(null);
			pdf.setToolTipText("Descargar PDF");
			pdf.setContentAreaFilled(false);
			pdf.setFocusable(false);
			pdf.addActionListener(this);
			pdf.setEnabled(false);
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
			table.getColumnModel().getColumn(1).setMaxWidth(150);
			table.getColumnModel().getColumn(1).setMinWidth(150);
			table.getColumnModel().getColumn(2).setMaxWidth(100);
			table.getColumnModel().getColumn(2).setMinWidth(100);
			table.getColumnModel().getColumn(3).setMaxWidth(100);
			table.getColumnModel().getColumn(3).setMinWidth(100);
			table.getColumnModel().getColumn(4).setMaxWidth(100);
			table.getColumnModel().getColumn(4).setMinWidth(100);
			
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
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1;
			gbc.ipady = 6;
			if(fecha) {
				panelBuscar.add(dateChooserF, gbc);
				
				gbc.gridx++;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weightx = 0;
				panelBuscar.add(preciol, gbc);
				
				gbc.gridx++;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weightx = 1;
				panelBuscar.add(precio, gbc);
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
			
			gbc.gridx++;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0;
			panelBuscar.add(filter, gbc);
			
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
					f.setSelectedFile(new File("Reporte Ganancias"));
					int opcion = f.showSaveDialog(this);
					if(opcion == JFileChooser.APPROVE_OPTION) {
						File file = f.getSelectedFile();
						ruta = file.toString();
						String data[][] = dataPDF(renglones);
						GenerarPDFReportes g = new GenerarPDFReportes(ruta, renglones, data, reporte, ivaReport);
					}
				} else {
					JOptionPane.showMessageDialog(dg, "No hay datos para crear PDF", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if(e.getSource() == filter) {
				if (filtros.getSelectedIndex() == 0) {
					if(dateChooserI.getDate() == null || dateChooserF.getDate() == null ||
							dateChooserF.getDate().equals("") || dateChooserI.getDate().equals("")) {
						dm = new DefaultTableModel(Consulta.dataVentas(), 
								new String[]{"Producto", "Cantidad", "Costo", "Total", "Ganancia"});
				        table.setModel(dm);
				        reporte = "Sin filtro.";
				        pdf.setEnabled(false);
					} else {
						String fechaI =  dateChooserI.getCalendar().get(Calendar.YEAR)
								+ "-" + (dateChooserI.getCalendar().get(Calendar.MONTH) + 1)
								+ "-" + dateChooserI.getCalendar().get(Calendar.DAY_OF_MONTH);
						String fechaF =  dateChooserF.getCalendar().get(Calendar.YEAR)
								+ "-" + (dateChooserF.getCalendar().get(Calendar.MONTH) + 1)
								+ "-" + dateChooserF.getCalendar().get(Calendar.DAY_OF_MONTH);
						int precioSel = Integer.parseInt(precio.getSelectedItem().toString());
						String dataVentas[][] = Consulta.dataVentasFecha(fechaI, fechaF, precioSel);
						if(fechaI.compareTo(fechaF) <= 0) {
							if(dataVentas.length > 0) {
								dm = new DefaultTableModel(dataVentas, 
										new String[]{"Producto", "Cantidad", "Costo", "Total", "Ganancia"});
						        table.setModel(dm);
						        reporte = "Reporte por fecha de " + fechaI + " a " + fechaF;
						        pdf.setEnabled(true);
						        for(int i = 0; i < dataVentas.length; i++) {
						        	ivaReport = ivaReport + Double.parseDouble(dataVentas[i][5]);
						        }
							} else {
								JOptionPane.showMessageDialog(null, "No se encontraron datos", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "La fecha inicial debe ser antes de la fecha final", "¡Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					if(pedidoF.getText() == null || pedidoI.getText() == null ||
							pedidoF.equals("") || pedidoI.equals("")) {
						dm = new DefaultTableModel(Consulta.dataVentas(), 
								new String[]{"Producto", "Cantidad", "Costo", "Total", "Ganancia"});
				        table.setModel(dm);
				        reporte = "Sin filtro.";
				        pdf.setEnabled(false);
					} else {
						String inicio = pedidoI.getText();
						String fin = pedidoF.getText();
						String[][] dataPedido = Consulta.dataVentasPedido(inicio, fin);
						if(Integer.parseInt(inicio) <= Integer.parseInt(fin)) {
							if(dataPedido.length > 0) {
								dm = new DefaultTableModel(dataPedido, 
										new String[]{"Producto", "Cantidad", "Costo", "Total", "Ganancia"});
						        table.setModel(dm);
						        reporte = "Reporte por numero de pedido de " + inicio + " a " + fin;
						        pdf.setEnabled(true);
						        for(int i = 0; i < dataPedido.length; i++) {
						        	ivaReport = ivaReport + Double.parseDouble(dataPedido[i][5]);
						        }
							} else {
								JOptionPane.showMessageDialog(null, "No se encontraron datos", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "El numero de pedido inicial debe ser antes del numero de pedido final", "¡Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				table.getColumnModel().getColumn(1).setMaxWidth(150);
				table.getColumnModel().getColumn(1).setMinWidth(150);
				table.getColumnModel().getColumn(2).setMaxWidth(100);
				table.getColumnModel().getColumn(2).setMinWidth(100);
				table.getColumnModel().getColumn(3).setMaxWidth(100);
				table.getColumnModel().getColumn(3).setMinWidth(100);
				table.getColumnModel().getColumn(4).setMaxWidth(100);
				table.getColumnModel().getColumn(4).setMinWidth(100);
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
