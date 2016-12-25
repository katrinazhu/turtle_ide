package slogo_commands;

/**
 * @author Larissa
 * purpose: implements ISPENDOWN command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class ISPENDOWN extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		return String.valueOf(t.isPendown());
	}

}
