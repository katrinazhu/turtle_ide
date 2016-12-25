package slogo_commands;

/**
 * @author Larissa
 * purpose: implements EXECUTEUSERCOMMAND command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slogo_logic.UserDefinedCommand;
import slogo_parsing.TreeNode;

public class EXECUTEUSERCOMMAND extends UserDefinedCommand{
	private Map<String, String> savedVars = new HashMap<String, String>();
	
	@Override
	public String executeCommand(List<TreeNode> nodes, String command) {
		storeVariables(nodes, userCommandVars.get(command.toUpperCase()));
		String answer = recursiveCall(userCommandLibrary.get(command.toUpperCase()));
		restoreVariables();
		return answer;
	}
	
	private void storeVariables(List<TreeNode> nodes, TreeNode variableNode){
		for(int i = 0; i < nodes.size(); i++){
			if (varsLibrary.containsKey(variableNode.getChildren().get(i).getValue().toUpperCase())){
				savedVars.put(variableNode.getChildren().get(i).getValue().toUpperCase(), varsLibrary.get(variableNode.getChildren().get(i).getValue().toUpperCase()));
			}
			varsLibrary.put(variableNode.getChildren().get(i).getValue().toUpperCase(), recursiveCall(nodes.get(i)));
		}
	}
	
	private void restoreVariables(){
		for (String var: savedVars.keySet()){
			varsLibrary.put(var.toUpperCase(), savedVars.get(var.toUpperCase()));
		}
		savedVars.clear();
	}

}
