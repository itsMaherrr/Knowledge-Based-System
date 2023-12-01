package sbc.rec.tp.reasoners;

import java.util.ArrayList;

import sbc.rec.tp.datasets.RuleInferenceEngine;
import sbc.rec.tp.models.Fact;
import sbc.rec.tp.models.KnowledgeBase;

public class InferenceEngine {
	
	public InferenceEngine(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}
	
	private KnowledgeBase knowledgeBase;
	
	public static enum Strategy {
		MOST_PREMISSES, LEAST_PREMISSES, HIGHEST_COMPLEXITY, ORDER, WEIGHT, RECENTLY_DEDUCTED; 
	}

	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}
	
	public ArrayList<Fact> forwardChaining(ArrayList<Fact> userFacts) {
		ArrayList<Fact> deducedFacts = new ArrayList<Fact>();
		ArrayList<RuleInferenceEngine> ruleBase = RuleInferenceEngine.buildInferenceEngineRuleBase(this.knowledgeBase.getRules());
		ArrayList<Fact> factBase = this.knowledgeBase.getFacts();
		
		for (Fact fact: userFacts) {
			deducedFacts.add(fact);
		}
		for (Fact fact: factBase) {
			if (!deducedFacts.contains(fact)) {
				deducedFacts.add(fact);
			}
		}
		
		ForwardChaining chainer = new ForwardChaining(deducedFacts, ruleBase);
		
		return chainer.chain();
	}
	
	public boolean BackwardChaining(ArrayList<Fact> userFacts, Fact goal) {
		ArrayList<Fact> deducedFacts = new ArrayList<Fact>();
		ArrayList<RuleInferenceEngine> ruleBase = RuleInferenceEngine.buildInferenceEngineRuleBase(this.knowledgeBase.getRules());
		ArrayList<Fact> factBase = this.knowledgeBase.getFacts();
		
		for (Fact fact: userFacts) {
			deducedFacts.add(fact);
		}
		for (Fact fact: factBase) {
			if (!deducedFacts.contains(fact)) {
				deducedFacts.add(fact);
			}
		}
		
		BackwardChaining chainer = new BackwardChaining(factBase, ruleBase);
		
		chainer.checkGoal(goal);
		
		return true;
	}
	
	public boolean allDesactivated(ArrayList<RuleInferenceEngine> ruleBase) {
		boolean exist = true;
		for (RuleInferenceEngine rule: ruleBase) {
			if (!rule.isDesactivated()) {
				exist = false;
				break;
			}
		}
		
		return exist;
	}

}
