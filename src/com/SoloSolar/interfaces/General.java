package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.fast.FastLookAndFeel;

public class General {
	private JFrame frame;
	private JPanel panelPrincipal;
	private ScalablePane bienvenida = new ScalablePane(new ImageIcon("assets/bienvenido.png").getImage());

	public General() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FastLookAndFeel f = new FastLookAndFeel();
					f.setTheme("Default", "", "Solo - Solar");
					UIManager.setLookAndFeel(f);
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
				frame = new JFrame("Solo - Solar");
				frame.setMinimumSize(new Dimension(860, 550));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new GeneralPanel(frame));
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class GeneralPanel extends JPanel implements ActionListener {
		private JMenuBar menu;
		private JMenu clientes, productos, categoria, proveedor;
		private JMenuItem altaClie, modClie, altaProd, altaCat, adminCat, adminProd, altaProv, adminProv, prodProv;
		private ImageIcon logo = new ImageIcon(
				new ImageIcon("assets/logo.png").getImage().getScaledInstance(154, 27, Image.SCALE_DEFAULT));
		private JLabel titulo;
		private JFrame jfp;
		private JPanel titleBar;
		private JButton userB;
		private ImageIcon userIcon = new ImageIcon(
				new ImageIcon("assets/User.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		private ImageIcon addC = new ImageIcon(
				new ImageIcon("assets/add.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon admC = new ImageIcon(
				new ImageIcon("assets/admin.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon addP = new ImageIcon(
				new ImageIcon("assets/addP.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon admP = new ImageIcon(
				new ImageIcon("assets/admP.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon addCat = new ImageIcon(
				new ImageIcon("assets/addCat.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon admCat = new ImageIcon(
				new ImageIcon("assets/admCat.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon addProv = new ImageIcon(
				new ImageIcon("assets/addProv.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon admProv = new ImageIcon(
				new ImageIcon("assets/admProv.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		private ImageIcon proProv = new ImageIcon(
				new ImageIcon("assets/proProv.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));

		public GeneralPanel(JFrame jf) {
			jfp = jf;
			titulo = new JLabel();
			titulo.setIcon(logo);
			titulo.setBorder(new EmptyBorder(5, 5, 6, 0));// top,left,bottom,right
			menu = new JMenuBar();
			menu.setBackground(new Color(232, 232, 232));
			clientes = new JMenu("Clientes");
			productos = new JMenu("Productos");
			categoria = new JMenu("Categoria");
			proveedor = new JMenu("Proveedores");
			titleBar = new JPanel();
			altaClie = new JMenuItem("Alta Cliente", addC);
			modClie = new JMenuItem("Administrar Clientes", admC);
			altaProd = new JMenuItem("Alta Producto", addP);
			adminProd = new JMenuItem("Administar Productos", admP);
			altaCat = new JMenuItem("Alta Categoria", addCat);
			adminCat = new JMenuItem("Administrar Categorías", admCat);
			altaProv = new JMenuItem("Alta Proveedor", addProv);
			adminProv = new JMenuItem("Administrar Proveedores", admProv);
			prodProv = new JMenuItem("Productos por Proveedor", proProv);
			
			jfp.setIconImage(new ImageIcon("assets/icono.png").getImage());
			panelPrincipal = new JPanel();
			panelPrincipal.setBackground(new Color(64, 128, 128));

			// Clientes
			menu.add(clientes);
			clientes.add(altaClie);
			clientes.addSeparator();
			clientes.add(modClie);
			altaClie.addActionListener(this);
			modClie.addActionListener(this);
			// clientes.getPopupMenu().setBorder(new VerticalTextBorder());

			// Productos
			menu.add(productos);
			productos.add(altaProd);
			productos.addSeparator();
			productos.add(adminProd);
			// productos.getPopupMenu().setBorder(new VerticalTextBorder());
			altaProd.addActionListener(this);
			adminProd.addActionListener(this);

			// Categoria
			menu.add(categoria);
			categoria.add(altaCat);
			categoria.addSeparator();
			categoria.add(adminCat);
			adminCat.addActionListener(this);
			altaCat.addActionListener(this);
			// categoria.getPopupMenu().setBorder(new VerticalTextBorder());
			
			// Proveedor
			menu.add(proveedor);
			proveedor.add(altaProv);
			proveedor.addSeparator();
			proveedor.add(adminProv);
			proveedor.addSeparator();
			proveedor.add(prodProv);
			prodProv.addActionListener(this);
			altaProv.addActionListener(this);
			adminProv.addActionListener(this);			

			setLayout(new BorderLayout());
			add(new MenuPanel(), BorderLayout.WEST);
			add(panelPrincipal, BorderLayout.CENTER);
			userB = new JButton("admin", userIcon);
			userB.setToolTipText("Administrar datos de usuario");
			userB.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			userB.setFont(new Font("Calibri", Font.PLAIN, 10));
			userB.setHorizontalTextPosition(SwingConstants.LEFT);
			userB.setBackground(new Color(255, 255, 166));
			userB.setContentAreaFilled(false);
			userB.setFocusable(false);
			userB.addActionListener(this);
			panelPrincipal.setLayout(new BorderLayout());
			jfp.add(titleBar, BorderLayout.NORTH);
			titleBar.setBackground(new Color(255, 255, 186));
			titleBar.setLayout(new BorderLayout());
			titleBar.add(menu, BorderLayout.SOUTH);
			titleBar.add(titulo, BorderLayout.CENTER);
			titleBar.add(userB, BorderLayout.EAST);
			panelPrincipal.add(bienvenida);
		}

		public void actionPerformed(ActionEvent e) {
			panelPrincipal.removeAll();
			if (e.getSource() == userB) {
				ModificarUsuario modUsuario = new ModificarUsuario();
				panelPrincipal.add(modUsuario);
			} else if (e.getSource() == altaClie) {
				AltaCliente ac = new AltaCliente();
				panelPrincipal.add(ac);
			} else if (e.getSource() == modClie) {
				AdministrarClientes mc = new AdministrarClientes();
				panelPrincipal.add(mc);
			} else if (e.getSource() == altaCat) {
				AltaCategoria acat = new AltaCategoria();
				panelPrincipal.add(acat);
			} else if (e.getSource() == adminCat) {
				AdministrarCategorias mcat = new AdministrarCategorias();
				panelPrincipal.add(mcat);
			} else if (e.getSource() == altaProd) {
				panelPrincipal.add(new AltaProducto());
			} else if (e.getSource() == adminProd) {
				panelPrincipal.add(new AdministrarProducto());
			} else if (e.getSource() == altaProv) {
				panelPrincipal.add(new AltaProveedor());
			} else if (e.getSource() == adminProv) {
				panelPrincipal.add(new AdministrarProveedor());
			} else if (e.getSource() == prodProv) {
				panelPrincipal.add(new ProductosPorProveedor());
			}

			panelPrincipal.updateUI();
			panelPrincipal.repaint();
			repaint();
		}

	}

	public class MenuPanel extends JPanel implements ActionListener {
		private JButton bienvenida, venta, reporte, buscar, salir;
		private ImageIcon home = new ImageIcon(
				new ImageIcon("assets/home.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		private ImageIcon purchase = new ImageIcon(
				new ImageIcon("assets/cashier.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		private ImageIcon search = new ImageIcon(
				new ImageIcon("assets/research.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		private ImageIcon reports = new ImageIcon(
				new ImageIcon("assets/newspaper.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		private ImageIcon exit = new ImageIcon(
				new ImageIcon("assets/exit.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

		public MenuPanel() {
			setBackground(new Color(232, 232, 232));
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			bienvenida = new JButton("Bienvenida", home);
			bienvenida.setMaximumSize(new Dimension(85, 60));
			bienvenida.setVerticalTextPosition(SwingConstants.BOTTOM);
			bienvenida.setHorizontalTextPosition(SwingConstants.CENTER);
			bienvenida.setBorder(null);
			bienvenida.setToolTipText("Bienvenida");
			bienvenida.setFocusable(false);
			bienvenida.addActionListener(this);
			add(bienvenida);
			venta = new JButton("Venta", purchase);
			venta.setMaximumSize(new Dimension(85, 60));
			venta.setVerticalTextPosition(SwingConstants.BOTTOM);
			venta.setHorizontalTextPosition(SwingConstants.CENTER);
			venta.setBorder(null);
			venta.setToolTipText("Registrar una venta");
			venta.setFocusable(false);
			venta.addActionListener(this);
			add(venta);
			buscar = new JButton("Buscar", search);
			buscar.setMaximumSize(new Dimension(85, 60));
			buscar.setVerticalTextPosition(SwingConstants.BOTTOM);
			buscar.setHorizontalTextPosition(SwingConstants.CENTER);
			buscar.setBorder(null);
			buscar.setToolTipText("Buscar productos");
			buscar.setFocusable(false);
			buscar.addActionListener(this);
			add(buscar);
			reporte = new JButton("Reportes", reports);
			reporte.setMaximumSize(new Dimension(85, 60));
			reporte.setVerticalTextPosition(SwingConstants.BOTTOM);
			reporte.setHorizontalTextPosition(SwingConstants.CENTER);
			reporte.setBorder(null);
			reporte.setToolTipText("Crear reporte");
			reporte.setFocusable(false);
			reporte.addActionListener(this);
			add(reporte);
			salir = new JButton("Salir", exit);
			salir.setMaximumSize(new Dimension(85, 60));
			salir.setVerticalTextPosition(SwingConstants.BOTTOM);
			salir.setHorizontalTextPosition(SwingConstants.CENTER);
			salir.setBorder(null);
			salir.setToolTipText("Salir del programa");
			salir.setFocusable(false);
			add(salir);
			salir.addActionListener(this);

		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bienvenida) {
				panelPrincipal.removeAll();
				panelPrincipal.add(General.this.bienvenida);
				panelPrincipal.updateUI();
				panelPrincipal.repaint();
				repaint();
			} else if (e.getSource() == venta) {
				panelPrincipal.removeAll();
				panelPrincipal.add(new Venta(frame));
				panelPrincipal.updateUI();
				panelPrincipal.repaint();
			} else if (e.getSource() == buscar) {
				Buscar b = new Buscar(frame);
			} else if (e.getSource() == salir) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el sistema?", "Cerrar Sistema",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			} else if (e.getSource() == reporte) {
				new ReportesVentas(frame);
			}
		}

	}

	public static void main(String[] mr) {
		new General();
	}
}