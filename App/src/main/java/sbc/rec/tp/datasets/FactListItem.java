package sbc.rec.tp.datasets;

public class FactListItem {
	
	public FactListItem(int id, String fact) {
		this.id = id;
		this.fact = fact;
	}
	
	private int id;
	private String fact;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFact() {
		return fact;
	}
	
	public void setFact(String fact) {
		this.fact = fact;
	}

}
