package com.bucciarellidev.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.bucciarellidev.main.Conexion;
import com.bucciarellidev.main.RemoveCarController;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class RemoveCar extends JFrame {

	private JPanel contentPane;
	private JTextField textPatentRemove;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveCar frame = new RemoveCar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RemoveCar() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel lblTitleRemove = new JLabel("Remove Car");
		lblTitleRemove.setForeground(new Color(255, 140, 0));
		lblTitleRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleRemove.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitleRemove.setBounds(10, 34, 464, 29);
		contentPane.add(lblTitleRemove);
		
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				Date date = cal.getTime();
				String fechaHora = dateFormat.format(date);
				
				String patente = textPatentRemove.getText();
				Conexion connection = new Conexion();
				connection.conectar();
				connection.remover(fechaHora,patente);
				
				
				
				
				try {
					Connection cx;
					String bd = "bd_parqueadero_soft";
					String user = "root";
					String password = "";
					String url = "jdbc:mysql://localhost:3306/" + bd;
					String driver = "com.mysql.cj.jdbc.Driver";
					Class.forName(driver);
					cx = DriverManager.getConnection(url,user,password);
					Statement stat;
					ResultSet rs;
					String horaEntrada;
					double valorAPagar = 0.0;
					
					stat = cx.createStatement();
					rs = stat.executeQuery("SELECT * FROM vehiculos WHERE patente = '"+ patente +"'");
					if(rs.next()) {
                    horaEntrada = rs.getString(5);
                    Date horaentrada = dateFormat.parse(horaEntrada);
                    Date horasalida = dateFormat.parse(fechaHora);
                    int minutosAPagar = (int) (horaentrada.getTime() - horasalida.getTime()) / 60000;
                    if(rs.getString(4).equals("Car")) {
                    	 valorAPagar = Math.abs(minutosAPagar) * 3;
                    } else if (rs.getString(4).equals("Motocycle")) {
                    	valorAPagar = Math.abs(minutosAPagar) * 2;
                    }

                    
                    stat.executeUpdate("UPDATE vehiculos SET paga='" + valorAPagar +"' WHERE patente= '" + patente + "'");
					}
					
					int value = JOptionPane.showConfirmDialog(null, "The vehicle has been removed. The owner must pay $" + valorAPagar, "Advertencia", JOptionPane.OK_CANCEL_OPTION);
					if(value == JOptionPane.OK_OPTION) {
						RemoveCarController controller = new RemoveCarController();
						controller.hide();
					}
	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnRemove.setBackground(new Color(255, 140, 0));
		btnRemove.setForeground(new Color(0, 0, 0));
		btnRemove.setBounds(199, 370, 99, 59);
		contentPane.add(btnRemove);
		
		JLabel lblPatentRemove = new JLabel("Patent");
		lblPatentRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatentRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatentRemove.setBounds(10, 180, 464, 14);
		contentPane.add(lblPatentRemove);
		
		textPatentRemove = new JTextField();
		textPatentRemove.setHorizontalAlignment(SwingConstants.CENTER);
		textPatentRemove.setBounds(177, 205, 138, 51);
		contentPane.add(textPatentRemove);
		textPatentRemove.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 484, 22);
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
	}

}
