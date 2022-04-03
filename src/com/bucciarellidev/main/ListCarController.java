package com.bucciarellidev.main;

import com.bucciarellidev.panels.ListCar;

public class ListCarController {
	public static ListCar window = new ListCar();
	public static void show() {
		window.setVisible(true);
	}
	public static void hide() {
		window.setVisible(false);
	}
}
