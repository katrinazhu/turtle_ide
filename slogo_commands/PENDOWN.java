package slogo_commands;

/**
 * @author Larissa
 * purpose: implements PENDOWN command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class PENDOWN extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		t.setPen(1);
		return "1";
	}

}
