package sbc.rec.tp.ui.controllers;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sbc.rec.tp.ui.views.MainWindow;

public class LoginFrameController {
	
	@FXML protected TextField usernameField;
	@FXML protected PasswordField passwordField;
	@FXML protected JFXButton goUser, loginBtn;
	@FXML protected AnchorPane scene, loginPane;

	public void init() {
		scene.setOnKeyPressed(event -> {
			scene.requestFocus();
		});
		
		loginPane.setOnKeyPressed(event -> {
			loginPane.requestFocus();
		});
		
		loginBtn.setOnAction(event -> {
			String username = this.usernameField.getText();
			String password = this.passwordField.getText();
			System.out.println(password);
			clearFields();
			//check validity
			if ((username.equals("admin")) && (password.equals("admin"))) {
				MainWindow.getInstance().getController().setCogniticianFrame();
			}
		});
		
		goUser.setOnAction(event -> {
			MainWindow.getInstance().getController().setUserFrame();
		});
	}
	
	private void clearUsernameField() {
		this.usernameField.setText("");
	}
	
	private void clearPasswordField() {
		this.passwordField.setText("");
	}
	
	private void clearFields() {
		clearUsernameField();
		clearPasswordField();
	}

}
