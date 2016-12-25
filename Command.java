package slogo_logic;

/**
 * @author Larissa
 * purpose: provides an interface that all command classes must implement
 * assumptions: assumes hat all commands have a list of TreeNode children, 
 * 	assumes that all implementations of Command should have access to the LogicManager
 * 	and variable and command libraries
 * dependencies: LogicManager
 * example: implemented by the abstract class MathBoolCommand in order to provide a framework
 * 	for the Math and Boolean command classes
 */

import java.util.List;
import java.util.Map;

import slogo_parsing.TreeNode;

public interface Command {

	/**
	 * Acts as the constructor for the class. Takes in every data structure that the class might need
	 * to have access to and stores pointers to the ones that the specific implementation will need. 
	 * @param logic - the LogicManager that instantiates the class
	 * @param turtle - the main tutle of the display
	 * @param varsLib - the map of string variable names to string versions of their values
	 * @param userVars - the map of string variable names to TreeNode versions of their values
	 * @param userCommands - the map of string command names of user defined commands to their TreeNode
	 * 	command trees 
	 * @param commandsLib - the set of all string command names

	 */
	public void initialize(LogicManager logic, Turtle turtle, Map<String, String> varsLib, Map<String, TreeNode> userVars, Map<String, TreeNode> userCommands, List<String> commandsLib);
	
	/**
	 * Executes the logic stored in the parameter nodes of the command tree that is passed in
	 * Uses recursive calls to LogicManager to evaluate the command tree according to the algorithm
	 * 	for the specific command
	 * @param nodes - the children of the command tree's head node, which store the parameters of the 
	 * 	method
	 * @param command - the string command name
	 * @return the String version of the command's return value
	 */
	public String executeCommand(List<TreeNode> nodes, String command);
	
}
