package slogo_commands;

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class STAMP extends TurtleCommand{

	@Override
	protected String execute(Turtle t, List<String> params) {
		t.setStamp(1);
		return "0";
	}

}
