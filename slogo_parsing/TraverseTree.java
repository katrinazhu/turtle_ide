package slogo_parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import slogo_general.ErrorBox;
import slogo_logic.LogicManager;

/**
 * @author Katrina Zhu
 * TraverseTree is responsible for taking text from InputText and calling BuildTree, traversing through the tree, and executing its commands.
 */
public class TraverseTree {
    private static final String WHITESPACE = "\\s+";
    private static final String NEWLINE="[\\r\\n]+";
    private static final String EMPTYSTRING="^$";
	private List<TreeNode>nodeList;
	private ProgramParser lang;
	private boolean isError;
	private LogicManager logicManager;
	
	/**
	 * Constructor for TraverseTree.
	 * 
	 * @param lang an instance of ProgramParser determining the language.
	 * @param logicManager an instance of LogicManager.
	 */
	public TraverseTree(ProgramParser lang, LogicManager logicManager){
		this.lang=lang;
		isError=false;
		this.logicManager=logicManager;
	}
	
	/**
     * This method is responsible for passing the text value in the TextArea to the parser and is also the main source of error checking.
     * If the user's expression is empty, this method displays an error box with an error prompting the user to type in a valid expression.
     * If the command does not exist, this method displays an error box with an error informing the user that the command does not exist.
     * If the command is incorrectly formatted, this method displays an error box with an error informing the user that the command is incorrectly formatted.
     * If the command is correctly formatted and exists, this method moves forward with executing the command.
     * 
     * @param input the text that the user input
     */
    public boolean executeTextActions(String input){
    	boolean executed=false;
    	if (input.equals("")) {
			ErrorBox.displayError("Please enter a valid expression");
		}
		else {
			if(commandDoesNotExist(input.toUpperCase())){           				
				ErrorBox.displayError("Command does not exist");
			}
			else{
				buildNodeList(input);
				while(!nodeList.isEmpty()){
	    			TreeNode headNode=makeTree(input);
	    			if(isError){
	    				ErrorBox.displayError("Commands are incorrectly formatted");
	    			}
	    			else{
	        			doCommand(headNode);
	        			executed=true;
	    			}
				}
			}
		}
    	return executed;
	}
    /**
     * This method sets the lang value with which the program should read commands
     * @param lang the desired new language in which the commands should be read
     */
    public void setLang(ProgramParser lang){
    	this.lang=lang;
    }
    
    /**
     * This method determines whether or not a given command string contains commands or variables that the language does not recognize
     * @param s a string of commands 
     * @return a boolean determining whether or not the command string has errors
     */
    private boolean commandDoesNotExist(String s){
    	String[]commands=commandsFromString(s);
    	List<String> userCommands = logicManager.getUserCommands();
    	Map<String, String> userVariables = logicManager.getUserVariables();
    	boolean makingNewInput = false;
    	for(int i=0; i<commands.length; i++){
			if(lang.getSymbol(commands[i]).equals("MakeUserInstruction") || lang.getSymbol(commands[i]).equals("MakeVariable") 
					|| lang.getSymbol(commands[i]).equals("For") || lang.getSymbol(commands[i]).equals("DoTimes")
					|| lang.getSymbol(commands[i]).equals("Repeat")){
				makingNewInput=true;
			}
    		if(!(userCommands.contains(commands[i]) || userVariables.containsKey(commands[i]))){
        		if(lang.getSymbol(commands[i]).equals("NO MATCH") || lang.getSymbol(commands[i]).equals("Command")
        				|| lang.getSymbol(commands[i]).equals("Variable")){
        			if(!makingNewInput){
        				return true;
        			}
        		}
    		}
    	}
    	return false;
    }
    /**
     * Builds nodeList based on the user inputted string
     * @param input the user inputted string
     */
    private void buildNodeList(String input){
        ArrayList<String> nodes=arrayToList(commandsFromString(input));
		nodeList=new ArrayList<TreeNode>();
		for(int i=0; i<nodes.size(); i++){
			nodeList.add(new TreeNode(lang, nodes.get(i)));
		}
    }
    
    /**
     * This method builds a tree given an input text, and returns the tree's head
     * @param input the input text that the user typed
     * @return the head node of the tree that was built
     */
    private TreeNode makeTree(String input) {
        BuildTree tree=new BuildTree(nodeList, lang, logicManager);
        nodeList= tree.getNodeList();
        isError=tree.isError();
        return tree.getHead();
    }
    
    /**
     * Given the headnode of a tree, executes commands for all its children
     * 
     * @param headNode the head node of the tree of commands
     */
    private void doCommand(TreeNode headNode){
		ArrayList<TreeNode> children=headNode.getChildren();
		for(int i=0; i<children.size(); i++){
			execute(children.get(i));
		}
    }
    
    /**
     * Executes the command for a given node
     * @param node the node representing the command one wishes to carry out
     * @return the node representing the data of what the command has returned
     */
    private TreeNode execute(TreeNode node){
    	return new TreeNode(lang, logicManager.executeLogic(node, node.getChildren()));
    }

    /**
     * Returns a string array of commands given a user's inputted text, removing blank or commented lines.
     * @param s the user input text
     * @return a string array of commands and variables
     */
    private String[] commandsFromString(String s){
    	String[]lines=s.split(NEWLINE);
        String correctedFile="";
        for(int j=0; j<lines.length; j++){
        	if(!(lines[j].contains("#")) && !lines[j].matches(EMPTYSTRING)){
        		correctedFile=correctedFile+lines[j] + " "; 
        	}
        }
        return correctedFile.split(WHITESPACE);        
    }
    
    /**
     * This method returns of ArrayList of strings given an array of strings
     * @param text array of strings
     * @return an ArrayList of strings
     */
    private ArrayList<String> arrayToList (String[] text) {
    	ArrayList<String> nodes=new ArrayList<String>();
        for (String s : text) {
            if (s.trim().length() > 0) {
                nodes.add(s);
            }
        }
        return nodes;
    }

}
