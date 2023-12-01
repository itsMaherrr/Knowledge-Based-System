package sbc.rec.tp.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import sbc.rec.tp.models.Fact;
import sbc.rec.tp.models.Rule;

public class SemanticAnalyzer {

	public SemanticAnalyzer(ParseTree parseTree) {
		this.parseTree = parseTree;
	}
	
	private final static TreeSet <String> DICTIONARY = new TreeSet <String>() {{
		add("farine");
		add("levure");
		add("eau");
		add("sel");
		add("beurre");
		add("pate");
		add("sucre");
		add("pate_feuilletee");
		add("pain");
		add("oeufs");
		add("crêpes");
		add("omelette");
		add("huile");
		add("frites");
		add("frites");
		add("frittes_omelettes");
		add("sauce_tomate");
		add("fromage_rape");
		add("pate_a_la_bolognaise");
		add("riz");
		add("legumes");
		add("riz_au_legumes");
		add("confiture");
		add("pain_grille");
		add("avocat");
		add("tomates");
		add("oignon");
		add("jus_decitron");
		add("guacamole");
		add("chocolat_noire");
		add("fudge_au_chocolat");
		add("pommes");
		add("cannelle");
		add("tarte_aux_pommes");
		add("pate_a_pizza");
		add("tomate_en_conserve");
		add("olives");
		add("pizza");
		add("mozzarella");
		add("herbe_de_provence");
		add("pizza_margherita");
		add("lentilles");
		add("oignons");
		add("bouillon_de_poulet");
		add("soupe_a_lentilles");
		add("oeufs");
		add("zestes_de_citron");
		add("tarte_aux_citron");
		add("citron");
		add("saumon");
		add("saumon_grille");
		add("haricots_verts");
		add("haricots_verts_sautes");
		add("spaghettis");
		add("huile_d'olive");
		add("spaghettis_aux_tomates");
		add("poulet");
		add("haricot");
		add("mais");
		add("haricots_au_poulet");
		add("vanille");
		add("blanc_d'oeufs");
		add("cremefouetee");
		add("sucre_en_poudre");
		add("meringue");
		add("bouillon_de_legumes");
		add("tomate_en_conserve");
		add("oignion");
		add("carottes");
		add("pois_chiches");
		add("tomate_en_des");
		add("oignon_rouges");
		add("vinigrette");
		add("salade_de_pois_chiches");
		add("miel");
		add("limonade_maison");
		add("cafe");
		add("cappuccino");
		add("fraise");
		add("milk_shake");
		add("eau_gazeuse");
		add("menthe");
		add("mojito");
		add("banane");
		add("flocons_d'avoine");
		add("smoothie");
		add("curry_en_poudre");
		add("oignon");
		add("riz");
		add("curry_de_poulet");
		add("pates");
		add("oignon");
		add("pates_au_thon");
		add("pain_de_mie");
		add("croque_monsieur_poulet");
		add("viande");
		add("croque_monsieur_viande");
		add("lait");
		add("gruyere_rape");
		add("croque_monsieur_thon");
		add("pain_de_mie");
		add("croque_madame_poulet");
		add("thon_en_boîte");
		add("fromage");
		add("croque_madame_thon");
		add("vinaigrette");
		add("salade_cesar");
		add("the_noir");
		add("chai_latte");
		add("pommes_de_terre");
		add("poivron");
		add("gratin_de_pommes_de_terre");
		add("chou_fleur");
		add("gratin_de_chou_fleur");
		add("epinards");
		add("champignons");
		add("gruyere_rape");
		add("muscade");
		add("poivre");
		add("quiche_epinards_et_champignons");
		add("concombre");
		add("salade");
		add("oeufs");
		add("thon");
		add("haricots_verts");
		add("vinaigrette");
		add("salade_nicoise");
		add("feta");
		add("vinaigrette");
		add("salade_mediterraneenne");
	}};
	
	private final static TreeSet <String> PREDICATS = new TreeSet <String>() {{
		add("disponible");
		add("ingredient");
		add("superieur");
		add("recipe");
	}};
	
	private HashMap<String, Object> Semantic = new HashMap<String, Object>();
	private ParseTree parseTree;
	
	public HashMap<String, Object> getSemantic() {
		return Semantic;
	}

	public void setSemantic(HashMap<String, Object> semantic) {
		Semantic = semantic;
	}
	
	public static TreeSet <String> getDictionary() {
		return DICTIONARY;
	}
	
	public ParseTree getParseTree() {
		return parseTree;
	}

	public void setParseTree(ParseTree parseTree) {
		this.parseTree = parseTree;
	}
	
	public boolean checkLexic() {
		return false;
		
	}
	
	public boolean checkCoherence(Fact fact) {
		return false;
		
	}
	
	public boolean checkCoherence(Rule rule) {
		return false;
		
	}
	
	public ArrayList<Fact> buildFactsBase(ArrayList<ParseTree> factsNodes) {
		ArrayList<Fact> facts = new ArrayList<Fact>();		
		for (ParseTree factsNode : factsNodes) {
			String predicat = factsNode.readLeafs().split("\\(")[0].strip();
			ArrayList<String> parameters = new ArrayList<String>();
			factsNode.getAllNodesWithValue("PARAMETER").forEach(parameter -> {
				parameters.add(parameter.readLeafs().strip());
			});
			
			// donc, mena nkmel ndiro yverifier les types 3la 7sab la valeur ta3 l noeud père
			// yverifier tani m3a les dictionnaires et tout
		}
		return facts;
	}
	
}
