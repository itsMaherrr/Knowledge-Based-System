package sbc.rec.tp.ui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import sbc.rec.tp.ui.controllers.LoginFrameController;

public class LoginFrame {
	
private static LoginFrame instance;
	
	private Scene scene;
	private Parent parent;
	private LoginFrameController controller;
	
	private LoginFrame() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFrame.fxml"));
			parent = loader.load();
			scene = new Scene(parent);
			scene.setFill(Color.TRANSPARENT);
			controller = ((LoginFrameController)loader.getController());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static LoginFrame getInstance() {
		if (instance == null) {
			instance = new LoginFrame();
		}
		return instance;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public LoginFrameController getController() {
		return controller;
	}
	
	public Parent getParent() {
		return parent;
	}

}
