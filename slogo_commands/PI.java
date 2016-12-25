package slogo_commands;

/**
 * @author Larissa
 * purpose: implements PI command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class PI extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Math.PI);
	}

}
