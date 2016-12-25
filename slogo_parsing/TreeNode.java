// This entire file is part of my masterpiece.
// Katrina Zhu
package slogo_parsing;
import java.util.ArrayList;
import slogo_parsing.ProgramParser;


/**
 * @author Katrina Zhu
 * TreeNode represents a command or a variable, creating an object with children, value, and typeCommand properties
 */
public class TreeNode {
	private int numChildren;
	private String value;
	private String typeCommand;
	private ArrayList<TreeNode>children;
	private ProgramParser syntax;
	private boolean grouped;
	
	/**
	 * Constructor for a blank TreeNode
	 */
	public TreeNode(){
		value=null;
		typeCommand=null;
		children=new ArrayList<TreeNode>();
		numChildren=100000;
		grouped=false;
	}
	
	/**
	 * Constructor for a TreeNode with data
	 * 
	 * @param lang the language in which the user is programming
	 * @param s the string that the user inputted representing a command, variable, constant, etc
	 */
	public TreeNode(ProgramParser lang, String s){
		syntax=new ProgramParser();
		syntax.addPatterns("resources/languages/Syntax");
		typeCommand=syntax.getSymbol(s);
		value=lang.getSymbol(s);
		if(value.equals("Constant")||value.equals("Variable")||value.equals("Command")){
			numChildren=0;
			value=s;
		}
		else{
			numChildren=lang.buildNumChildren().get(value);
		}
		grouped=false;
		children=new ArrayList<TreeNode>();		
	}
	/**
	 * Relates whether or not the node is grouped (for example, the sum node is grouped in ( sum 1 1 1 1 ) )
	 * @return a boolean reflecting whether the node is grouped
	 */
	public boolean isGrouped(){
		return grouped;
	}
	/**
	 * Setter in which the user can change whether or not a node is grouped
	 * @param g a boolean reflecting whether or not the node is grouped
	 */
	public void setGrouped(boolean g){
		grouped=g;
	}
	/**
	 * Getter for the type of the node
	 * @return the type of command/constant/variable of the node
	 */
	public String getTypeCommand(){
		return typeCommand;
	}
	/**
	 * Adds a node to this node's list of children
	 * @param n the node which the user wishes to add to this node's children
	 */
	public void addChildren(TreeNode n){
		children.add(n);
	}
	/**
	 * Getter for the number of children this node has
	 * @return the number of children of this node
	 */
	public int getNumChildren(){
		return numChildren;
	}
	/**
	 * Getter which returns the value of this node
	 * @return the node's value
	 */
	public String getValue(){
		return value;
	}
	/**
	 * Returns a list of the node's children
	 * @return an ArrayList of nodes of the node's children
	 */
	public ArrayList<TreeNode> getChildren(){
		return children;
	}
}
