package application;

import ui_package.*;
import javax.swing.JFrame;
import DataPackage.Harbor;
import MyThreads.StatusThread;
import data_base.StockDAO;
import logic.HarborLogic;

import org.apache.log4j.Logger;

public class Main {
	public static final Logger logger = Logger.getLogger(Main.class);

	public static StatusThread statusTh;
	
	public static void main(String[] args) {

		logger.info("Start program");
		
		HarborLogic harborLogic = new HarborLogic(4);
		
		statusTh = new StatusThread(harborLogic.getHarbor());
		statusTh.start();
		
		harborLogic.loadShipQueue();
		
		JFrame mainWindow = new MainWindow(harborLogic);
		
		harborLogic.start();

		mainWindow.setVisible(true);
		
		//harborLogic.finish();
	}
}
