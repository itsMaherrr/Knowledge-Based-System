package sbc.rec.tp.models;

import java.util.ArrayList;

public class Rule implements Cloneable {
	
	public Rule(ArrayList<Fact> premisses, Fact conclusion) {
		this.premisses = premisses;
		this.conclusion = conclusion;
		this.weight = 1;
	}
	
	public Rule(ArrayList<Fact> premisses, Fact conclusion, float weight) {
		this.premisses = premisses;
		this.conclusion = conclusion;
		this.weight = weight;
	}

	private ArrayList <Fact> premisses;
	private Fact conclusion;
	private float weight;
	
	// getters and setters
	public ArrayList <Fact> getPremisses() {
		return premisses;
	}
	
	public String getPremissesAsString() {
		String premisses = "";
		int premissesLength = getPremisses().size();
		for (int i = 0; i < premissesLength; i++) {
			premisses += getPremisses().get(i).getPredicatAsString();
			if (i < premissesLength - 1) {
				premisses += ", ";
			}
		}
		
		return premisses;
	}
	
	public String getConclusionAsString() {
		return getConclusion().getPredicatAsString();
	}
	
	public String getRuleAsString() {
		return getPremissesAsString() + " -> " + getConclusionAsString(); 
	}
	
	public void setPremisses(ArrayList <Fact> premisses) {
		this.premisses = premisses;
	}
	
	public Fact getConclusion() {
		return conclusion;
	}
	
	public void setConclusion(Fact conclusion) {
		this.conclusion = conclusion;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	private int findUnification(Fact fact) {
		for (Fact premisse: getPremisses()) {
			if (premisse.unifiableWith(fact)) {
				return premisse.findUnification(fact);
			}
		}
		return -1;
		
	}
	
	public Rule unify(Fact fact) throws CloneNotSupportedException {
		boolean canUnify = true;
		Rule unified = (Rule) this.clone();
		for (Fact premisse: unified.getPremisses()) {
			int i;
			if ((i = findUnification(fact)) != -1) {
				unified.unifyAll(premisse.getParameters().get(i).getValue(), fact.getParameters().get(i).getValue());
				return unified;
			}
		}
		return null;
	}
	
	public void unifyAll(String variable, String value) {
		for (Fact premisse: getPremisses()) {
			for(Parameter parameter: premisse.getParameters()) {
				if (parameter.getValue().equals(variable)){
					parameter.unify(value);
				}
			}
		}
		for (Parameter parameter: getConclusion().getParameters()) {
			if (parameter.getValue().equals(variable)){
				parameter.unify(value);
			}
		}
	}
	
}
