package sbc.rec.tp.explicator;

import java.util.ArrayList;

import sbc.rec.tp.models.Rule;

public class Explicator {
	
	public Explicator (ArrayList<Rule> trace) {
		this.setTrace(trace);
	}
	
	private ArrayList<Rule> trace;

	public ArrayList<Rule> getTrace() {
		return trace;
	}

	public void setTrace(ArrayList<Rule> trace) {
		this.trace = trace;
	}
	
}
