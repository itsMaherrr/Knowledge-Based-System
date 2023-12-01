package sbc.rec.tp.models;

import java.util.ArrayList;

public class Fact implements Cloneable {
		
	public Fact (String predicat, ArrayList<Parameter> parameters) {
		this.predicat = predicat;
		this.parameters = parameters;
		this.negative = false;
	}
	
	public Fact (String predicat, ArrayList<Parameter> parameters, boolean negative) {
		this.predicat = predicat;
		this.parameters = parameters;
		this.negative = negative;
	}
	
	private String predicat;
	private ArrayList<Parameter> parameters;
	private boolean negative;
	
	public String getPredicat() {
		return predicat;
	}
	
	public void setPredicat(String predicat) {
		this.predicat = predicat;
	}

	public ArrayList<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public int getParametersCount() {
		return getParameters().size();
	}

	public boolean isNegative() {
		return negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}
	
	public String getPredicatAsString() {
		String predicat = getPredicat();
		String parameters = "";
		int parametersLength = getParameters().size();
		for (int i = 0; i < parametersLength; i++) {
			parameters += getParameters().get(i).getValue();
			if (i < parametersLength - 1) {
				parameters += ", ";
			}
		}
		
		return predicat + "(" + parameters + ")";
	}
	
	public boolean matchPredicat(Fact fact) {
		return getPredicat().strip().equals(fact.getPredicat().strip());
	}
	
	public boolean unifiableWith(Fact fact) {
		boolean unifiable = true;
		for (int i = 0; i < getParametersCount(); i++) {
			Parameter parameter = getParameters().get(i);
			Parameter factParameter = fact.getParameters().get(i);
			if ((!parameter.equals(factParameter)) && (!parameter.canUnify())) {
				unifiable = false;
				break;
			}
		}
		
		return unifiable;
	}
	
	public int findUnification(Fact fact) {
		for (int i = 0; i < getParametersCount(); i++) {
			Parameter parameter = getParameters().get(i);
			Parameter factParameter = fact.getParameters().get(i);
			if (parameter.canUnify()) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean equal(Fact fact) {
		boolean equal = true;
		equal = getPredicat().equals(fact.getPredicat()) && isNegative() == fact.isNegative();
		System.out.println(getPredicatAsString() + ", and : " +fact.getPredicatAsString() + ", are "+ equal);
		if (equal) {
			for (int i = 0; i < getParametersCount(); i++) {
				if (!getParameters().get(i).equals(fact.getParameters().get(i))) {
					System.out.println(getParameters().get(i));
					equal = false;
					break;
				}
			}
		}
		
		return equal;
	}
	
	public boolean opposite(Fact fact) {
		boolean equal = true;
		equal = getPredicat().equals(fact.getPredicat());
		if (equal) {
			for (int i = 0; i < getParametersCount(); i++) {
				if (!getParameters().get(i).equals(fact.getParameters().get(i))) {
					equal = false;
					break;
				}
			}
		}
		
		return equal && isNegative() != fact.isNegative();
	}
	
}
