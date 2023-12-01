package sbc.rec.tp.ui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import sbc.rec.tp.ui.controllers.UserFrameController;

public class UserFrame {

	private static UserFrame instance;
	
	private Scene scene;
	private Parent parent;
	private UserFrameController controller;
	
	private UserFrame() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFrame.fxml"));
			parent = loader.load();
			scene = new Scene(parent);
			scene.setFill(Color.TRANSPARENT);
			controller = ((UserFrameController)loader.getController());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserFrame getInstance() {
		if (instance == null) {
			instance = new UserFrame();
		}
		return instance;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public UserFrameController getController() {
		return controller;
	}
	
	public Parent getParent() {
		return parent;
	}

	
}
