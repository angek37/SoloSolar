package com.SoloSolar.interfaces;

import com.SoloSolar.DB.*;

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

public class Login {
	public Login() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Inicio de SesiÃ³n");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new LoginWindow(frame));
                frame.pack();
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	
	public class LoginWindow extends JPanel implements ActionListener {
		public JLabel icono;
		private JTextField user;
		private JPasswordField pass;
		private JButton signin;
		private ImageIcon access = new ImageIcon(new ImageIcon("assets/login.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));		
		private ImageIcon erroricon = new ImageIcon(new ImageIcon("assets/error-icon.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));		
		private JFrame JF;
		
			public LoginWindow(JFrame frame) {
				JF = frame;
				setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.insets = new Insets(8, 8, 8, 8);
				gbc.anchor = GridBagConstraints.EAST;
				gbc.gridx = 0;
				gbc.gridy = 0;
				add(new JLabel("Usuario:"), gbc);
				user = new JTextField();
				gbc.gridx++;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weightx = 2;
				add(user, gbc);
				
				gbc.anchor = GridBagConstraints.EAST;
				gbc.gridx = 0;
				gbc.gridy++;
				add(new JLabel("Contraseña:"), gbc);
				pass = new JPasswordField();
				
				gbc.gridx++;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weightx = 2;
				add(pass, gbc);
				signin = new JButton("Iniciar Sesión");
				gbc.gridy++;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weightx = 2;
				add(signin, gbc);
	            signin.addActionListener(this);
				icono = new JLabel(access);
				gbc.gridx++;
				gbc.gridy = 0;
				gbc.gridheight = 3;
				gbc.fill = GridBagConstraints.VERTICAL;
				add(icono, gbc);
			}

			public void actionPerformed(ActionEvent e) {
				ConnectionDB c = new ConnectionDB();
				
				if(e.getSource() == signin) {
					if(c.getPassword("Usuario", user.getText()).equals(pass.getText())) {
						//new General();
						JF.setVisible(false);
					}else {
						user.setText("");
						pass.setText("");
						icono.setIcon(erroricon);
					}
				}
			}
			
	}
	
	public static void main(String[] mr) {
		new Login();
	}
}
