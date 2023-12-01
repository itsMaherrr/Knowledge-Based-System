package sbc.rec.tp.reasoners;

import java.util.ArrayList;

import sbc.rec.tp.datasets.RuleInferenceEngine;
import sbc.rec.tp.models.Fact;
import sbc.rec.tp.models.Rule;

public class ForwardChaining {
	
	private static ArrayList<String> metaKnowledgeFunctions = new ArrayList<String>() {{ add("superieur"); }};
	
	public ForwardChaining(ArrayList<Fact> factBase, ArrayList<RuleInferenceEngine> ruleBase) {
		this.factBase = factBase;
		this.ruleBase = ruleBase;
	}
	
	private ArrayList<Fact> factBase;
	private ArrayList<RuleInferenceEngine> ruleBase;
	
	public ArrayList<Fact> getFactBase() {
		return factBase;
	}
	
	public void setFactBase(ArrayList<Fact> factBase) {
		this.factBase = factBase;
	}
	
	public ArrayList<RuleInferenceEngine> getRuleBase() {
		return ruleBase;
	}

	public void setRuleBase(ArrayList<RuleInferenceEngine> ruleBase) {
		this.ruleBase = ruleBase;
	}
	
	private boolean isApplicableRule(RuleInferenceEngine rule) {
		boolean applicable = true;
		for (Fact premisse: rule.getRule().getPremisses()) {
			if (metaKnowledgeFunctions.contains(premisse.getPredicat().strip())) continue;
			boolean hasMatch = false;
			for (Fact fact: getFactBase()) {
				if ((premisse.matchPredicat(fact)) && (premisse.unifiableWith(fact))) {
					hasMatch = true;
					break;
				}
			}
			applicable = applicable && hasMatch;
			
			if (!applicable) {
				break;
			}
		}
		return applicable;
	}
	
	private ArrayList<RuleInferenceEngine> conflictSet(ArrayList<RuleInferenceEngine> rulesBase) {
		ArrayList<RuleInferenceEngine> conflictSet = new ArrayList<RuleInferenceEngine>();
		ArrayList<Rule> trace = new ArrayList<Rule>();
		
		for (RuleInferenceEngine rule: rulesBase) {
			if (!rule.isDesactivated() && isApplicableRule(rule)) {
				conflictSet.add(rule);
			}
		}
		
		return conflictSet;
	}
	
	public ArrayList<Fact> chain() {
		ArrayList<Fact> deduced = new ArrayList<Fact>();
		
		boolean keepChaining = true;
		
		while (keepChaining) {
			ArrayList<RuleInferenceEngine> conflictSet = conflictSet(ruleBase);
			
			if (conflictSet.size() == 0) {
				keepChaining = false;
			}
			else {
				conflictSet.forEach(rule -> {
					for (Fact fact: getFactBase()) {
						try {
							Rule tryRule = rule.getRule();
							while(tryRule.unify(fact) != null) {
								tryRule = rule.getRule().unify(fact);
							}
							if (!deduced.contains(fact)) {
								deduced.add(tryRule.getConclusion());
							}
							rule.desactivate();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
				});
			}
			for (Fact fact: deduced) {
				getFactBase().remove(fact);
			}
			
		}
		return deduced;
	}
}
