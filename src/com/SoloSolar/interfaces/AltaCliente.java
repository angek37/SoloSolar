package com.SoloSolar.interfaces;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.SoloSolar.DB.ConnectionDB;

/*public class AltaCliente {
	
	public AltaCliente() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                General frame = new General("Alta de clientes");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Altas(frame));
                frame.pack();
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	*/
	public class AltaCliente extends JPanel {
		public JLabel icono;
		private JTextField user;
		private JPasswordField pass;
		private JButton signin;
		private ImageIcon access = new ImageIcon(new ImageIcon("assets/login.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));		
		private ImageIcon erroricon = new ImageIcon(new ImageIcon("assets/error-icon.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));		
		private JFrame JF;
		
		public AltaCliente () {
			//JF = frame;
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.anchor = GridBagConstraints.EAST;
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(new JLabel("Usuario:"), gbc);
		}

	}
	/*
	public static void main(String[] mr) {
		new AltaCliente();
	}*/
//}
