package sbc.rec.tp.datasets;

public class RecipeTableItem {
	
	public RecipeTableItem(int id, String recipe) {
		this.id = id;
		this.recipe = recipe;
	}

	private int id;
	private String recipe;
	
	public String getRecipe() {
		return recipe;
	}
	
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
