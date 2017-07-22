package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.SoloSolar.interfaces.General.GeneralPanel;
import com.jtattoo.plaf.fast.FastLookAndFeel;

public class Buscar extends JFrame {
	
	public Buscar() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	UIManager.setLookAndFeel(new FastLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Buscar");
                frame.setMinimumSize(new Dimension(750, 500));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new SearchDialog(frame));
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	
	public class SearchDialog extends JPanel {
		private JFrame jf;
		private JPanel panelPrincipal;
		
		public SearchDialog(JFrame frame) {
			jf = frame;
			jf.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelPrincipal = new JPanel();
			//panelPrincipal.setBackground(new Color(64, 128, 128));
			panelPrincipal.setBackground(new Color(239, 228, 176));
			
			setLayout(new BorderLayout());
			add(panelPrincipal, BorderLayout.CENTER);
			
		}
	}
}
