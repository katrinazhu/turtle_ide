// This entire file is part of my masterpiece.
// Katrina Zhu
package slogo_parsing;
import java.util.List;
import java.util.Map;
import slogo_logic.LogicManager;
import slogo_parsing.TreeNode;
import slogo_parsing.ProgramParser;


/**
 * @author Katrina Zhu
 * Builds the expression tree necessary to parse through commands and execute them in the correct order.
 */
public class BuildTree {
	private List<TreeNode> nodeList;
	private TreeNode head;
	private int numCommands;
	private int numCommandsChildrenFulfilled;
	private LogicManager logicManager;
	/**
	 * Constructor for BuildTree. Calls addNode() method to actually construct the tree with a blank node as the head.
	 * 
	 * @param nodeList a list of nodes that need to be constructed into a tree
	 * @param lang the ProgramParser object that determines the language
	 * @param logicManager an instance of the LogicManager class
	 */
	public BuildTree(List<TreeNode> nodeList, ProgramParser lang, LogicManager logicManager){        
		this.nodeList=nodeList;
		head=new TreeNode();
		numCommands=0;
		numCommandsChildrenFulfilled=0;
		this.logicManager=logicManager;
		addNode(head);
	}
	
	/**
	 * Getter that returns the list of nodes that have yet to be added to a tree.
	 * @return a list a nodes that have not been added to a tree
	 */
	public List<TreeNode> getNodeList(){
		return nodeList;
	}
	
	/**
	 * Getter that returns the head of the tree.
	 * @return the head of the tree
	 */
	public TreeNode getHead(){
		return head;
	}
	
	/**
	 * Indicates whether or not there was an error while building the tree
	 * @return if there was an error building the tree
	 */
	public boolean isError(){
		return !(numCommands==numCommandsChildrenFulfilled);
	}
	
	/**
	 * Method that recursively adds nodes to the current node to the tree.
	 * @param current TreeNode to which the addNode method adds subsequent nodes
	 */
	private void addNode(TreeNode current){
		TreeNode newNode = nodeList.get(0);
		nodeList=nodeList.subList(1, nodeList.size());
		TreeNode nextNewNode=new TreeNode();
		if(!nodeList.isEmpty()){
			nextNewNode=nodeList.get(0);				
		}
		int numChildren=newNode.getNumChildren();
		Map<String, TreeNode> userVarsLibrary=logicManager.getUserVarsLibrary();
		if(userVarsLibrary.containsKey(newNode.getValue().toUpperCase())){
			numChildren=userVarsLibrary.get(newNode.getValue().toUpperCase()).getChildren().size();
		}
		if(newNode.getTypeCommand().equals("Command") && numChildren!=0){
			numCommands++;
		}					
		if(newNode.getValue().equals("GroupStart") && !nodeList.isEmpty()){
			nextNewNode.setGrouped(true);
			addNode(current);
		}
		else if(!(newNode.getValue().equals("ListEnd"))){
			current.addChildren(newNode);	
		}
		while(numChildren>0 && !nodeList.isEmpty()){
			TreeNode tempNode=nodeList.get(0);
			if(tempNode.getValue().equals("GroupEnd")){
				nodeList=nodeList.subList(1, nodeList.size());
				newNode.setGrouped(false);
				numChildren=0;
			}
			else if(!tempNode.getTypeCommand().equals("ListEnd")){
				addNode(newNode);
				if(!newNode.isGrouped()){
					numChildren--;	
				}
			}
			else{
				nodeList=nodeList.subList(1, nodeList.size());
				numChildren=-1;
			}
			if(numChildren==0){
				numCommandsChildrenFulfilled++;
			}
		}	
	}
}
