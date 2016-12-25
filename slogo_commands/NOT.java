package slogo_commands;

/**
 * @author Larissa
 * purpose: implements NOT command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class NOT extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		if (Double.parseDouble((params.get(0))) == 0){
			return "1";
		}
		return "0";
	}

}
