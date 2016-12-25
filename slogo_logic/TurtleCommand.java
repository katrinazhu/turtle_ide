package slogo_logic;

/**
 * @author Larissa
 * purpose: abstract class that defines a framework for the turtle commands
 * assumptions: assumes that turtle commands will all need to be evaluated using a post order traversal
 * dependencies: PostOrderTraversal
 * example: specifies a framework for the FORWARD command
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import slogo_parsing.TreeNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class TurtleCommand implements Command{
	private List<Turtle> myTurtles;
	private ObservableList<Turtle> observedTurtles;
	private List<Turtle> activeTurtles;
	private PostOrderTraversal traversalManager;
	
	/**
	 * creates new observable list of turtles containing turtle, the main turtle of the program, initializes a PostOrderTraversal using 
	 * the LogicManager that is passed in
	 */
	public void initialize(LogicManager logic, Turtle turtle, Map<String, String> varsLib, Map<String, TreeNode> userVars, Map<String, TreeNode> userCommands, List<String> commandsLib){
		traversalManager = new PostOrderTraversal(logic);
		myTurtles = new ArrayList<Turtle>();
		myTurtles.add(turtle);
		observedTurtles = FXCollections.observableList(myTurtles);
		activeTurtles = new ArrayList<Turtle>();
		activeTurtles.addAll(myTurtles);
	}

	protected abstract String execute(Turtle t, List<String> params); 
	
	/**
	 * calls executes the command tree whose parameters are passed in as nodes on each of the active turtles 
	 */
	public String executeCommand(List<TreeNode> nodes, String command) {
		String answer = "0";
		for (Turtle t: activeTurtles){
			answer = execute(t, traverseCommand(nodes));
		}
		return answer;
	}
	
	/**
	 * provides the subclasses access to the traverseCommand method of the PostOrderTraversal
	 * @param nodes - the list of node parameters that traverse command will evaluate and convert to a list of strings
	 * @return the list of evaluated string parameters
	 */
	private List<String> traverseCommand(List<TreeNode> nodes){
		return traversalManager.traverseCommand(nodes);
	}

}
