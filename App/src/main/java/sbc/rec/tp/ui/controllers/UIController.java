package sbc.rec.tp.ui.controllers;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sbc.rec.tp.ui.views.MainFrame;

public class UIController extends Application {

	private static Stage stage;
	

	private Stage initStage(Stage stage) {
		if (UIController.stage == null) {
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setResizable(false);
			stage.setTitle("Recipes Catalog");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/sbc/rec/tp/ui/images/menu.png")));
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
