package sbc.rec.tp.ui.controllers;

import java.io.BufferedReader
;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import sbc.rec.tp.compiler.Grammar;
import sbc.rec.tp.compiler.LexicalAnalyzer;
import sbc.rec.tp.compiler.ParseTree;
import sbc.rec.tp.compiler.SyntacticAnalyzer;
import sbc.rec.tp.compiler.Token;
import sbc.rec.tp.datasets.FactListItem;
import sbc.rec.tp.datasets.IngredientTableItem;
import sbc.rec.tp.datasets.RecipeTableItem;
import sbc.rec.tp.datasets.RuleListItem;
import sbc.rec.tp.models.Fact;
import sbc.rec.tp.models.KnowledgeBase;
import sbc.rec.tp.models.Parameter;
import sbc.rec.tp.models.Rule;
import sbc.rec.tp.reasoners.InferenceEngine;
import sbc.rec.tp.models.Parameter.DataType;
import sbc.rec.tp.models.Parameter.Type;
import sbc.rec.tp.ui.views.MainWindow;

public class CogniticianFrameController {
	
	@FXML protected VBox conversationTab;
	@FXML protected TextField kbPath, quantityField;
	@FXML protected JFXButton userLogin, addKB, kbBtn, testBtn, addIngredientBtn, clearIngredientBtn, chainingBtn, clearRecipe, startSearching;
	@FXML protected JFXComboBox<String> ingredientsList, recipesList, strategiesList;
	@FXML protected AnchorPane scene, kbTab, testTab, chainingTab;
	@FXML protected StackPane container;
	@FXML protected JFXCheckBox noGoal, explanation;
	@FXML protected TableView<RuleListItem> rulesList;
	@FXML protected TableView<FactListItem> factsList;
	@FXML protected TableView<IngredientTableItem> testIngerdients;
	@FXML protected TableView<RecipeTableItem> testResult;
	@FXML protected TableColumn ingredientColumn, quantityColumn, idColumn, recipeColumn;
	@FXML protected TableColumn idCell, premissesCell, conclusionCell, weightCell, factIdCell, factsCell;
	
