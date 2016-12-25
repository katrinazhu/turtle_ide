package slogo_commands;

/**
 * @author Larissa
 * purpose: implements FOR command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.StructuralCommand;
import slogo_parsing.TreeNode;

public class FOR extends StructuralCommand{

	@Override
	public String executeCommand(List<TreeNode> nodes, String command) {
		return createLoop(
				Double.parseDouble(recursiveCall(nodes.get(0).getChildren().get(1))),
				Double.parseDouble(recursiveCall(nodes.get(0).getChildren().get(2))),
				Double.parseDouble(recursiveCall(nodes.get(0).getChildren().get(3))),
				nodes.get(0).getChildren().get(0).getValue(),
				nodes.get(1));
	}

}
