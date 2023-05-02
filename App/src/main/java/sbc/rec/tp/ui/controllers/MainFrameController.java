package sbc.rec.tp.ui.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sbc.rec.tp.compiler.Grammar;
import sbc.rec.tp.compiler.LexicalAnalyser;
import sbc.rec.tp.compiler.SyntacticAnalyser;
import sbc.rec.tp.compiler.Token;

public class MainFrameController extends FrameController {
	
	@FXML protected Button sendButton;
	@FXML protected VBox conversationTab;
	@FXML protected TextField questionField;
	
	@Override
	public void init(Stage stage) {
		super.init(stage);
		sendButton.setOnAction(event -> {
			submitQuestion();
		});
		questionField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				submitQuestion();
			}
		});
	}
	
	private String getQuestion() {
		return questionField.getText();
	}
	
	private void emptyQuestionField() {
		questionField.clear();
	}
	
	private void submitQuestion() {
		String message = getQuestion();
		emptyQuestionField();
		compile(message);
	}
	
	private void compile(String message) {
		LexicalAnalyser analyser = new LexicalAnalyser();
		try {
			ArrayList<Token> tokens = analyser.analyze(message);
			tokens.forEach(token -> {
				System.out.println("value -> " + token.getValue() + " | type -> " + token.getType());
			});
			
			Grammar firstOrderLogicGrammar = new Grammar();
			SyntacticAnalyser parser = new SyntacticAnalyser(firstOrderLogicGrammar);
			try {
				if (parser.parse(tokens)) {
					System.out.println("Syntactically good");
				}
			}
			catch (Error ex) {
				ex.printStackTrace();
			}
		} catch (Error e) {
			e.printStackTrace();
			// system displays in red : Error occurred during lexical analysis, please check your question
		}
	}

}
