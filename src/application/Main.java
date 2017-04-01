package application;

import ui_package.*;
import javax.swing.JFrame;
import DataPackage.Harbor;

public class Main {
	public static void main(String[] args) {
		Harbor harbor = new Harbor(5);

		JFrame mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
