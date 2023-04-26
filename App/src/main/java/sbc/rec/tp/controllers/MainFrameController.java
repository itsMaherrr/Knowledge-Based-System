package sbc.rec.tp.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sbc.rec.tp.compiler.LexicalAnalyser;
import sbc.rec.tp.compiler.Token;

public class MainFrameController extends FrameController {
	
	@FXML protected Button sendButton;
	@FXML protected VBox conversationTab;
	@FXML protected TextField questionField;
	
	@Override
	public void init(Stage stage) {
		super.init(stage);
		sendButton.setOnAction(event -> {
			String message = getQuestion();
			emptyQuestionField();
			compile(message);
		});
	}
	
	private String getQuestion() {
		return questionField.getText();
	}
	
	private void emptyQuestionField() {
		questionField.clear();
	}
	
	private void compile(String message) {
		LexicalAnalyser analyser = new LexicalAnalyser();
		try {
			ArrayList<Token> tokens = analyser.analyze(message);
			// DO something with tokens
		} catch (Exception e) {
			// system displays in red : Error occurred during lexical analysis, please check your question
		}
	}

}
