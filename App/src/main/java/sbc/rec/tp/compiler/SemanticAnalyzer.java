package sbc.rec.tp.compiler;

import java.util.TreeSet;

public class SemanticAnalyzer {

	public SemanticAnalyzer() {}
	
	public static TreeSet <String> getDictionary() {
		return DICTIONARY;
	}

	private final static TreeSet <String> DICTIONARY = new TreeSet <String>() {{
		add("");
	}};
	
}
