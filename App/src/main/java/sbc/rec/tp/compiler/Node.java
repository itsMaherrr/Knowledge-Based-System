package sbc.rec.tp.compiler;

import java.util.ArrayList;

public abstract class Node <ValueType, ChildrenType> {
	
	public Node(ValueType value) {
		this.value = value;
		this.children = new ArrayList<ChildrenType>();
	}
	
	protected ValueType value;
	protected ArrayList<ChildrenType> children;
	
	public ValueType getValue() {
		return value;
	}
	
	public void setValue(ValueType value) {
		this.value = value;
	}
	
	public ArrayList<ChildrenType> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<ChildrenType> children) {
		this.children = children;
	}
	
	public ChildrenType getFristChild() {
		int first = 0;
		if (hasChildre()) {
			return getChildren().get(first);
		}
		return null;
	}
	
	public int getChildrenCount() {
		return this.children.size();
	}
	
	public boolean hasChildre() {
		return this.getChildrenCount() > 0;
	}
	
	public void appendChild(ChildrenType child) {
		this.children.add(child);
	}
	
	public boolean isLeaf() {
		return this.children.size() == 0;
	}

}
