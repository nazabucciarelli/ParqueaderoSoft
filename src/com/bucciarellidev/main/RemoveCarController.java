package com.bucciarellidev.main;

import com.bucciarellidev.panels.RemoveCar;

public class RemoveCarController {
	public static RemoveCar window = new RemoveCar();
	public static void show() {
		window.setVisible(true);
	}
	public static void hide() {
		window.setVisible(false);
	}
}
