package com.SoloSolar.interfaces;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Venta extends JPanel {
	
	public Venta() {
		setLayout(new BorderLayout());
		add(new DatosP(), BorderLayout.NORTH);
		add(new TablaP(), BorderLayout.CENTER);
		add(new BotonesP(), BorderLayout.SOUTH);
	}
	
	public class DatosP extends JPanel {
		JTextField pedido, idCliente, nombreCliente, observaciones;
		JButton buscarCliente;
		private ImageIcon customerIco = new ImageIcon(
				new ImageIcon("assets/searchCustomer.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		private JDatePickerImpl datePicker;
		
		public DatosP() {
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(10, 10, 0, 10);
			gbc.weightx = 1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipady = 8;
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(new JLabel("Pedido:"), gbc);
			pedido = new JTextField();
			pedido.setEnabled(false);
			gbc.ipadx = 80;
			gbc.gridx++;
			add(pedido, gbc);
			gbc.gridx++;
			gbc.ipadx = 20;
			add(new JLabel("Cliente:"), gbc);
			buscarCliente = new JButton(customerIco);
			buscarCliente.setBorder(null);
			buscarCliente.setBackground(null);
			buscarCliente.setFocusable(false);
			gbc.ipadx = 30;
			gbc.gridx++;
			add(buscarCliente, gbc);
			idCliente = new JTextField();
			idCliente.setEnabled(false);
			gbc.gridx++;
			gbc.ipadx = 80;
			add(idCliente, gbc);
			nombreCliente = new JTextField();
			nombreCliente.setEnabled(false);
			gbc.ipadx = 300;
			gbc.gridx++;
			add(nombreCliente, gbc);
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 0;
			add(new JLabel("Fecha:"), gbc);
			UtilDateModel model = new UtilDateModel();
			model.setSelected(true);
			Properties p = new Properties();
			p.put("text.today", "Hoy");
			p.put("text.month", "Mes");
			p.put("text.year", "Año");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
			gbc.ipadx = 90;
			gbc.gridx++;
			add(datePicker, gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			gbc.ipadx = 20;
			add(new JLabel("Observaciones:"), gbc);
			observaciones = new JTextField();
			gbc.gridx++;
			gbc.gridwidth = 4;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(observaciones, gbc);
		}
		
	}
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "dd/MM/yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	
	public class TablaP extends JPanel {
		
	}
	
	public class BotonesP extends JPanel {
		
	}
}
