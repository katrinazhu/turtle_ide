package slogo_commands;

/**
 * @author Larissa
 * purpose: implements DOTIMES command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.StructuralCommand;
import slogo_parsing.TreeNode;

public class DOTIMES extends StructuralCommand{

	@Override
	public String executeCommand(List<TreeNode> nodes, String command) {
		return createLoop(1,
				Double.parseDouble((recursiveCall(nodes.get(0).getChildren().get(1)))),
				1, 
				nodes.get(0).getChildren().get(0).getValue(),
				nodes.get(1));
	}


}
