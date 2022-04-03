package com.bucciarellidev.panels;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import com.bucciarellidev.main.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Home extends JFrame {

	private JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	

	public Home() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 400, 400, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Parqueadero-Soft");
		lblTitle.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 73, 384, 29);
		contentPane.add(lblTitle);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("data\\cars.png"));
		lblIcon.setBounds(119, 71, 174, 167);
		contentPane.add(lblIcon);
		
		JButton btnAddCar = new JButton("Add Car");
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCarController.show();
			}
		});
		btnAddCar.setBackground(new Color(0, 206, 209));
		btnAddCar.setForeground(Color.BLACK);
		btnAddCar.setBounds(71, 237, 107, 52);
		contentPane.add(btnAddCar);
		
		JButton btnListCar = new JButton("List Car");
		btnListCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCarController.show();
			}
		});
		btnListCar.setForeground(Color.BLACK);
		btnListCar.setBackground(new Color(0, 206, 209));
		btnListCar.setBounds(71, 304, 107, 52);
		contentPane.add(btnListCar);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(new Color(0, 206, 209));
		btnExit.setBounds(219, 304, 107, 52);
		contentPane.add(btnExit);
		
		JButton btnRemoveCar = new JButton("Remove Car");
		btnRemoveCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveCarController.show();
			}
		});
		btnRemoveCar.setForeground(Color.BLACK);
		btnRemoveCar.setBackground(new Color(0, 206, 209));
		btnRemoveCar.setBounds(219, 237, 107, 52);
		contentPane.add(btnRemoveCar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 384, 22);
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
		
		JLabel lblNewLabel = new JLabel("\u00A9 All rights reserved by Nazareno Bucciarelli");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 373, 384, 38);
		contentPane.add(lblNewLabel);
		
		setTitle("Parqueadero-Soft");
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
