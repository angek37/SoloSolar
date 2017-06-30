package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.SoloSolar.interfaces.General.GeneralPanel;

public class General {
	
	public General() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new GeneralPanel("Solo - Solar");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	
	public class GeneralPanel extends JFrame implements ActionListener{
		private JMenuBar menu;
		private JMenu usuarios, clientes, productos, categoria, salir;
		private JMenuItem altaUser, bajaUser, modUser, altaClie, bajaClie, modClie, 
						altaProd, bajaProd, modProd, altaCat, bajaCat, modCat, cerrSes, salProg;
		
		public GeneralPanel(String title) {
			super(title);
			setSize(500, 500);
			menu = new JMenuBar();
			usuarios = new JMenu("Usuarios");
			clientes = new JMenu("Clientes");
			productos = new JMenu("Productos");
			categoria = new JMenu("Categoria");
			salir = new JMenu("Salir...");
			altaUser = new JMenuItem("Alta Usuario");
			bajaUser = new JMenuItem("Baja Usuario");
			modUser  = new JMenuItem("Modificar Usuario");
			altaClie  = new JMenuItem("Alta Cliente");
			bajaClie = new JMenuItem("Baja Cliente");
			modClie = new JMenuItem("Modificar Cliente");
			altaProd  = new JMenuItem("Alta Producto");
			bajaProd  = new JMenuItem("Baja Producto");
			modProd  = new JMenuItem("Modificar Producto");
			altaCat  = new JMenuItem("Alta Categoria");
			bajaCat  = new JMenuItem("Baja Categoria");
			modCat = new JMenuItem("Modificar Categoria");
			cerrSes = new JMenuItem("Cerrar sesión");
			salProg = new JMenuItem("Salir");
			add(menu, BorderLayout.NORTH);
			
			//Usuarios
			menu.add(usuarios);
			usuarios.add(altaUser);
			usuarios.add(bajaUser);
			usuarios.add(modUser);
			
			//Clientes
			menu.add(clientes);
			clientes.add(altaClie);
			clientes.add(bajaClie);
			clientes.add(modClie);
			
			//Productos
			menu.add(productos);
			productos.add(altaProd);
			productos.add(bajaProd);
			productos.add(modProd);
			
			//Categoria
			menu.add(categoria);
			categoria.add(altaCat);
			categoria.add(bajaCat);
			categoria.add(modCat);
			
			//Salir
			menu.add(salir);
			salir.add(cerrSes);
			salir.add(salProg);
			
			salProg.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == salProg) {
				System.exit(0);
			}
		}
			
	}
	
	/*
	public static void main(String[] mr) {
		new General();
	}
	*/
}
