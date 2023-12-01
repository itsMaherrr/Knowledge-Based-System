package sbc.rec.tp.compiler;

import java.util.Stack;

import static java.util.Map.entry;

import java.util.ArrayList;

public class SyntacticAnalyzer {
	
	public SyntacticAnalyzer(Grammar grammar) {
		this.grammar = grammar;
	}
	
	private Grammar grammar;

	public Grammar getGrammar() {
		return grammar;
	}

	public void setGrammar(Grammar grammar) {
		this.grammar = grammar;
	}
	
	private String [] mirrorRule(String [] rule) {
		String reversedRule = "";
		
		// writing the rule backwards
		for (String element : rule) {
			reversedRule = element + " " + reversedRule;
		}
		
		return reversedRule.split(" ");
	}
	
	/*
	private void derivate(Stack <String> derivationStack, Token token) {
		while (!derivationStack.lastElement().equals(token.getValue()) && 
				!derivationStack.lastElement().equals(token.getType().name().toLowerCase())) {
			String nonTerminal = derivationStack.pop();
			String [] parsingRule = grammar.getParsingRule(nonTerminal, token);
			String [] reversedParsingRule = mirrorRule(parsingRule);
			
			for (String rule : reversedParsingRule) {
				derivationStack.add(rule);
			}
			
		}
	}
	*/
	
	public ParseTree parse(ArrayList<Token> tokens) throws Error {
		int currentIndex = 0;
		Stack <String> derivationStack = new Stack <String>();
		ParseTree parseTree = new ParseTree("S");
		ParseTree subTree = parseTree;
		
		derivationStack.push("#");
		derivationStack.push("S");
		
		boolean endAnalysis = false;
		Token currentToken = tokens.get(currentIndex);
		while (!endAnalysis) {
			String stackTop = derivationStack.lastElement();
			// if we have reached a terminal in the top of stack
			if (grammar.getTerminals().contains(stackTop)) {
				if ((stackTop.equals("epsilon")) ||
						(stackTop.equals(currentToken.getValue())) || (stackTop.equals(currentToken.getType().name().toLowerCase()))) {
					
					// in case of epsilon, the current token remains unchanged
					if (!stackTop.equals("epsilon")) {
						subTree.appendChild(new ParseTree(currentToken.getValue(), subTree));
						currentIndex++;
						currentToken = tokens.get(currentIndex);
					}
					else {
						subTree.setValue("");
					}
					subTree = subTree.getNextSibling();
					derivationStack.pop();
				}
				else {
					throw new Error("Syntactic Error Occurred!");
				}
			}
			
			// in case the top element was not a terminal
			else {
				
				// in case it was a non terminal, we keep deriving
				if (grammar.getNonTerminals().contains(stackTop)) {
					String [] nextRule = grammar.getParsingRule(stackTop, currentToken);
					
					// if there is no applicable rule
					if (nextRule == null) {
						System.out.println(stackTop + ", " + currentToken.getValue() + ", " + currentToken.getType().name());
						throw new Error("Syntactic Error Occurred!");
					}
					
					// push the mirror of the right side of the rule else
					else {
						String [] mirroredNextRule = mirrorRule(nextRule);
						
						ArrayList<ParseTree> children = new ArrayList<ParseTree>();
						
						derivationStack.pop();
						
						for (String part : mirroredNextRule) {
							derivationStack.add(part);
							children.add(0, new ParseTree(part));
						}
						
						subTree.setChildren(children);
						
						subTree = subTree.getChildren().get(0);
					}
				}
				
				// in case it wasn't a terminal nor a non terminal, then it's a #
				else {
					
					// text is correct if the current token is # as well
					if (currentToken.getValue().equals("#")) {
						endAnalysis = true;
					}
					else {
						throw new Error("Syntactic Error Occurred!");
					}
				}
			}
		}
		
		// if no exception has been thrown
		return parseTree;
		
	}

}
