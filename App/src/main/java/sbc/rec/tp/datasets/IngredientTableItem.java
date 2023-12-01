package sbc.rec.tp.datasets;

public class IngredientTableItem {
	
	public IngredientTableItem(String name, String quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	private String name;
	private String quantity;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
