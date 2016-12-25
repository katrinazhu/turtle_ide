package slogo_commands;

/**
 * @author Larissa
 * purpose: implements REPEAT command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.ArrayList;
import java.util.List;

import slogo_logic.StructuralCommand;
import slogo_parsing.TreeNode;

public class REPEAT extends StructuralCommand{

	@Override
	public String executeCommand(List<TreeNode> nodes, String command) {
		String answer = "0";
		double endNum = Double.parseDouble(recursiveCall(nodes.get(0)));
		varsLibrary.put(":repcount", "1");
		for (int i = 1; i <= endNum; i++) {
			answer = evaluateNode(nodes.get(1), new ArrayList<String>()).get(0);
			varsLibrary.put(":repcount", String.valueOf(i));
		}
		varsLibrary.remove(":repcount");
		return answer;
	}

}
