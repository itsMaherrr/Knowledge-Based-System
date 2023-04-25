package sbc.rec.tp.compiler;

public class Token {
	
	private String value;
	private TokenType type;
	
	public Token() {}
	
	public Token(String value, TokenType type) {
		this.setValue(value);
		this.setType(type);
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TokenType type) {
		this.type = type;
	}

}
