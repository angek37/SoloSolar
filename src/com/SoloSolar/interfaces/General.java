package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.fast.FastLookAndFeel;

public class General {
	
	public General() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                	UIManager.setLookAndFeel(new FastLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Solo - Solar");
                frame.setMinimumSize(new Dimension(600, 450));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new GeneralPanel(frame));
                frame.setResizable(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
	
	public class GeneralPanel extends JPanel implements ActionListener{
		private JMenuBar menu;
		private JMenu clientes, productos, categoria;
		private JMenuItem altaClie, bajaClie, modClie, 
						altaProd, bajaProd, modProd, altaCat, adminCat;
		private ImageIcon logo = new ImageIcon(new ImageIcon("assets/logo.png").getImage().getScaledInstance(154, 27, Image.SCALE_DEFAULT));
		private JLabel titulo;
		private JFrame jfp;
		private JPanel panelPrincipal = new JPanel();
		private JPanel titleBar;
		private JButton userB;
		private ImageIcon userIcon = new ImageIcon(new ImageIcon("assets/User.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		public GeneralPanel(JFrame jf) {
			jfp = jf;
			titulo = new JLabel();
			titulo.setIcon(logo);
			titulo.setBorder(new EmptyBorder(5,5,6,0));//top,left,bottom,right
			menu = new JMenuBar();
			clientes = new JMenu("Clientes");
			productos = new JMenu("Productos");
			categoria = new JMenu("Categoria");
			titleBar = new JPanel();
			altaClie  = new JMenuItem("Alta Cliente");
			bajaClie = new JMenuItem("Baja Cliente");
			modClie = new JMenuItem("Modificar Cliente");
			altaProd  = new JMenuItem("Alta Producto");
			bajaProd  = new JMenuItem("Baja Producto");
			modProd  = new JMenuItem("Modificar Producto");
			altaCat  = new JMenuItem("Alta Categoria");
			adminCat = new JMenuItem("Administrar Categorías");
			jfp.setIconImage(new ImageIcon("assets/icono.png").getImage());
			
			//Clientes
			menu.add(clientes);
			clientes.add(altaClie);
			clientes.add(bajaClie);
			clientes.add(modClie);
			altaClie.addActionListener(this);
			bajaClie.addActionListener(this);
			modClie.addActionListener(this);
			clientes.getPopupMenu().setBorder(new VerticalTextBorder());
			
			//Productos
			menu.add(productos);
			productos.add(altaProd);
			productos.add(bajaProd);
			productos.add(modProd);
			productos.getPopupMenu().setBorder(new VerticalTextBorder());
			altaProd.addActionListener(this);
			
			//Categoria
			menu.add(categoria);
			categoria.add(altaCat);
			categoria.add(adminCat);
			adminCat.addActionListener(this);
			altaCat.addActionListener(this);
			categoria.getPopupMenu().setBorder(new VerticalTextBorder());
			
			setLayout(new BorderLayout());
			add(new MenuPanel(), BorderLayout.WEST);
			add(panelPrincipal, BorderLayout.CENTER);
			userB = new JButton("admin",userIcon);
			userB.setToolTipText("Administrar datos de usuario");
			userB.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			userB.setFont(new Font("Calibri", Font.PLAIN, 10));
			userB.setHorizontalTextPosition(SwingConstants.LEFT);
			userB.setBackground(new Color(255,255,166));
			userB.setContentAreaFilled(false);
			userB.setFocusable(false);
			userB.addActionListener(this);
			panelPrincipal.setLayout(new BorderLayout());
			jfp.add(titleBar, BorderLayout.NORTH);
			titleBar.setBackground(new Color(255,255,186));
			titleBar.setLayout(new BorderLayout());
			titleBar.add(menu, BorderLayout.SOUTH);
			titleBar.add(titulo, BorderLayout.CENTER);
			titleBar.add(userB, BorderLayout.EAST);
		}
		
		public void actionPerformed(ActionEvent e) {
			panelPrincipal.removeAll();
			
			if (e.getSource() == userB) {
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
			} else if (e.getSource() == altaCat) {
				AltaCategoria acat = new AltaCategoria();
				panelPrincipal.add(acat);
			} else if (e.getSource() == adminCat) {
				AdministrarCategorias mcat = new AdministrarCategorias();
				panelPrincipal.add(mcat);
			} else if (e.getSource() == altaProd) {
				panelPrincipal.add(new AltaProducto());
			}
			
			panelPrincipal.updateUI();
			panelPrincipal.repaint();
			repaint();
		}
			
	}
	
	private static class VerticalTextBorder implements Border {
        @Override
        public Insets getBorderInsets(final Component c) {
            return new Insets(0, 15, 0, 0);
        }

        @Override
        public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
            final Graphics2D g2 = (Graphics2D) g;
            final AffineTransform fontAT = new AffineTransform();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            fontAT.rotate(-Math.PI / 2);
            g2.setFont(g2.getFont().deriveFont(fontAT));
            g2.drawString("", 10, height);
            c.setBackground(new Color(196, 196, 196));
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
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
			venta.setToolTipText("Registrar una venta");
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
				int reply = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el sistema?", "Cerrar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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