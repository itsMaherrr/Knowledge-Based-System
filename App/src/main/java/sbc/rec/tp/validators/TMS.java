package sbc.rec.tp.validators;

import java.util.ArrayList;

import sbc.rec.tp.models.Fact;
import sbc.rec.tp.models.KnowledgeBase;
import sbc.rec.tp.models.Rule;

public class TMS {
	
	/*
	private static ArrayList<Node> buildNetwork(KnowledgeBase kb) {
		ArrayList<Fact> factsBase = kb.getFacts();
		ArrayList<Rule> rulesBase = kb.getRules();
		
		for (Rule rule: rulesBase) {
			Node node = new Node();
			
		}
	}
	*/
	
	public TMS(KnowledgeBase kb) {
		
	}
	
	private ArrayList<Node> network;

	public ArrayList<Node> getNetwork() {
		return network;
	}

	public void setNetwork(ArrayList<Node> network) {
		this.network = network;
	} 

}