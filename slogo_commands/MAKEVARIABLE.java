package slogo_commands;

/**
 * @author Larissa
 * purpose: implements MAKEVARIABLE command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.UserDefinedCommand;
import slogo_parsing.TreeNode;

public class MAKEVARIABLE extends UserDefinedCommand{

	@Override
	public String executeCommand(List<TreeNode> nodes, String command) {
		String expr = recursiveCall(nodes.get(1));
		varsLibrary.put(nodes.get(0).getValue().toUpperCase(), expr);
		;
		return expr;
	}

}
