package slogo_commands;

/**
 * @author Larissa
 * purpose: implements NATURALLOG command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class NATURALLOG extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Math.log(Double.parseDouble((params.get(0)))));
	}

}
