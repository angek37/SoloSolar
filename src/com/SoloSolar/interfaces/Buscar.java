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
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

import com.SoloSolar.Capsulas.Categoria;
import com.SoloSolar.Capsulas.Producto;
import com.SoloSolar.DB.Consulta;
import com.SoloSolar.interfaces.AltaCategoria.CategoryModel;
import com.SoloSolar.interfaces.AltaCliente.ClientModel;
import com.sun.glass.events.KeyEvent;

public class Buscar {

	public Buscar(JFrame padre) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JDialog dialog = new JDialog(padre, "Solo - Solar (Buscar)");
				dialog.setMinimumSize(new Dimension(750, 500));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.add(new SearchDialog(dialog));
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.getRootPane().registerKeyboardAction(e -> {
					dialog.dispose();
				}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
			}
		});
	}

	public class SearchDialog extends JPanel implements KeyListener {
		private JDialog dg;
		private JPanel panelPrincipal, panelBuscar;
		private JTable table;
		private JScrollPane jsp;
		private JTextField buscar;
		private JLabel buscarLBL;

		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelBuscar = new JPanel();
			panelPrincipal = new JPanel();
			panelBuscar.setBackground(new Color(239, 228, 176));
			panelPrincipal.setBackground(new Color(239, 228, 16));
			buscarLBL = new JLabel("Buscar: ");
			buscar = new JTextField();
			table = new JTable(new ProductModel(""));
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
			return 2;
		}
		
		public String getColumnName(int col) {
			String aux = "";
		      switch(col) {
		      case 0: 
		    	  return aux = "Nombre";
		      case 1: 
		    	  return aux = "Descripción";
		      }
			return aux;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			   Object value = null;
			   Producto prod = producto[rowIndex];
			   switch (columnIndex) {
			   case 0:
				   value = prod.getNombre();
				   break;
			   case 1:
				   value = prod.getCategoria();
				   break;
			   }
	           return value;
		}
	}
}
