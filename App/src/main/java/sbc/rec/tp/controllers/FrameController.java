package sbc.rec.tp.controllers;

import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public abstract class FrameController {

	@FXML protected Pane closeBtn, minimizeBtn;
	@FXML protected HBox titlePane;
	@FXML protected AnchorPane window;
	
	protected double x, y;
	
	public void init(Stage stage) {
		titlePane.setOnMousePressed(mouseEvent -> {
			x = mouseEvent.getSceneX();
			y = mouseEvent.getSceneY();
		});
		titlePane.setOnMouseDragged(mouseEvent -> {
			stage.setX(mouseEvent.getScreenX()-x);
			stage.setY(mouseEvent.getScreenY()-y);
		});
		closeBtn.setOnMouseClicked(mouseEvent -> {
			stage.close();
			System.exit(0);
		});
		minimizeBtn.setOnMouseClicked(mouseEvent -> {
			stage.setIconified(true);
		});
		window.setOnMouseClicked(mouseEvent -> {
			window.requestFocus();
		});
	}
    
}