package slogo_commands;

/**
 * @author Larissa
 * purpose: implements SETTOWARDS command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class SETTOWARDS extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		double newHeading = Math.atan((Double.parseDouble(params.get(0)) - t.getX())/(Double.parseDouble(params.get(1)) - t.getY()));
		double dHeading = newHeading - t.getDHeading();
		t.setHeading(dHeading, newHeading);
		return String.valueOf(t.getDHeading());
	}

}
