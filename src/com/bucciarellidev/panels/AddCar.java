package com.bucciarellidev.panels;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bucciarellidev.main.Conexion;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class AddCar extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatent;
	private JTextField textFieldOwnerName;

	

	public AddCar() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Add Car");
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 34, 464, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblPatent = new JLabel("Patent");
		lblPatent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatent.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatent.setBounds(10, 110, 464, 29);
		contentPane.add(lblPatent);
		
		textFieldPatent = new JTextField();
		textFieldPatent.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPatent.setBounds(199, 150, 99, 53);
		contentPane.add(textFieldPatent);
		textFieldPatent.setColumns(10);
		
		JLabel lblOwnerName = new JLabel("Owner Name");
		lblOwnerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblOwnerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOwnerName.setBounds(10, 214, 464, 17);
		contentPane.add(lblOwnerName);
		
		textFieldOwnerName = new JTextField();
		textFieldOwnerName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOwnerName.setBounds(140, 242, 210, 29);
		contentPane.add(textFieldOwnerName);
		textFieldOwnerName.setColumns(10);
		
		JLabel lblTypeVehicle = new JLabel("Type of Vehicle");
		lblTypeVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeVehicle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTypeVehicle.setBounds(10, 273, 464, 23);
		contentPane.add(lblTypeVehicle);
		
		JPanel panel = new JPanel();
		panel.setBounds(109, 298, 281, 40);
		contentPane.add(panel);
		
		JRadioButton rdbtnCar = new JRadioButton("Car");
		panel.add(rdbtnCar);
		
		JRadioButton rdbtnMotocycle = new JRadioButton("Motocycle");
		panel.add(rdbtnMotocycle);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vehicleClass = "";
				if(rdbtnMotocycle.isSelected()) {
					vehicleClass = "Motocycle";
				}
				else if (rdbtnCar.isSelected()) {
					vehicleClass = "Car";
				}
				
				String patent = textFieldPatent.getText();
				String ownerName = textFieldOwnerName.getText();
				
				
				
				Conexion connect = new Conexion();
				connect.conectar();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				Date date = cal.getTime();
				String fechaHora = dateFormat.format(date);
	
				
				try {
					connect.insertar(patent, ownerName, vehicleClass, fechaHora);	
				}
				catch(Exception ex) {
					ex.printStackTrace();

					}
					
		

			}
		});
		btnAdd.setBackground(new Color(255, 140, 0));
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBounds(199, 370, 99, 59);
		contentPane.add(btnAdd);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 2, 484, 22);
		contentPane.add(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnOptions.add(mntmExit);
		
		ButtonGroup grupoBotones = new ButtonGroup();
		grupoBotones.add(rdbtnMotocycle);
		grupoBotones.add(rdbtnCar);
	

	
	}
}
