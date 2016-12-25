package slogo_commands;

/**
 * @author Larissa
 * purpose: implements MAKEUSERINSTRUCTION command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.UserDefinedCommand;
import slogo_parsing.TreeNode;

public class MAKEUSERINSTRUCTION extends UserDefinedCommand{

	@Override
	public String executeCommand(List<TreeNode> nodes, String command) {
		;
		;
		commandsLibrary.add(nodes.get(0).getValue().toUpperCase());
		userCommandLibrary.put(nodes.get(0).getValue().toUpperCase(), nodes.get(2));
		userCommandVars.put(nodes.get(0).getValue().toUpperCase(), nodes.get(1));
		return "1";
	}

}
