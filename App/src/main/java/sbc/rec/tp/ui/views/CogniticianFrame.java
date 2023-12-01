package sbc.rec.tp.ui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import sbc.rec.tp.ui.controllers.CogniticianFrameController;

public class CogniticianFrame{
	
private static CogniticianFrame instance;
	
	private Scene scene;
	private Parent parent;
	private CogniticianFrameController controller;
	
	private CogniticianFrame() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CogniticianFrame.fxml"));
			parent = loader.load();
			scene = new Scene(parent);
			scene.setFill(Color.TRANSPARENT);
			controller = ((CogniticianFrameController)loader.getController());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static CogniticianFrame getInstance() {
		if (instance == null) {
			instance = new CogniticianFrame();
		}
		return instance;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public CogniticianFrameController getController() {
		return controller;
	}
	
	public Parent getParent() {
		return parent;
	}

}
