package sbc.rec.tp.datasets;

public class RuleListItem {
	
	public RuleListItem(int id, String premisses, String conclusion, float weight) {
		this.id = id;
		this.premisses = premisses;
		this.conclusion = conclusion;
		this.weight = weight;
	}
	
	private int id;
	private String premisses;
	private String conclusion;
	private float weight;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPremisses() {
		return premisses;
	}
	
	public void setPremisses(String premisses) {
		this.premisses = premisses;
	}
	
	public String getConclusion() {
		return conclusion;
	}
	
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}

}
