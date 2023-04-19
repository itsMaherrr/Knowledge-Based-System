package sbc.rec.tp.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import sbc.rec.tp.controllers.MainFrameController;

public class MainFrame {

	private static MainFrame instance;
	
	private Scene scene;
	private MainFrameController controller;
	
	private MainFrame() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
			scene = new Scene(loader.load());
			scene.setFill(Color.TRANSPARENT);
			controller = ((MainFrameController)loader.getController());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	public Scene getScene() {
		return scene;
	}
	public MainFrameController getController() {
		return controller;
	}

	
}
