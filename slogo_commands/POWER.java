package slogo_commands;

/**
 * @author Larissa
 * purpose: implements POWER command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class POWER extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Math.pow(Double.parseDouble(((String)params.get(0))), Double.parseDouble((params.get(1)))));
	}

}
