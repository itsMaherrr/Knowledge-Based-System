package sbc.rec.tp.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sbc.rec.tp.ui.views.CogniticianFrame;
import sbc.rec.tp.ui.views.LoginFrame;
import sbc.rec.tp.ui.views.UserFrame;

public class MainWindowController extends WindowController {
	
	@FXML AnchorPane scene;
	@FXML StackPane sceneContainer;
	
	
	@Override
	public void init(Stage stage) {
		super.init(stage);
		initAllFrames();
		addAllFrames();
		setUserFrame();
	}
	
	private void initAllFrames() {
		UserFrame.getInstance().getController().init();
		LoginFrame.getInstance().getController().init();
		CogniticianFrame.getInstance().getController().init();
	}
	
	private void addAllFrames() {
		this.sceneContainer.getChildren().add(UserFrame.getInstance().getParent());
		this.sceneContainer.getChildren().add(LoginFrame.getInstance().getParent());
		this.sceneContainer.getChildren().add(CogniticianFrame.getInstance().getParent());
	}
	
	public void setUserFrame() {
		Parent userFrameParent = UserFrame.getInstance().getParent();
		displayFrame(userFrameParent, this.sceneContainer);
	}
	
	public void setLoginFrame() {
		Parent loginFrameParent = LoginFrame.getInstance().getParent();
		displayFrame(loginFrameParent, this.sceneContainer);
	}
	
	public void setCogniticianFrame() {
		Parent cogniticianFrameParent = CogniticianFrame.getInstance().getParent();
		displayFrame(cogniticianFrameParent, this.sceneContainer);
	}
	
	private void displayFrame(Parent frame, StackPane mainScene) {
		mainScene.getChildren().forEach(child -> {
			if (child == frame) {
				child.setVisible(true);
			}
			else {
				child.setVisible(false);
			}
		});
	}

}
