package com.bucciarellidev.main;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Conexion {
	String bd = "bd_parqueadero_soft";
	String user = "root";
	String password = "";
	String url = "jdbc:mysql://localhost:3306/" + bd;
	String driver = "com.mysql.cj.jdbc.Driver";
	Connection cx;
	Statement stmt;
	ResultSet rs;
	Statement stat;
	String horaSalida;

	public Connection conectar() {
		try {
			Class.forName(driver);
			cx = DriverManager.getConnection(url,user,password);
			System.out.println("Se conectó correctamente a la base de datos " +bd);
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("No pudo conectarse a la base de datos " + bd);
			ex.printStackTrace();
		}
		return cx;
	}
	
	
	public void remover(String horasalida, String patent) {
		int valor = 0;
		try {
			stmt = cx.createStatement();
			stmt.executeUpdate("UPDATE vehiculos SET hora_salida='"+ horasalida +"'WHERE patente= '" + patent + "'");


		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The vehicle could not be removed because an error has occurred.");
		}
		
	}
	

	
	
	public void insertar(String patent, String owner, String type, String timeIn) {
		try {
			stmt = cx.createStatement();
			stmt.executeUpdate("INSERT INTO `vehiculos` (`id`, `patente`, `propietario`, `tipo`, `hora_entrada`, `hora_salida`, `paga`) VALUES (NULL , '" + patent + "','" + owner + "','" + type + "','" + timeIn + "', NULL, '0')");
			JOptionPane window = new JOptionPane();
			int valor=window.showConfirmDialog(null, "El vehiculo se registró exitosamente.", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
			if(valor == window.OK_OPTION) {
				AddCarController controller = new AddCarController();
				controller.hide();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "El vehiculo no pudo registrarse.");
		}
	}
	
	public void desconectar() {
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
