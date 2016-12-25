package slogo_commands;

/**
 * @author Larissa
 * purpose: implements MINUS command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class MINUS extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Double.parseDouble((params.get(0))) * -1);
	}

}
