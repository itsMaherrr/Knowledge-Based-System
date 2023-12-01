package sbc.rec.tp.compiler;

import java.util.ArrayList;

public class ParseTree extends Node<String, ParseTree> {
	
	public ParseTree(String value) {
		super(value);
	}
	
	public ParseTree(String value, ParseTree parent){
        super(value);
        this.parent = parent;
    }
	
	private ParseTree parent;
	
	public ParseTree getParent() {
		return parent;
	}

	public void setParent(ParseTree parent) {
		this.parent = parent;
	}
	
	@Override
	public void setChildren(ArrayList<ParseTree> children) {
		children.forEach(child -> {
			child.setParent(this);
		});
		super.setChildren(children);
	}
	
	@Override
	public void appendChild(ParseTree child) {
		child.setParent(this);	
		super.appendChild(child);	
	}

	public String readLeafs() {
		if (this.isLeaf()) {
			return this.getValue() + " ";
		}
		else {
			String result = "";
			for (ParseTree child : this.getChildren()) {
				result += child.readLeafs();
			}
			return result;
		}
	}
	
	public boolean hasNextSibling() {
		return this.getParent().getChildren().indexOf(this) < this.getParent().getChildrenCount() - 1;
	}
	
	private boolean hasParent() {
		return this.getParent() != null;
	}
	
	public ParseTree getNextSibling() {
		if ((this.hasParent()) && (this.hasNextSibling())) {
			int siblingIndex = this.getParent().getChildren().indexOf(this) + 1;
			return this.getParent().getChildren().get(siblingIndex);
		}
		else if (this.hasParent()) {
			return this.getParent().getNextSibling();
		}
		else {
			return null;
		}
	}
	
	public ArrayList<ParseTree> getAllNodesWithValue(String value) {
		ArrayList<ParseTree> resultNodes = new ArrayList<ParseTree>();
		
		if (this.getValue().equals(value)) resultNodes.add(this);
		
		for (ParseTree child : this.getChildren()) {
			child.getAllNodesWithValue(value).forEach(node -> {
				resultNodes.add(node);
			});
		}
		
		return resultNodes;
		
	}
	
}
