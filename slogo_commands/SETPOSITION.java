package slogo_commands;

/**
 * @author Larissa
 * purpose: implements SETPOSITION command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class SETPOSITION extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		double dPosition = Math.sqrt(Math.pow(Double.parseDouble(params.get(0))-t.getX(),2) + Math.pow(Double.parseDouble(params.get(1))-t.getY(), 2));
		t.setPosition(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1)), dPosition);
		return String.valueOf(dPosition);
	}

}
