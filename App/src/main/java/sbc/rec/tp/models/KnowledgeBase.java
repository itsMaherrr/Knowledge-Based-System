package sbc.rec.tp.models;

import java.util.ArrayList;

public class KnowledgeBase {
	
	public KnowledgeBase(ArrayList <Rule> rules) {
		this.rules = rules;
	}
	
	private ArrayList <Rule> rules;

	public ArrayList <Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList <Rule> rules) {
		this.rules = rules;
	}

}
