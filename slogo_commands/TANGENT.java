package slogo_commands;

/**
 * @author Larissa
 * purpose: implements TANGENT command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class TANGENT extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Math.tan(Double.parseDouble((params.get(0)))));
	}

}
