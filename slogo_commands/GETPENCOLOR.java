package slogo_commands;

/**
 * @author Larissa
 * purpose: implements GETPENCOLOR command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class GETPENCOLOR extends TurtleCommand{

	@Override
	protected String execute(Turtle t, List<String> params) {
		return String.valueOf(t.getPenColor());
	}

}
