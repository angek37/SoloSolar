package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.DB.Consulta;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.glass.events.KeyEvent;

public class Buscar {
	TableModel tm;
    TableRowSorter<TableModel> tr;

	public Buscar(JFrame padre) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JDialog dialog = new JDialog(padre, "Solo - Solar (Buscar)");
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

	public class SearchDialog extends JPanel implements KeyListener, ActionListener {
		private JDialog dg;
		private JPanel panelBuscar;
		private JTable table;
		private JScrollPane jsp;
		private JTextField buscar;
		private JLabel buscarLBL;
		private JButton pdf;
		private ImageIcon pdfIcon = new ImageIcon(
				new ImageIcon("assets/pdfnew.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelBuscar = new JPanel();
			//panelBuscar.setBackground(new Color(239, 228, 176));
			panelBuscar.setBackground(new Color(153, 217, 234));
			buscarLBL = new JLabel("Buscar: ");
			buscar = new JTextField();
			table = new javax.swing.JTable() {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
		        };
			};
			tm = new DefaultTableModel(Consulta.dataProducts(), 
					new String[]{"Clave", "Nombre", "Categoria", "Precio 1", "Precio2"}) {
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
			
			setLayout(new BorderLayout());
			add(panelBuscar, BorderLayout.CENTER);
			panelBuscar.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.anchor = GridBagConstraints.WEST;
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
			String ruta = "", dataPDF[][] = Consulta.dataPDF(buscar.getText());
			if(e.getSource() == pdf) {
				JFileChooser f = new JFileChooser();
				f.setSelectedFile(new File("reporte"));
				int opcion = f.showSaveDialog(this);
				if(opcion == JFileChooser.APPROVE_OPTION) {
					File file = f.getSelectedFile();
					ruta = file.toString();
				}
				
				try {
					FileOutputStream archivo = new FileOutputStream(ruta + ".pdf");
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					doc.add(new Paragraph(ruta));
					int celdas = Consulta.cantidadDatosPDF(buscar.getText());
					PdfPTable tab = new PdfPTable(5);
					PdfPCell cellClave = new PdfPCell(new Paragraph("Clave")),
							 cellNombre = new PdfPCell(new Paragraph("Nombre")),
							 cellCat = new PdfPCell(new Paragraph("Categoria")),
							 cellPre1 = new PdfPCell(new Paragraph("Precio 1")),
							 cellPre2 = new PdfPCell(new Paragraph("Precio2"));
					cellClave.setBorderWidthBottom(1f);
					cellClave.setBorderWidthTop(1f);
					cellClave.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					cellNombre.setBorderWidthBottom(1f);
					cellNombre.setBorderWidthTop(1f);
					cellNombre.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					cellCat.setBorderWidthBottom(1f);
					cellCat.setBorderWidthTop(1f);
					cellCat.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					cellPre1.setBorderWidthBottom(1f);
					cellPre1.setBorderWidthTop(1f);
					cellPre1.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					cellPre2.setBorderWidthBottom(1f);
					cellPre2.setBorderWidthTop(1f);
					cellPre2.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
					tab.addCell(cellClave);
					tab.addCell(cellNombre);
					tab.addCell(cellCat);
					tab.addCell(cellPre1);
					tab.addCell(cellPre2);
					tab.setTotalWidth(510f);
					tab.setLockedWidth(true);
					tab.setHorizontalAlignment(0);
					tab.getDefaultCell().setBorder(Rectangle.BOTTOM);
					for(int i = 0; i < Consulta.cantidadDatosPDF(buscar.getText()); i++) {
						for(int j = 0; j < 5; j++) {
							tab.addCell(dataPDF[i][j]);
						}
					}
					doc.add(tab);
					doc.close();
					JOptionPane.showMessageDialog(null, "Se ha guardado correctamente", "¡Exito!", JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException | DocumentException e1) {
					e1.printStackTrace();
				}
				
			}
		}

		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
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
		}
	}
}
