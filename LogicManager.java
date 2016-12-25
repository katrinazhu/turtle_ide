package slogo_logic;

/**
 * @author Larissa
 * purpose: acts as the controller for the logic package, taking in a command tree
 * 	and using reflection to call the command class that it corresponds to
 * assumptions: assumes that the string command name has an associated command class 
 * 	or exists in the user defined commands library
 * dependencies: Turtle
 * example: takes in a SUM command tree and uses reflection to pass 
 * 	it's children to the SUM command class
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo_parsing.TreeNode;

public class LogicManager {
	private List<String> commandsLibrary = new ArrayList<String>(Arrays.asList("FORWARD", "BACKWARD", "LEFT", "RIGHT",
			"SETHEADING", "SETTOWARDS", "SETPOSITION", "PENDOWN", "PENUP", "SHOWTURTLE", "HIDETURTLE", "HOME",
			"CLEARSCREEN", "XCOORDINATE", "YCOORDINATE", "HEADING", "ISPENDOWN", "ISSHOWING", "SUM", "DIFFERENCE",
			"PRODUCT", "QUOTIENT", "REMAINDER", "MINUS", "RANDOM", "SINE", "COSINE", "TANGENT", "ARCTANGENT",
			"NATURALLOG", "POWER", "PI", "LESSTHAN", "GREATERTHAN", "EQUAL", "NOTEQUAL", "AND", "OR", "NOT",
			"MAKEVARIABLE", "MAKEUSERINSTRUCTION", "REPEAT", "DOTIMES", "FOR", "IF", "IFELSE", "STAMP", "CLEARSTAMPS"));
	private Map<String, String> varsLibrary;
	private Map<String, TreeNode> userDefVarsLibrary;
	private Map<String, TreeNode> userCommandLibrary;
	private Turtle myTurtle;

	public LogicManager(Turtle turtle) {
		this.myTurtle = turtle;
		varsLibrary = new HashMap<String, String>();
		userDefVarsLibrary = new HashMap<String, TreeNode>();
		userCommandLibrary = new HashMap<String, TreeNode>();
	}

	/**
	 * takes in a command tree head node and a list of it's children and uses the 
	 * 	type of the command node to determine what algorithm to use to evaluate the 
	 * 	command
	 * @param command - the node containing the name and type of the command
	 * @param children - the child nodes of the command node, which contain the parameters
	 * 	of the command
	 * @return the string value returned by the command
	 */
	public String executeLogic(TreeNode command, List<TreeNode> children) {
		String answer = "0";
		if (command.getTypeCommand().equals("Constant")) {
			answer = command.getValue();
		}
		if (command.getTypeCommand().equals("Variable")) {
			answer = varsLibrary.get(command.getValue().toUpperCase());
		}
		if (command.getTypeCommand().equals("ListStart")) {
			answer = executeChildren(command);
		}
		if (command.getTypeCommand().equals("Command")) {
			answer = executeReflection(command.getValue(), children);
		}
		return answer;
	}
	
	private String executeChildren(TreeNode command){
		String answer = "";
		for (TreeNode child : command.getChildren()) {
			answer = executeLogic(child, child.getChildren());
		}
		return answer;
	}

	private String executeReflection(String command, List<TreeNode> params) {
		Class<?> c = getClass(command.toUpperCase());
		Command instance = instantiateClass(c);
		instance.initialize(this, myTurtle, varsLibrary, userDefVarsLibrary, userCommandLibrary, commandsLibrary);
		return instance.executeCommand(params, command);
	}

	private Class<?> getClass(String command) {
		Class<?> c = null;
		try {
			if (userCommandLibrary.containsKey(command)){
				c = Class.forName("slogo_commands.EXECUTEUSERCOMMAND");
			} else {
				c = Class.forName("slogo_commands." + command);
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return c;
	}
	
	private Command instantiateClass(Class<?> command){
		Command instance = null;
		try {
			instance = (Command) command.newInstance();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
		return instance;
	}

	/**
	 * provides access to the variables of the workspace
	 * @return the map of variables to their values
	 */
	public Map<String, String> getUserVariables() {
		return varsLibrary;
	}

	/**
	 * provides access to the list of commands that are defined in the workspace
	 * @return list of string command names
	 */
	public List<String> getUserCommands() {
		return commandsLibrary;
	}

	/**
	 * provides access to the map of variables defined within user defined commands
	 * @return map of string command names to their node values
	 */
	public Map<String, TreeNode> getUserVarsLibrary(){
		return userDefVarsLibrary;
	}

}
