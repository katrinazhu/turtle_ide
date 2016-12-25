package slogo_commands;

/**
 * @author Larissa
 * purpose: implements SETHEADING command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class SETHEADING extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		
		double heading = Double.parseDouble(params.get(0));
		double dHeading =  heading - t.getHeading();
		t.setHeading(dHeading, heading);
		return String.valueOf(t.getDHeading());
	}

}
