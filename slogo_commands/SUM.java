package slogo_commands;

/**
 * @author Larissa
 * purpose: implements SUM command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;
import slogo_parsing.TreeNode;

public class SUM extends MathBoolCommand{

	@Override
	public String execute(List<String> params) {
		double sum = 0;
		for(int i=0; i<params.size(); i++){
			sum+=Double.parseDouble(params.get(i));
		}
		return String.valueOf(sum);
	}

}
