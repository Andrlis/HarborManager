package application;

import ui_package.*;
import javax.swing.JFrame;
import DataPackage.Harbor;
import data_base.StockDAO;

import org.apache.log4j.Logger;

public class Main {
	public static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		logger.info("Start program");
		
		StockDAO stockDAO = new StockDAO();
		stockDAO.getConnection();
		Harbor harbor = new Harbor(5);
		harbor.setStock(stockDAO.select());

		JFrame mainWindow = new MainWindow(harbor);

		mainWindow.setVisible(true);
		stockDAO.closeConnection();
	}
}
