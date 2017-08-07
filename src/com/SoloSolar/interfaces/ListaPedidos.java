package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ListaPedidos extends JPanel {
	JTable table;
	
	public ListaPedidos() {
		setLayout(new BorderLayout());
		add(new TablePanel(), BorderLayout.CENTER);
		add(new OptionsPanel(), BorderLayout.SOUTH);
	}
	
	public class TablePanel extends JPanel {
		
		public TablePanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			table = new JTable();
			add(table, gbc);
		}
	}
	
	public class OptionsPanel extends JPanel {
		
	}
}
