package slogo_commands;

/**
 * @author Larissa
 * purpose: implements COSINE command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class COSINE extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		return String.valueOf(Math.cos(Double.parseDouble((params.get(0)))));
	}

}
