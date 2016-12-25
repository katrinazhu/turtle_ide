package slogo_logic;

/**
 * @author Larissa
 * purpose: abstract class that defines a framework for the user defined (make variable and make command) commands
 * assumptions: assumes that user defined commands will not need to be evaluated using a post order traversal
 * dependencies: LogicManager
 * example: specifies a framework for the MAKEVARIABE command
 */

import java.util.List;
import java.util.Map;

import slogo_parsing.TreeNode;

public abstract class UserDefinedCommand implements Command {
	protected Map<String, TreeNode> userCommandLibrary;
	protected Map<String, TreeNode> userCommandVars;
	protected Map<String, String> varsLibrary;
	protected List<String> commandsLibrary;
	protected LogicManager logicManager;

	/**
	 * stores pointers to the LogicManager and various libraries that are passed
	 * in
	 */
	public void initialize(LogicManager logic, Turtle turtles, Map<String, String> varsLib,
			Map<String, TreeNode> userVars, Map<String, TreeNode> userCommands, List<String> commandsLib) {
		logicManager = logic;
		varsLibrary = varsLib;
		userCommandVars = userVars;
		userCommandLibrary = userCommands;
		commandsLibrary = commandsLib;
	}

	/**
	 * gives subclasses access to the executeLogic method of the LogicManager in
	 * order to allow it to make recursive calls
	 * 
	 * @param node - the node on which the recursive call will be made
	 * @return the string returned by evaluating node
	 */
	protected String recursiveCall(TreeNode node) {
		return logicManager.executeLogic(node, node.getChildren());
	}

	@Override
	public abstract String executeCommand(List<TreeNode> nodes, String command);

}
