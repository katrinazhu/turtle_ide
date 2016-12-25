package slogo_commands;

/**
 * @author Larissa
 * purpose: implements CLEARSCREEN command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class CLEARSCREEN extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		double dPosition = Math.sqrt(Math.pow(0 - t.getX(),2) + Math.pow(0 - t.getY(), 2));

		t.clearScreen();
		t.setPosition(0, 0, dPosition);
		t.setHeading(90 - t.getHeading(), 90);
		return String.valueOf(Math.round(dPosition));
	}

}
