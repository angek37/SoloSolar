package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
                frame.setUndecorated(true);
    			frame.setMinimumSize(new Dimension(600, 450));
                frame.add(new GeneralPanel(frame));
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	
	public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
	
	public class GeneralPanel extends JPanel implements ActionListener{
		private JMenuBar menu;
		private JMenu usuarios, clientes, productos, categoria, salir;
		private JMenuItem altaUser, bajaUser, modUser, altaClie, bajaClie, modClie, 
						altaProd, bajaProd, modProd, altaCat, adminCat, salProg;
		private ImageIcon barraTitulo, cerrarImg, minimizarImg;
		private ImageFondo titulo;
		private JFrame jfp;
		private JPanel titleBar, panelPrincipal = new JPanel();
		private JButton cerrar, minimizar;
		
		public GeneralPanel(JFrame jf) {
			FrameDragListener frameDragListener = new FrameDragListener(jf);
			jfp = jf;
			barraTitulo = new ImageIcon(new ImageIcon("assets/titulo.jpg").getImage());
			titulo = new ImageFondo(barraTitulo.getImage());
			FlowLayout fl = new FlowLayout();
			fl.setAlignment(FlowLayout.RIGHT);
			titulo.setLayout(fl);
			titulo.addMouseListener(frameDragListener);
			titulo.addMouseMotionListener(frameDragListener);
			titulo.setBackground(new Color(56, 170, 57));
			menu = new JMenuBar();
			usuarios = new JMenu("Usuarios");
			clientes = new JMenu("Clientes");
			productos = new JMenu("Productos");
			categoria = new JMenu("Categoria");
			titleBar = new JPanel();
			salir = new JMenu("Salir...");
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
			panelPrincipal.setBackground(new Color(153, 217, 234));
			cerrarImg = new ImageIcon(new ImageIcon("assets/cerrar.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			minimizarImg = new ImageIcon(new ImageIcon("assets/minimizar.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			cerrar = new JButton("", cerrarImg);
			cerrar.setMaximumSize(new Dimension(85,60));
			cerrar.setVerticalTextPosition(SwingConstants.BOTTOM);
			cerrar.setHorizontalTextPosition(SwingConstants.CENTER);
			cerrar.setBorder(null);
			cerrar.setFocusable(false);
			cerrar.setContentAreaFilled(false);
			minimizar = new JButton("", minimizarImg);
			minimizar.setMaximumSize(new Dimension(85,60));
			minimizar.setVerticalTextPosition(SwingConstants.BOTTOM);
			minimizar.setHorizontalTextPosition(SwingConstants.CENTER);
			minimizar.setBorder(null);
			minimizar.setFocusable(false);
			minimizar.setContentAreaFilled(false);
			titulo.add(minimizar, BorderLayout.EAST);
			titulo.add(cerrar, BorderLayout.EAST);
			minimizar.addActionListener(this);
			cerrar.addActionListener(this);
			
			//Usuarios
			menu.add(usuarios);
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
			jfp.add(titleBar, BorderLayout.NORTH);
			titleBar.setLayout(new BorderLayout());
			titleBar.setBackground(new Color(56, 170, 57));
			titleBar.add(titulo, BorderLayout.NORTH);
			titleBar.add(menu, BorderLayout.SOUTH);
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
			} else if (e.getSource() == cerrar) {
				System.exit(0);
			} else if (e.getSource() == minimizar) {
				jfp.setExtendedState(1);
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
			//setBackground(new Color(153, 217, 234));
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
