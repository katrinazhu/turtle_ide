package slogo_commands;

import java.util.List;

import slogo_logic.Turtle;
import slogo_logic.TurtleCommand;

public class CLEARSTAMPS extends TurtleCommand{

	@Override
	protected String execute(Turtle t, List<String> params) {
		boolean hasStamps=false;
		if(t.isStamped()==1){
			hasStamps=true;
		}
		t.setStamp(0);
		if(hasStamps){
			return "1";
		}
		return "0";
	}

}
