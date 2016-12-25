package slogo_commands;

/**
 * @author Larissa
 * purpose: implements BACKWARD command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class BACKWARD extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		double sum = 0;
		for (int i = 0; i < params.size(); i++) {
			double distance = Double.parseDouble(params.get(i));
			double x = t.getX() - Math.cos(Math.toRadians(t.getHeading())) * distance;
			double y = t.getY() - Math.sin(Math.toRadians(t.getHeading())) * distance;
			t.setPosition(x, y, distance);
			sum -= distance;
		}
		return String.valueOf(sum);
	}

}
