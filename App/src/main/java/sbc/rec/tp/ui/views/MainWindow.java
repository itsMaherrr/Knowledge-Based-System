package sbc.rec.tp.ui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import sbc.rec.tp.ui.controllers.MainWindowController;

public class MainWindow {
	
private static MainWindow instance;
	
	private Scene scene;
	private MainWindowController controller;
	
	private MainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			scene = new Scene(loader.load());
			scene.setFill(Color.TRANSPARENT);
			controller = ((MainWindowController)loader.getController());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	public Scene getScene() {
		return scene;
	}
	public MainWindowController getController() {
		return controller;
	}


}
