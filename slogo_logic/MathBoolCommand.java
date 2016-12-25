package slogo_logic;

/**
 * @author Larissa
 * purpose: abstract class that defines a framework for the math and boolean commands
 * assumptions: assumes that math and boolean commands will all need to be evaluated 
 * 	using a post order traversal
 * dependencies: PostOrderTraversal
 * example: specifies a framework for the SUM command
 */

import java.util.List;
import java.util.Map;

import slogo_parsing.TreeNode;

public abstract class MathBoolCommand implements Command{
	private PostOrderTraversal traversalManager;
	
	/**
	 * initializes the PostOrderTraversal class that will help the command evaluate it's tree
	 * 	using the LogicManager that is passed in
	 */
	public void initialize(LogicManager logic, Turtle turtles, Map<String, String> varsLib, Map<String, TreeNode> userVars, Map<String, TreeNode> userCommands, List<String> commandsLib){
		traversalManager = new PostOrderTraversal(logic);
	}

	/**
	 * calls execute on the traversed parameters of the tree
	 */
	public String executeCommand(List<TreeNode> nodes, String command){
		return execute(traverseCommand(nodes));
	}
	
	protected abstract String execute(List<String> params); 
	
	/**
	 * allows subclasses to access the traverseCommand method of the PostOrderTraversal
	 * @param nodes
	 * @return
	 */
	private List<String> traverseCommand(List<TreeNode> nodes){
		return traversalManager.traverseCommand(nodes);
	}
	
}