	private File knowledgeBaseFile;
	private TreeSet<String> ingredients;
	private InferenceEngine inferenceEngine;
	private KnowledgeBase knowledgeBase;
	
	
	public void init() {
		addKB.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			knowledgeBaseFile = fileChooser.showOpenDialog(scene.getScene().getWindow());
			if (knowledgeBaseFile != null) {
				kbPath.setText(knowledgeBaseFile.getAbsolutePath());
				String kb = readFile(knowledgeBaseFile);
				compile(kb);
			}
		});
		userLogin.setOnAction(event -> {
			MainWindow.getInstance().getController().setUserFrame();
		});
		scene.setOnKeyPressed(event -> {
			scene.requestFocus();
		});
		kbBtn.setOnAction(event -> {
			container.getChildren().forEach(child -> {
				child.setVisible(false);
			});
			kbTab.setVisible(true);
		});
		testBtn.setOnAction(event -> {
			container.getChildren().forEach(child -> {
				child.setVisible(false);
			});
			testTab.setVisible(true);
		});
		chainingBtn.setOnAction(event -> {
			container.getChildren().forEach(child -> {
				child.setVisible(false);
			});
			chainingTab.setVisible(true);
		});
		addIngredientBtn.setOnAction(event -> {
			String ingredient = ingredientsList.getValue();
			String quantity = quantityField.getText();
			clearFields();
			addIngredient(ingredient, quantity);
		});
		clearIngredientBtn.setOnAction(event -> {
			clearFields();
			testIngerdients.getItems().clear();
		});
		noGoal.setOnAction(event -> {
			clearGoal();
			recipesList.setDisable(noGoal.isSelected());
		});
		startSearching.setOnAction(event -> {
			ArrayList<Fact> userFacts = buildUserFactList();
			inferenceEngine.forwardChaining(userFacts);
			/*
			ArrayList<Parameter> parameters = new ArrayList<Parameter>() {{ add(new Parameter("pate", Type.CONSTANT, DataType.ALPHABETIC)); }};
			Fact goal = new Fact("recipe", parameters);
			inferenceEngine.BackwardChaining(userFacts, goal);
			*/
		});
		clearRecipe.setOnAction(event -> {
			clearGoal();
			noGoal.setSelected(false);
			explanation.setSelected(false);
			setDefaultStrategy();
			testResult.getItems().clear();
		});
		initStrategies();
	}
	
	private ArrayList<Fact> chainForwardly() {
		return null;
		
	}
	
	private ArrayList<Fact> buildUserFactList(){
		ArrayList<Fact> userFacts = new ArrayList<Fact>();
		testIngerdients.getItems().forEach(item -> {
			String predicat = "ingredient";
			Parameter name = new Parameter(item.getName(), Type.CONSTANT, DataType.ALPHABETIC);
			Parameter quantity = new Parameter(item.getQuantity(), Type.CONSTANT, DataType.NUMERIC);
			ArrayList<Parameter> parameters = new ArrayList<Parameter>() {{
				add(name);
				add(quantity);
			}};
			Fact fact = new Fact(predicat, parameters);
			
			userFacts.add(fact);
		});
		
		return userFacts;
	}
	
	private void initStrategies() {
		strategiesList.getItems().add("Most Ingredients");
		strategiesList.getItems().add("Least Ingredients");
		strategiesList.getItems().add("Longest Process");
		strategiesList.getItems().add("Default");
		strategiesList.getItems().add("Most Common");
		
		setDefaultStrategy();
	}
	
	private void setDefaultStrategy() {
		strategiesList.getSelectionModel().select("Default");
	}
	
	private void addIngredient(String ingredient, String quantity) {
		IngredientTableItem item = new IngredientTableItem(ingredient, quantity);
		
		ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		testIngerdients.getItems().add(item);
	}
	
	private void clearGoal() {
		recipesList.getSelectionModel().clearSelection();
	}
	
	private void clearFields() {
		ingredientsList.getSelectionModel().clearSelection();
		quantityField.setText("");
	}
	
	private void setRuleList(ArrayList<Rule> ruleBase) {
		ArrayList<RuleListItem> ruleListItems = new ArrayList<RuleListItem>();
		int id = 1;
		for (Rule rule: ruleBase) {
			String premisses = rule.getPremissesAsString();
			String conclusion = rule.getConclusionAsString();
			float weight = rule.getWeight();
			
			RuleListItem item = new RuleListItem(id, premisses, conclusion, weight);
			ruleListItems.add(item);
			
			id++;
		}
		
		idCell.setCellValueFactory(new PropertyValueFactory<>("id"));
		premissesCell.setCellValueFactory(new PropertyValueFactory<>("premisses"));
		conclusionCell.setCellValueFactory(new PropertyValueFactory<>("conclusion"));
		weightCell.setCellValueFactory(new PropertyValueFactory<>("weight"));
		
		rulesList.setItems(FXCollections.observableList(ruleListItems));
	}
	
	private void setFactList(ArrayList<Fact> factsBase) {
		ArrayList<FactListItem> factListItems = new ArrayList<FactListItem>();
		int id = 1;
		for (Fact fact: factsBase) {
			String predicat = fact.getPredicatAsString();
			
			FactListItem item = new FactListItem(id, predicat);
			factListItems.add(item);
			
			id++;
		}
		
		factIdCell.setCellValueFactory(new PropertyValueFactory<>("id"));
		factsCell.setCellValueFactory(new PropertyValueFactory<>("fact"));
		
		factsList.setItems(FXCollections.observableList(factListItems));
	}
	
	private void setIngredientsList(TreeSet<String> ingredients) {
		for (String ingredient: ingredients) {
			ingredientsList.getItems().add(ingredient);
		}
	}
	
	private void setRecipesList(TreeSet<String> recipes) {
		for (String recipe: recipes) {
			recipesList.getItems().add(recipe);
		}
	}
	
	@SuppressWarnings("finally")
	private String readFile(File file) {
		String fileContent = "";
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			
			String line;
			while ((line = br.readLine()) != null) {
				fileContent += line + " ";
			}
			
			br.close();
			fileReader.close();
		}
		catch (IOException ex) {
			System.out.println("Couldn't read File !");
		}
		finally {
			return fileContent;
		}
	}
	
	private void compile(String knowledgeBase) {
		LexicalAnalyzer analyser = new LexicalAnalyzer();
		try {
			ArrayList<Token> tokens = analyser.analyze(knowledgeBase);
			/*
			tokens.forEach(token -> {
				System.out.println("value -> " + token.getValue() + " | type -> " + token.getType());
			});
			*/
			Grammar knowledgeBaseGrammar = new Grammar();
			SyntacticAnalyzer parser = new SyntacticAnalyzer(knowledgeBaseGrammar);
			try {
				int first = 0;
				
				ArrayList<String> predicatNames = new ArrayList<String>();
				ParseTree parseTree = parser.parse(tokens);
				
				ParseTree rules = parseTree.getAllNodesWithValue("REGLES").get(first);
				ParseTree facts = parseTree.getAllNodesWithValue("FAITS").get(first);
				
				ArrayList<Rule> rulesBase = KnowledgeBase.buildRuleBase(rules);
				ArrayList<Fact> factsBase = KnowledgeBase.buildFactBase(facts);
				
				initKB(factsBase, rulesBase);
				initIG(this.knowledgeBase);
				
				TreeSet<String> ingredients = getIngredientsSet(rulesBase);
				TreeSet<String> recipes = getRecipesSet(rulesBase);
				setIngredients(ingredients);
				
				setRuleList(rulesBase);
				setFactList(factsBase);
				setIngredientsList(ingredients);
				setRecipesList(recipes);
				/*
				parseTree.getAllNodesWithValue("PARAMETER").forEach(elem -> {
					System.out.println(elem.readLeafs().strip());
				});
				*/
				/*
				ArrayList<ParseTree> facts = parseTree.getAllNodesWithValue("FAIT");
				ArrayList<ParseTree> rules = parseTree.getAllNodesWithValue("REGLE");
				System.out.println(facts.size());
				System.out.println(rules.size());
				facts.forEach(fact -> {
					fact.getAllNodesWithValue("PARAMETER").forEach(node ->{
						System.out.println(node.readLeafs().replace(" ", ""));
					});
				});
				rules.forEach(rule -> {
					System.out.println(rule.readLeafs().replace(" ", ""));
				});
				*/
			}
			catch (Error ex) {
				ex.printStackTrace();
			}
		} catch (Error e) {
			e.printStackTrace();
			// system displays in red : Error occurred during lexical analysis, please check your question
		}
	}
	
	private void initKB(ArrayList<Fact> facts, ArrayList<Rule> rules) {
		this.knowledgeBase = new KnowledgeBase(facts, rules);
	}
	
	private void initIG(KnowledgeBase knowledgeBase) {
		this.inferenceEngine = new InferenceEngine(knowledgeBase);
	}
	
	public static TreeSet<String> getIngredientsSet(ArrayList<Rule> rules) {
		TreeSet<String> ingredients = new TreeSet();
		for (Rule rule: rules) {
			for (Fact fact: rule.getPremisses()) {
				for (Parameter parameter: fact.getParameters()) {
					if ((parameter.getType() == Parameter.Type.CONSTANT) && (parameter.getDataType() == Parameter.DataType.ALPHABETIC)) {
						ingredients.add(parameter.getValue().replace("_", " "));
					}
				}
			}
		}
		
		return ingredients;
	}
	
	public static TreeSet<String> getRecipesSet(ArrayList<Rule> rules) {
		TreeSet<String> recipes = new TreeSet();
		for (Rule rule: rules) {
			for (Parameter parameter: rule.getConclusion().getParameters()) {
				if ((parameter.getType() == Parameter.Type.CONSTANT) && (parameter.getDataType() == Parameter.DataType.ALPHABETIC)) {
					recipes.add(parameter.getValue().replace("_", " "));
				}
			}
		}
		
		return recipes;
	}

	public TreeSet<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(TreeSet<String> ingredients) {
		this.ingredients = ingredients;
	}

	public InferenceEngine getInferenceEngine() {
		return inferenceEngine;
	}

	public void setInferenceEngine(InferenceEngine inferenceEngine) {
		this.inferenceEngine = inferenceEngine;
	}

}
