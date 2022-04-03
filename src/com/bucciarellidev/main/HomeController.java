package com.bucciarellidev.main;
import com.bucciarellidev.panels.*;

public class HomeController {
	public static Home window = new Home();
	public static void show() {
		window.setVisible(true);
	}
	public static void hide() {
		window.setVisible(false);
	}
}
