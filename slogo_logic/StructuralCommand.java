package slogo_logic;

/**
 * @author Larissa
 * purpose: abstract class that defines a framework for the structural (if and loop) commands
 * assumptions: assumes that the structural commands do not need a post order traversal to be 
 * 	evaluated, assumes that all loops will follow the structure of the method create loop
 * dependencies: LogicManager
 * example: specifies a framework for the DOTIMES command
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo_parsing.TreeNode;

public abstract class StructuralCommand implements Command {
	protected LogicManager logicManager;
	protected Map<String, String> varsLibrary;
	private Map<String, String> savedVars;

	/**
	 * initializes pointers to the variables library in LogicManager and to LogicManager itself and initializes the saved variables map
	 */
	public void initialize(LogicManager logic, Turtle turtles, Map<String, String> varsLib, Map<String, TreeNode> userVars, Map<String, TreeNode> userCommands, List<String> commandsLib){
		savedVars = new HashMap<String, String>();
		logicManager = logic;
		varsLibrary = varsLib;
	}

	/**
	 * allows subclasses to evaluate their node parameters
	 * @param var - the variable node to be evaluated
	 * @param evaluatedVars - the list of evaluated variables to be modified
	 * 	and returned
	 * @return the modified evaluatedVars list
	 */
	protected List<String> evaluateNode(TreeNode var, List<String> evaluatedVars) {
		if (var.getTypeCommand().equals("ListStart")) {
			for (TreeNode child : var.getChildren()) {
				evaluatedVars.add(recursiveCall(child));
			}
		} else {
			evaluatedVars.add(recursiveCall(var));
		}
		return evaluatedVars;
	}

	/**
	 * allows subclasses to make recursive calls to LogicManager
	 * @param node - the node to be evaluated
	 * @return the string value returned from the evaluation of node
	 */
	protected String recursiveCall(TreeNode node) {
		return logicManager.executeLogic(node, node.getChildren());
	}

	@Override
	public abstract String executeCommand(List<TreeNode> nodes, String command);

	/**
	 * provides a template for the loop commands
	 * @param start - starting value of the variable that iterates through the loop
	 * @param end - the ending value of the variable that iterates through the loop
	 * @param inc - the amount that the variable that iterates through the loop is incremented each time
	 * @param varName - the name of the variable that iterates through the loop
	 * @param repeat - the head of the command tree to be evaluated each time through the loop
	 * @return the string value returned by the last iteration through the list
	 */
	protected String createLoop(double start, double end, double inc, String varName, TreeNode repeat) {
		if (varsLibrary.containsKey(varName.toUpperCase())) {
			savedVars.put(varName.toUpperCase(), varsLibrary.get(varName.toUpperCase()));
		}
		String answer = "0";
		for (double i = start; i <= end; i += inc) {
			varsLibrary.put(varName.toUpperCase(), String.valueOf(i));
			answer = recursiveCall(repeat);
		}
		varsLibrary.remove(varName);
		if (savedVars.containsKey(varName.toUpperCase())) {
			varsLibrary.put(varName.toUpperCase(), savedVars.get(varName.toUpperCase()));
		}
		return answer;
	}

}
