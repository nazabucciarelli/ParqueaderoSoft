package com.bucciarellidev.panels;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bucciarellidev.main.Conexion;
import com.bucciarellidev.main.ListCarController;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListCar extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatent;
	private JTextField textFieldOwner;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public ListCar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 400, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel lblSearch = new JLabel("Search Vehicles");
		lblSearch.setForeground(new Color(255, 140, 0));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setBounds(10, 11, 614, 23);
		contentPane.add(lblSearch);
		
		JLabel lblPatent = new JLabel("Patent");
		lblPatent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatent.setBounds(223, 55, 57, 23);
		contentPane.add(lblPatent);
		
		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOwner.setBounds(370, 55, 57, 23);
		contentPane.add(lblOwner);
		
		textFieldPatent = new JTextField();
		textFieldPatent.setBounds(205, 79, 86, 20);
		contentPane.add(textFieldPatent);
		textFieldPatent.setColumns(10);
		
		textFieldOwner = new JTextField();
		textFieldOwner.setColumns(10);
		textFieldOwner.setBounds(351, 79, 86, 20);
		contentPane.add(textFieldOwner);
		
		JLabel lblType = new JLabel("Type of vehicle");
		lblType.setBounds(283, 128, 86, 14);
		contentPane.add(lblType);
		
		JCheckBox chckbxCar = new JCheckBox("Car");
		chckbxCar.setBounds(248, 149, 57, 23);
		contentPane.add(chckbxCar);
		
		JCheckBox chckbxMotocycle = new JCheckBox("Motocycle");
		chckbxMotocycle.setBounds(318, 149, 97, 23);
		contentPane.add(chckbxMotocycle);
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Patent", "Owner", "Type of Vehicle", "Check in time", "Check out time", "Pay"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] datos = new String[7];
				String patent = textFieldPatent.getText();
				String owner = textFieldOwner.getText();
				String type = "";
				
				// Esta porcion de codigo limpiará la tabla, para luego mostrar otros resultados.
				model.getDataVector().removeAllElements();
				tableCustomers.updateUI();
				//Hasta aca
				
				if(chckbxCar.isSelected()) {
					if (chckbxMotocycle.isSelected()) {
						type = "";
					} else {
						type = "Car";
					}
				} else if(chckbxMotocycle.isSelected()) {
						type = "Motocycle";
				} 
				
				
				try {
					Conexion connect = new Conexion();
					Connection cn = connect.conectar();
					
					Statement read = cn.createStatement();
					ResultSet resultado = read.executeQuery("SELECT * FROM vehiculos WHERE patente LIKE '%" + patent +"%' AND propietario LIKE '%" + owner +"%' AND tipo LIKE '%"+ type+"%'");
					
					while(resultado.next()) {
						datos[0] = String.valueOf(resultado.getInt(1)); 
						datos[1] = resultado.getString(2); 
						datos[2] = resultado.getString(3); 
						datos[3] = resultado.getString(4); 
						datos[4] = resultado.getString(5); 
						datos[5] = resultado.getString(6); 
						datos[6] = String.valueOf(resultado.getInt(7));
						model.addRow(datos);
					}
				} catch (SQLException ea) {
					ea.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnFind.setBounds(172, 427, 89, 23);
		contentPane.add(btnFind);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCarController off = new ListCarController();
				off.hide();
			}
		});
		btnExit.setBounds(417, 427, 89, 23);
		contentPane.add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 179, 614, 234);
		contentPane.add(scrollPane);
		
		
		tableCustomers = new JTable();

		tableCustomers.setModel(model);
		tableCustomers.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableCustomers.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableCustomers.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableCustomers.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableCustomers.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableCustomers.getColumnModel().getColumn(5).setPreferredWidth(100);
		scrollPane.setViewportView(tableCustomers);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableCustomers.getSelectedRow();
				String value = tableCustomers.getValueAt(row, 0).toString();
				
				Conexion connect = new Conexion();
				
				
				
				Connection con = connect.conectar();
				try {
					Statement stat = con.createStatement();
					stat.executeUpdate("DELETE from vehiculos WHERE id='"+ value +"'");
					btnFind.doClick();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(298, 427, 89, 23);
		contentPane.add(btnDelete);
		
		

	}
	
	private JTable tableCustomers;
}

