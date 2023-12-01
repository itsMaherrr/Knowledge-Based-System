package sbc.rec.tp.validators;

import java.util.ArrayList;

public class Justification {
	
	public Justification() {
		this.in = new ArrayList<Node>();
		this.out = new ArrayList<Node>();
	}
	
	public Justification(ArrayList<Node> in, ArrayList<Node> out) {
		this.in = in;
		this.out = out;
	}

	private ArrayList<Node> in;
	private ArrayList<Node> out;
	
	public ArrayList<Node> getIn() {
		return in;
	}
	
	public void setIn(ArrayList<Node> in) {
		this.in = in;
	}
	
	public ArrayList<Node> getOut() {
		return out;
	}
	
	public void setOut(ArrayList<Node> out) {
		this.out = out;
	}
	
	public boolean isValid() {
		boolean valid = true;
		for (Node node: in) {
			if (node.getState() == Node.State.OUT) {
				valid = false;
				break;
			}
		}
		if (!valid) {
			return valid;
		}
		else {
			for (Node node: out) {
				if (node.getState() == Node.State.IN) {
					valid = false;
					break;
				}
			}
		}
		return valid;
	}

}
