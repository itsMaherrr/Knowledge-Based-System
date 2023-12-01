package sbc.rec.tp.validators;

import java.util.ArrayList;

import sbc.rec.tp.models.Fact;
import sbc.rec.tp.models.Rule;

public class Node {

	/*
	public static Node buildConclusionNode(Rule rule) {
		
	}
	
	public static Node buildFactNode(Fact fact) {
		
	}
	*/
	
	public Node(String name, Type type, State state, ArrayList<Justification> justifications) {
		this.name = name;
		this.type = type;
		this.state = state;
		this.justifications = justifications;
	}
	
	public Node(String name, Type type) {
		this.name = name;
		this.type = type;
		this.state = State.OUT;
		this.justifications = new ArrayList<Justification>() {{ add(new Justification()); }};
	}
	
	private String name;
	private Type type;
	private State state;
	private ArrayList<Justification> justifications;
	
	public static enum Type {
		HYPOTHESE, PREMISSE, CONCLUSION, CONFLICT;
	}
	
	public static enum State {
		IN, OUT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ArrayList<Justification> getJustifications() {
		return justifications;
	}

	public void setJustifications(ArrayList<Justification> justifications) {
		this.justifications = justifications;
	}
	
	

}
