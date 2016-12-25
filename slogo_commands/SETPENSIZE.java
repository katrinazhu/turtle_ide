package slogo_commands;

/**
 * @author Larissa
 * purpose: implements SETPENSIZE command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class SETPENSIZE extends TurtleCommand{

	@Override
	protected String execute(Turtle t, List<String> params) {
		t.setPenSize(Double.parseDouble(params.get(0)));
		return params.get(0);
	}

	
	
}
