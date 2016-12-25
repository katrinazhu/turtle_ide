package slogo_logic;

/**
 * @author Larissa
 * purpose: recursively traverses the command tree using a depth first, post 
 * 	order traversal
 * assumptions: 
 * dependencies: LogicManager
 * example: in the command fd sum 50 fd 100, the traversal evaluates fd 100 first, 
 * 	then uses the 100 returned from that to evaluate sum 50 100, then uses the 150
 * 	returned from that to evaluate fd 150
 */


import java.util.ArrayList;
import java.util.List;

import slogo_parsing.TreeNode;

public class PostOrderTraversal{
	private LogicManager logicManager;
	
	public PostOrderTraversal(LogicManager logic){
		logicManager = logic;
	}
	
	/**
	 * evaluates the given list of nodes and adds the string values returned
	 * 	by this evaluation to a list of strings
	 * @param nodes - the list of node parameters to be evaluated
	 * @return the list of string values returned by the node evaluation
	 */
	public List<String> traverseCommand(List<TreeNode> nodes) {
		ArrayList<String> evaluatedVars = new ArrayList<String>();
		for (int i = 0; i < nodes.size(); i++) {
			evaluateNode(nodes.get(i), evaluatedVars);
		}
		return evaluatedVars;
	}
	
	protected List<String> evaluateNode(TreeNode var, List<String> vars) {
		if (var.getTypeCommand().equals("ListStart")) {
			for (TreeNode child : var.getChildren()) {
				vars.add(recursiveCall(child));
			}
		}
		else{
			vars.add(recursiveCall(var));
		}
		return vars;
	}
	
	protected String recursiveCall(TreeNode var){
		return logicManager.executeLogic(var, var.getChildren());
	}

}
