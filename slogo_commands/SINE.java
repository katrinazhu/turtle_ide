package slogo_commands;

/**
 * @author Larissa
 * purpose: implements SINE command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class SINE extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Math.sin(Double.parseDouble((params.get(0)))));
	}

}
