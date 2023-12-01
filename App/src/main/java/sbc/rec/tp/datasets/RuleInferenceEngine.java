package sbc.rec.tp.datasets;

import java.util.ArrayList;

import sbc.rec.tp.models.Rule;

public class RuleInferenceEngine {
	
	public static ArrayList<RuleInferenceEngine> buildInferenceEngineRuleBase(ArrayList<Rule> ruleBase) {
		ArrayList<RuleInferenceEngine> igRuleBase = new ArrayList<RuleInferenceEngine>();
		ruleBase.forEach(rule -> {
			igRuleBase.add(new RuleInferenceEngine(rule));
		});
		
		return igRuleBase;
	}
	
	public RuleInferenceEngine(Rule rule) {
		this.rule = rule;
		this.desactivated = false;
	}

	private Rule rule;
	private boolean desactivated;
	
	public Rule getRule() {
		return rule;
	}
	
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	
	public boolean isDesactivated() {
		return desactivated;
	}
	
	public void setDesactivated(boolean desactivated) {
		this.desactivated = desactivated;
	}
	
	public void desactivate() {
		setDesactivated(true);
	}

}
