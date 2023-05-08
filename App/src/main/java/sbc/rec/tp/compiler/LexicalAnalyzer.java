package sbc.rec.tp.compiler;

import java.util.ArrayList;

public class LexicalAnalyzer {
	
	private String text;
	
	public LexicalAnalyzer() {}
	
	public LexicalAnalyzer(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public ArrayList<Token> analyze(String text) throws Error {
		// Initializing tokens array 
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		text = text + " ";
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
					case 0 -> {
						switch (currentCharacter) {
							case ' ' -> {
								beginningIndex++;	// blank character is not the beginning of a token
								}
							case '(', ')' -> currentState = 10;
							case ':', ',', '.', ';' -> currentState = 20;
							case '-'-> currentState = 29;
							case '_' -> currentState = 39;
							case 'f' -> currentState = 55;
							case 'r' -> currentState = 64;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else if (isDigit(currentCharacter)) currentState = 49;
								else currentState = -1;
							}
						}
					}
					case 29 -> {
						switch (currentCharacter) {
							case '>'-> currentState = 30;
							default -> currentState = -1;
							
						}
					}
					case 39 -> {
						switch (currentCharacter) {
							case '_', '\'' -> currentState = 39;
							case ' ', ',', '.', ':', ';', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 49 -> {
						switch (currentCharacter) {
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 50;
							default -> {
								if (isDigit(currentCharacter)) currentState = 49;
								else currentState = -1;
							}
						}
					}
					case 55 -> {
						switch(currentCharacter) {
							case 'a' -> currentState = 56;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 56 -> {
						switch(currentCharacter) {
							case 'i' -> currentState = 57;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 57 -> {
						switch(currentCharacter) {
							case 't' -> currentState = 58;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
							
						}
					}
					case 58 -> {
						switch(currentCharacter) {
							case 's' -> currentState = 59;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 59 -> {
						switch(currentCharacter) {
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 60;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 64 -> {
						switch(currentCharacter) {
							case 'e' -> currentState = 65;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 65 -> {
						switch(currentCharacter) {
							case 'g' -> currentState = 66;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 66 -> {
						switch(currentCharacter) {
							case 'l' -> currentState = 67;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 67 -> {
						switch(currentCharacter) {
							case 'e' -> currentState = 68;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 68 -> {
						switch(currentCharacter) {
							case 's' -> currentState = 69;
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 40;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					case 69 -> {
						switch(currentCharacter) {
							case '_' -> currentState = 39;
							case ' ', ',', '.', ';', ':', ')', '(' -> currentState = 60;
							default -> {
								if (isAlphabetic(currentCharacter)) currentState = 39;
								else currentState = -1;
							}
						}
					}
					
				}
				i++;
				lexicalError = currentState == -1;
				finalState = currentState == 0 ? false : currentState % 10 == 0;
								
			}
			
			// Adding found token to tokens array
			if (finalState) {
				String tokenValue;
				TokenType tokenType;
				
				switch (currentState) {
					case 10 -> tokenType = TokenType.PARANTHESIS;
					case 20 -> tokenType = TokenType.PONCTUATION;
					case 30 -> tokenType = TokenType.ARROW;
					case 40 -> {
						i--;
						tokenType = TokenType.IDENTIFIER;
					}
					case 50 -> {
						i--;
						tokenType = TokenType.DIGIT;
					}
					case 60 -> {
						i--;
						tokenType = TokenType.KEYWORD;
					}
					default -> tokenType = null;	// technically, this case shouldn't be reached
				}
				
				tokenValue = text.subSequence(beginningIndex, i).toString();
				
				Token token = new Token(tokenValue, tokenType);
				
				tokens.add(token);
			}
			
			
		}
		
		if (lexicalError) {
			throw new Error("Lexical Error Encountered!");
		}
		
		Token endToken = new Token("#", TokenType.END);
		tokens.add(endToken);
		
		return tokens;
		
	}
	
	private boolean isAlphabetic(char c) {
		return Character.isAlphabetic(c);
	}
	
	private boolean isDigit(char c) {
		return Character.isDigit(c);
	}

}
