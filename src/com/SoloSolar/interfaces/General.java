package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

                JFrame frame = new JFrame("Solo - Solar");
                //frame.setUndecorated(true);
    			frame.setSize(600, 450);
    			frame.setMinimumSize(new Dimension(600, 450));
                frame.add(new GeneralPanel(frame));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	
	public class GeneralPanel extends JPanel implements ActionListener{
		private JMenuBar menu;
		private JMenu usuarios, clientes, productos, categoria, salir;
		private JMenuItem altaUser, bajaUser, modUser, altaClie, bajaClie, modClie, 
						altaProd, bajaProd, modProd, altaCat, adminCat, salProg;
		
		private JFrame jfp;
		private JPanel panelPrincipal = new JPanel();
		
		public GeneralPanel(JFrame jf) {
			jfp = jf;
			
			menu = new JMenuBar();
			usuarios = new JMenu("Usuarios");
			clientes = new JMenu("Clientes");
			productos = new JMenu("Productos");
			categoria = new JMenu("Categoria");
			salir = new JMenu("Salir...");
			/*altaUser = new JMenuItem("Alta Usuario");
			bajaUser = new JMenuItem("Baja Usuario");*/
			modUser  = new JMenuItem("Modificar Usuario");
			altaClie  = new JMenuItem("Alta Cliente");
			bajaClie = new JMenuItem("Baja Cliente");
			modClie = new JMenuItem("Modificar Cliente");
			altaProd  = new JMenuItem("Alta Producto");
			bajaProd  = new JMenuItem("Baja Producto");
			modProd  = new JMenuItem("Modificar Producto");
			altaCat  = new JMenuItem("Alta Categoria");
			adminCat = new JMenuItem("Administrar Categorías");
			salProg = new JMenuItem("Salir del Sistema");
			
			//Usuarios
			menu.add(usuarios);
			/*usuarios.add(altaUser);
			usuarios.add(bajaUser);*/
			usuarios.add(modUser);
			modUser.addActionListener(this);
			
			//Clientes
			menu.add(clientes);
			clientes.add(altaClie);
			clientes.add(bajaClie);
			clientes.add(modClie);
			altaClie.addActionListener(this);
			bajaClie.addActionListener(this);
			modClie.addActionListener(this);
			
			//Productos
			menu.add(productos);
			productos.add(altaProd);
			productos.add(bajaProd);
			productos.add(modProd);
			
			//Categoria
			menu.add(categoria);
			categoria.add(altaCat);
			categoria.add(adminCat);
			adminCat.addActionListener(this);
			altaCat.addActionListener(this);
			//Salir
			menu.add(salir);
			salir.add(salProg);
			salProg.addActionListener(this);
			
			setLayout(new BorderLayout());
			add(new MenuPanel(), BorderLayout.WEST);
			add(panelPrincipal, BorderLayout.CENTER);
			panelPrincipal.setLayout(new BorderLayout());
			jfp.add(menu, BorderLayout.NORTH);
		}
		
		public void actionPerformed(ActionEvent e) {
			panelPrincipal.removeAll();
			
			if (e.getSource() == modUser) {
				ModificarUsuario modUsuario = new ModificarUsuario();
				panelPrincipal.add(modUsuario);
			} else if (e.getSource() == altaClie) {
				AltaCliente ac = new AltaCliente();
				panelPrincipal.add(ac);
			} else if (e.getSource() == bajaClie) {
				BajaCliente bc = new BajaCliente();
				panelPrincipal.add(bc);
			} else if(e.getSource() == modClie) {
				ModificarCliente mc = new ModificarCliente();
				panelPrincipal.add(mc);
			} else if (e.getSource() == salProg) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el sistema?", "Cerrar Sistema", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			} else if (e.getSource() == altaCat) {
				AltaCategoria acat = new AltaCategoria();
				panelPrincipal.add(acat);
			} else if (e.getSource() == adminCat) {
				AdministrarCategorias mcat = new AdministrarCategorias();
				panelPrincipal.add(mcat);
			}
			
			panelPrincipal.updateUI();
			panelPrincipal.repaint();
			repaint();
		}
			
	}
	
	public class MenuPanel extends JPanel implements ActionListener {
		private JButton venta, reporte, buscar, salir;
		private ImageIcon purchase = new ImageIcon(new ImageIcon("assets/cashier.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));		
		private ImageIcon search = new ImageIcon(new ImageIcon("assets/research.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));		
		private ImageIcon reports = new ImageIcon(new ImageIcon("assets/newspaper.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		private ImageIcon exit = new ImageIcon(new ImageIcon("assets/exit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		public MenuPanel() {
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			venta = new JButton("Venta", purchase);
			venta.setMaximumSize(new Dimension(85,60));
			venta.setVerticalTextPosition(SwingConstants.BOTTOM);
			venta.setHorizontalTextPosition(SwingConstants.CENTER);
			venta.setBorder(null);
			add(venta);
			buscar = new JButton("Buscar", search);
			buscar.setMaximumSize(new Dimension(85,60));
			buscar.setVerticalTextPosition(SwingConstants.BOTTOM);
			buscar.setHorizontalTextPosition(SwingConstants.CENTER);
			buscar.setBorder(null);
			add(buscar);
			reporte = new JButton("Reportes", reports);
			reporte.setMaximumSize(new Dimension(85,60));
			reporte.setVerticalTextPosition(SwingConstants.BOTTOM);
			reporte.setHorizontalTextPosition(SwingConstants.CENTER);
			reporte.setBorder(null);
			add(reporte);
			salir = new JButton("Salir", exit);
			salir.setMaximumSize(new Dimension(85,60));
			salir.setVerticalTextPosition(SwingConstants.BOTTOM);
			salir.setHorizontalTextPosition(SwingConstants.CENTER);
			salir.setBorder(null);
			add(salir);
			salir.addActionListener(this);
			
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == salir) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el sistema?", "Cerrar Sistema", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}
		
	}
	
	public static void main(String[] mr) {
		new General();
	}
}
