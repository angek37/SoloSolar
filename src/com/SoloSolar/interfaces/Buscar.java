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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.DB.Consulta;
import com.sun.glass.events.KeyEvent;

public class Buscar {

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
				new ImageIcon("assets/pdf.png").getImage().getScaledInstance(150, 30, Image.SCALE_DEFAULT));

		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelBuscar = new JPanel();
			//panelBuscar.setBackground(new Color(239, 228, 176));
			panelBuscar.setBackground(new Color(153, 217, 234));
			buscarLBL = new JLabel("Buscar: ");
			buscar = new JTextField();
			table = new JTable(new ProductModel(""));
			pdf = new JButton("Descargar PDF", pdfIcon);
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
			add(panelBuscar, BorderLayout.NORTH);
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
			panelBuscar.add(buscar, gbc);
			buscar.addKeyListener(this);
			
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			panelBuscar.add(jsp, gbc);
			
			gbc.gridx = 0;
			gbc.gridy++;
			panelBuscar.add(pdf, gbc);
			
		}
		
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			
		}

		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
		}

		@Override
		public void keyReleased(java.awt.event.KeyEvent e) {
			table.setModel(new ProductModel(buscar.getText()));
		}

		@Override
		public void keyTyped(java.awt.event.KeyEvent e) {
		}
	}
	
	public class ProductModel extends AbstractTableModel {
		String product;
		Consulta select = new Consulta();
		Producto[] producto;
		
		public ProductModel(String product) {
			this.product = product;
			this.producto = select.seekProducts(product);
		}
		
		public int getRowCount() {
			return producto.length;
		}

		public int getColumnCount() {
			return 5;
		}
		
		public String getColumnName(int col) {
			String aux = "";
		      switch(col) {
		      case 0: 
		    	  return aux = "Clave";
		      case 1: 
		    	  return aux = "Nombre";
		      case 2:
		    	  return aux = "Categoria";
		      case 3: 
		    	  return aux = "Precio 1";
		      case 4:
		    	  return aux = "Precio 2";
		      }
			return aux;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			   Object value = null;
			   Producto prod = producto[rowIndex];
			   switch (columnIndex) {
			   case 0:
				   value = prod.getClave();
				   break;
			   case 1:
				   value = prod.getNombre();
				   break;
			   case 2: 
				   value = prod.getCategoriaNombre();
				   break;
			   case 3:
				   value = prod.getPrecio1();
				   break;
			   case 4:
				   value = prod.getPrecio2();
				   break;
			   }
	           return value;
		}
	}
}
