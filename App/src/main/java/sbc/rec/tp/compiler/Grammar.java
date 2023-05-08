package sbc.rec.tp.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Grammar {
	
	private ArrayList <String> terminals = new ArrayList <String> () {{
		add("faits");
		add("regles");
		add("identifier");
		add("digit");
		add("->");
		add(":");
		add(".");
		add(",");
		add("(");
		add(")");
		add("epsilon");
	}};
	
	private ArrayList <String> nonTerminals = new ArrayList <String> () {{
		add("S");
		add("SBC");
		add("FAITZ");
		add("FAITS");
		add("FAITS'");
		add("PARAMETERS");
		add("PARAMETERS'");
		add("PARAMETER");
		add("REGLES");
		add("REGLES'");
		add("REGLE");
		add("PREDICATS");
		add("PREDICATS'");
		add("PREDICAT");
	}};
	
	// initializing parsing table
	private Map<String, Map<String, String>> parsingTable = new HashMap<String, Map<String, String>>() {{
		
		put("S", Map.of("faits", "SBC", "#", "epsilon"));
		put("SBC", Map.of("faits", "faits : FAITZ regles : REGLES"));
		put("FAITZ", Map.of("regles", "epsilon", "identifier", "FAITS"));
		put("FAITS", Map.of("identifier", "PREDICAT . FAITS'"));
		put("FAITS'", Map.of("regles", "epsilon", "identifier", "FAITS"));
		put("PREDICAT", Map.of("identifier", "identifier ( PARAMETERS )"));
		put("PARAMETERS", Map.of("identifier", "PARAMETER PARAMETERS'", "digit", "PARAMETER PARAMETERS'"));
		put("PARAMETERS'", Map.of(")", "epsilon", ",", ", PARAMETERS"));
		put("PARAMETER", Map.of("identifier", "identifier", "digit", "digit"));
		put("REGLES", Map.of("identifier", "REGLE . REGLES'"));
		put("REGLES'", Map.of("#", "epsilon", "identifier", "REGLES"));
		put("REGLE", Map.of("identifier", "PREDICATS -> PREDICAT"));
		put("PREDICATS", Map.of("identifier", "PREDICAT PREDICATS'"));
		put("PREDICATS'", Map.of(",", ", PREDICATS", "->", "epsilon"));
		
	}};
	
	public ArrayList <String> getTerminals() {
		return terminals;
	}

	public void setTerminals(ArrayList <String> terminals) {
		this.terminals = terminals;
	}

	public ArrayList <String> getNonTerminals() {
		return nonTerminals;
	}

	public void setNonTerminals(ArrayList <String> nonTerminals) {
		this.nonTerminals = nonTerminals;
	}

	
	public Map<String, Map<String, String>> getParsingTable() {
		return this.parsingTable;
	}
	
	public void setParsingTable(Map<String, Map<String, String>> parsingTable) {
		this.parsingTable = parsingTable;
	}
	
	public String [] getParsingRule(String nonTerminal, Token nextToken) {
		// return the rule meeting the value of the token if it's not null, else return the value meeting its type, if both are null, return null
		return this.parsingTable.get(nonTerminal).get(nextToken.getValue()) != null ?
				this.parsingTable.get(nonTerminal).get(nextToken.getValue()).split(" ")
			: this.parsingTable.get(nonTerminal).get(nextToken.getType().name().toLowerCase()) != null ?
				this.parsingTable.get(nonTerminal).get(nextToken.getType().name().toLowerCase()).split(" ") : 
					null;
	}

}