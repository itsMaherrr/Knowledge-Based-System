package sbc.rec.tp.compiler;

import java.util.ArrayList;

public class LexicalAnalyser {
	
	private String text;
	
	public LexicalAnalyser(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public ArrayList<Token> analyze(String text) throws Exception {
		// Initializing tokens array 
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		int textLength = text.length();
		boolean lexicalError = false;
		
		int i = 0;
		
		// For every token in the text, analyzing until an error is encountered or text is finished
		while ((i < textLength) && (!lexicalError)) {
			
			// Initial state = 0
			int currentState = 0;
			boolean finalState = false;
			// token beginning index
			int beginningIndex = i;
			
			// Analyzing for every letter in a token
			while ((!finalState) && (!lexicalError) && (i < textLength)) {
				char currentCharacter = text.charAt(i);
				
				switch (currentState) {
					case 0: {
						switch (currentCharacter) {
							case ' ': {
								beginningIndex++;	// blank character is not the beginning of a token
								}
							case '(': {currentState = 10;}
							case ')': {currentState = 10;}
							case ':': {currentState = 20;}
							case ',': {currentState = 20;}
							case '.': {currentState = 20;}
							case ';': {currentState = 20;}
							case '-': {currentState = 29;}
							default: {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else if (isDigit(currentCharacter)) currentState = 49;
								else currentState = -1;
							}
						}
					}
					case 29:{
						switch (currentCharacter) {
							case '>': {currentState = 30;}
							default : {currentState = -1;}
							
						}
					}
					case 39:{
						switch (currentCharacter) {
							case '_': {currentState = 39;}
							case ' ': {currentState = 40;}
							case ',': {currentState = 40;}
							case '.': {currentState = 40;}
							case ';': {currentState = 40;}
							case ':': {currentState = 40;}
							case ')': {currentState = 40;}
							case '(': {currentState = 40;}
							default: {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 49:{
						switch (currentCharacter) {
							case ' ': {currentState = 50;}
							case ',': {currentState = 50;}
							case '.': {currentState = 50;}
							case ';': {currentState = 50;}
							case ':': {currentState = 50;}
							case ')': {currentState = 50;}
							case '(': {currentState = 50;}
							default: {
								if (isDigit(currentCharacter)) currentState = 49;
								else currentState = -1;
							}
						}
					}
					
				}
				i++;
				lexicalError = currentState == -1;
				finalState = currentState == 10 || currentState == 20 || currentState == 30 || currentState == 40 || currentState == 50;
				
			}
			
			// Adding found token to tokens array
			if (finalState) {
				String tokenValue;
				TokenType tokenType;
				
				switch (currentState) {
					case 10:{
						tokenType = TokenType.PARANTHESIS;
					}
					case 20:{
						tokenType = TokenType.PONCTUATION;
					}
					case 30:{
						tokenType = TokenType.ARROW;
					}
					case 40:{
						i--;
						tokenType = TokenType.IDENTIFIER;
					}
					case 50:{
						i--;
						tokenType = TokenType.DIGIT;
					}
					default:{
						tokenType = null;	// technically, this case shouldn't be reached
					}
				}
				
				tokenValue = text.subSequence(beginningIndex, i).toString();
				
				Token token = new Token(tokenValue, tokenType);
				
				tokens.add(token);
			}
			
			
		}
		
		if (lexicalError) {
			throw new Error("Lexical Error Encountered!");
		}
		
		return tokens;
		
	}
	
	private boolean isAlphabetic(char c) {
		return Character.isAlphabetic(c);
	}
	
	private boolean isDigit(char c) {
		return Character.isDigit(c);
	}

}
