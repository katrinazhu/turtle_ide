package slogo_commands;

/**
 * @author Larissa
 * purpose: implements LEFT command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class LEFT extends TurtleCommand {

	@Override
	protected String execute(Turtle t, List<String> params) {
		double sum = 0;
		for (int i = 0; i < params.size(); i++) {
			double degrees = Double.parseDouble(params.get(i));
			double heading = t.getHeading() + degrees;
			while (heading > 360){
				heading -= 360;
			}
			t.setHeading(degrees, heading);
			sum += degrees;
		}
		return String.valueOf(sum);
	}

}
