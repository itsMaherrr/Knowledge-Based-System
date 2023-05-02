package sbc.rec.tp.models;

import java.util.ArrayList;

public class Rule {
	
	public Rule(ArrayList<String> premisses, String conclusion) {
		this.premisses = premisses;
		this.conclusion = conclusion;
	}

	private ArrayList <String> premisses;
	private String conclusion;
	
	// getters and setters
	public ArrayList <String> getPremisses() {
		return premisses;
	}
	
	public void setPremisses(ArrayList <String> premisses) {
		this.premisses = premisses;
	}
	
	public String getConclusion() {
		return conclusion;
	}
	
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
}
