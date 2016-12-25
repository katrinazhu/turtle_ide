package slogo_commands;

/**
 * @author Larissa
 * purpose: implements HIDETURTLE command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class HIDETURTLE extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		t.setShowing(0);
		return "0";
	}

}
