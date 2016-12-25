package slogo_commands;

/**
 * @author Larissa
 * purpose: implements RANDOM command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class RANDOM extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Math.random() * Double.parseDouble((params.get(0))));
	}

}
