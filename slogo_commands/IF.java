package slogo_commands;

/**
 * @author Larissa
 * purpose: implements IF command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.StructuralCommand;
import slogo_parsing.TreeNode;

public class IF extends StructuralCommand{

	@Override
	public String executeCommand(List<TreeNode> nodes, String command) {
		;
		;
		if (recursiveCall(nodes.get(0)).equals("0")) {
			return recursiveCall(nodes.get(1));
		}
		return "0";
	}

}
