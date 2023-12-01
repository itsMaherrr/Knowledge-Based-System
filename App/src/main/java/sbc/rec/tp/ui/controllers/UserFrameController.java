package sbc.rec.tp.ui.controllers;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sbc.rec.tp.compiler.Grammar;
import sbc.rec.tp.compiler.LexicalAnalyzer;
import sbc.rec.tp.compiler.ParseTree;
import sbc.rec.tp.compiler.SyntacticAnalyzer;
import sbc.rec.tp.compiler.Token;
import sbc.rec.tp.ui.views.MainWindow;

public class UserFrameController {
	
	@FXML protected VBox conversationTab;
	@FXML protected JFXButton cogniticialLogin;
	
	
	public void init() {
		cogniticialLogin.setOnAction(event -> {
			MainWindow.getInstance().getController().setLoginFrame();
		});
	}

}
