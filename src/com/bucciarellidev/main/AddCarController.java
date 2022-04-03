package com.bucciarellidev.main;

import com.bucciarellidev.panels.AddCar;

public class AddCarController {
	public static AddCar window = new AddCar();
	public static void show() {
		window.setVisible(true);
	}
	public static void hide() {
		window.setVisible(false);
	}
}
