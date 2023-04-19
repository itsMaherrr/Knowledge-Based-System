package sbc.rec.tp.controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sbc.rec.tp.views.MainFrame;

public class UIController extends Application {

	private static Stage stage;
	

	private Stage initStage(Stage stage) {
		if (UIController.stage == null) {
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setResizable(false);
			UIController.stage = stage;
		}
		return UIController.stage;	
	}

	@Override
	public void start(Stage stage) throws Exception {
		initStage(stage);		
		setMainWindow(stage);
		stage.show();
	}

	private void setMainWindow(Stage stage) {
		MainFrame mainFrame = MainFrame.getInstance();
		stage.setScene(mainFrame.getScene());
		mainFrame.getController().init(stage);
		
	}

	public void run() {
		launch();
	}
    
}
