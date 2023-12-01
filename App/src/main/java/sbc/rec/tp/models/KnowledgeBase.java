package sbc.rec.tp.models;

import java.util.ArrayList;

import sbc.rec.tp.compiler.ParseTree;

public class KnowledgeBase {
		
	public static ArrayList<Fact> buildFactBase(ParseTree factsTree){
		ArrayList<Fact> factBase = new ArrayList<Fact>();
		factsTree.getAllNodesWithValue("PREDICAT").forEach(factTree -> {
			Fact fact = buildFact(factTree);
			factBase.add(fact);
		});
		
		return factBase;
	}
	
	public static ArrayList<Rule> buildRuleBase(ParseTree rulesTree) {
		ArrayList<Rule> ruleBase = new ArrayList<Rule>();
		ArrayList<ParseTree> rulesTrees = rulesTree.getAllNodesWithValue("REGLE");
		for (ParseTree ruleTree: rulesTrees) {
			Rule rule = buildRule(ruleTree);
			ruleBase.add(rule);
		}
		
		return ruleBase;
	}
	
	private static Rule buildRule(ParseTree ruleTree) {
		ArrayList<Fact> premisses = new ArrayList<Fact>();
		Fact conclusion;
		ruleTree.getAllNodesWithValue("PREDICAT").forEach(element -> {
			Fact predicat = buildFact(element);
			premisses.add(predicat);
		});
		int last = premisses.size() - 1;
		conclusion = premisses.remove(last);
		
		Rule rule = new Rule(premisses, conclusion);
		return rule;
	}
	
	private static Fact buildFact(ParseTree factTree) {
		String predicat = factTree.getAllNodesWithValue("identifier").get(0).readLeafs();
		ParseTree parametersTree = factTree.getAllNodesWithValue("PARAMETERS").get(0);
		ArrayList<Parameter> parameters = buildParameters(parametersTree);
		boolean negative = factTree.getFristChild().readLeafs().strip().equals("-");
				
		Fact fact = new Fact(predicat, parameters, negative);
		
		return fact;
	}
	
	private static ArrayList<Parameter> buildParameters(ParseTree parametersTree){
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		parametersTree.getAllNodesWithValue("PARAMETER").forEach(node -> {
			String value = node.readLeafs();
			String data = node.getFristChild().getValue().strip();
			char first = value.charAt(0);
			Parameter.Type type = switch (first) {
				case '_' -> Parameter.Type.ANY;
				default -> {
					if (Character.isUpperCase(first)) yield Parameter.Type.VARIABLE;
					else yield Parameter.Type.CONSTANT;
				}
			};
			Parameter.DataType dataType = switch(data) {
				case "identifier" -> Parameter.DataType.ALPHABETIC;
				default -> Parameter.DataType.NUMERIC;
			};
			
			Parameter parameter = new Parameter(value, type, dataType);
			parameters.add(parameter);
		});
		
		return parameters;
	}
	
	public KnowledgeBase(ArrayList <Fact> facts, ArrayList <Rule> rules) {
		this.facts =facts;
		this.rules = rules;
	}
	
	private ArrayList <Fact> facts;
	private ArrayList <Rule> rules;

	public ArrayList <Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList <Rule> rules) {
		this.rules = rules;
	}

	public ArrayList <Fact> getFacts() {
		return facts;
	}

	public void setFacts(ArrayList <Fact> facts) {
		this.facts = facts;
	}

}
