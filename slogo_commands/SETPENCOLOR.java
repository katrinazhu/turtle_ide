package slogo_commands;

/**
 * @author Larissa
 * purpose: implements SETPENCOLOR command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class SETPENCOLOR extends TurtleCommand{

	@Override
	protected String execute(Turtle t, List<String> params) {
		t.setPenColor(Integer.parseInt(params.get(0)));
		return params.get(0);
	}

	
	
}
