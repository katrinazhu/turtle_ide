package slogo_commands;

/**
 * @author Larissa
 * purpose: implements REMAINDER command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class REMAINDER extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Double.parseDouble((params.get(0))) % Double.parseDouble((params.get(1))));
	}
}
