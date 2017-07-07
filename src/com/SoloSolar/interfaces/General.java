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
    			frame.setSize(600, 400);
                frame.add(new GeneralPanel(frame));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	
	public class GeneralPanel extends JPanel implements ActionListener{
		private JMenuBar menu;
		private JMenu usuarios, clientes, productos, categoria, salir;
		private JMenuItem altaUser, bajaUser, modUser, altaClie, bajaClie, modClie, 
						altaProd, bajaProd, modProd, altaCat, bajaCat, modCat, salProg;
		
		private JFrame jfp;
		private JPanel panelPrincipal = new JPanel();
		private AltaCliente ac = new AltaCliente();
		private BajaCliente bc = new BajaCliente();
		private ModificarCliente mc = new ModificarCliente();
		private AltaCategoria acat = new AltaCategoria();
		
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
			bajaCat  = new JMenuItem("Baja Categoria");
			modCat = new JMenuItem("Modificar Categoria");
			salProg = new JMenuItem("Salir");
			
			//Usuarios
			menu.add(usuarios);
			/*usuarios.add(altaUser);
			usuarios.add(bajaUser);*/
			usuarios.add(modUser);
			
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
			categoria.add(bajaCat);
			categoria.add(modCat);
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
			if (e.getSource() == altaClie) {
				panelPrincipal.add(ac);
			} else if (e.getSource() == bajaClie) {
				panelPrincipal.add(bc);
			} else if(e.getSource() == modClie) {
				panelPrincipal.add(mc);
			} else if(e.getSource() == salProg) {
				System.exit(0);
			} else if (e.getSource() == altaCat) {
				panelPrincipal.add(acat);
			}
			
			panelPrincipal.updateUI();
			panelPrincipal.repaint();
			repaint();
		}
			
	}
	
	public class MenuPanel extends JPanel {
		private JButton venta, reporte, buscar;
		private ImageIcon purchase = new ImageIcon(new ImageIcon("assets/cashier.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));		
		private ImageIcon search = new ImageIcon(new ImageIcon("assets/research.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));		
		private ImageIcon reports = new ImageIcon(new ImageIcon("assets/newspaper.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));		

		
		public MenuPanel() {
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			venta = new JButton("Venta",purchase);
			venta.setMaximumSize(new Dimension(85,60));
			venta.setVerticalTextPosition(SwingConstants.BOTTOM);
			venta.setHorizontalTextPosition(SwingConstants.CENTER);
			venta.setBorder(null);
			add(venta);
			buscar = new JButton("Buscar",search);
			buscar.setMaximumSize(new Dimension(85,60));
			buscar.setVerticalTextPosition(SwingConstants.BOTTOM);
			buscar.setHorizontalTextPosition(SwingConstants.CENTER);
			buscar.setBorder(null);
			add(buscar);
			reporte = new JButton("Reportes",reports);
			reporte.setMaximumSize(new Dimension(85,60));
			reporte.setVerticalTextPosition(SwingConstants.BOTTOM);
			reporte.setHorizontalTextPosition(SwingConstants.CENTER);
			reporte.setBorder(null);
			add(reporte);
			
		}
	}
	
	public static void main(String[] mr) {
		new General();
	}
}
