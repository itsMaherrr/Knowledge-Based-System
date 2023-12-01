package sbc.rec.tp.reasoners;

import java.util.ArrayList;

import sbc.rec.tp.datasets.RuleInferenceEngine;
import sbc.rec.tp.models.Fact;
import sbc.rec.tp.models.Rule;

public class BackwardChaining {
	
	public BackwardChaining(ArrayList<Fact> factBase, ArrayList<RuleInferenceEngine> ruleBase) {
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
	
	private ArrayList<RuleInferenceEngine> conflictSet(ArrayList<RuleInferenceEngine> rulesBase, Fact conclusion) {
		ArrayList<RuleInferenceEngine> conflictSet = new ArrayList<RuleInferenceEngine>();
		ArrayList<Rule> trace = new ArrayList<Rule>();
		
		for (RuleInferenceEngine rule: rulesBase) {
			if ((!rule.isDesactivated()) && (rule.getRule().getConclusion().equal(conclusion))) {			
				conflictSet.add(rule);
			}
		}
		
		return conflictSet;
	}
	
	public boolean checkGoal(Fact goal) {
		ArrayList<RuleInferenceEngine> conflictSet = conflictSet(ruleBase, goal);

		return true;
	}

}
