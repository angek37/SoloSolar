package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

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
	
	public class SearchDialog extends JPanel {
		private JDialog dg;
		private JPanel panelPrincipal;
		
		public SearchDialog(JDialog dialog) {
			dg = dialog;
			dg.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelPrincipal = new JPanel();
			//panelPrincipal.setBackground(new Color(64, 128, 128));
			panelPrincipal.setBackground(new Color(239, 228, 176));
			
			setLayout(new BorderLayout());
			add(panelPrincipal, BorderLayout.CENTER);
			
		}
	}
}
