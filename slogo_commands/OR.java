package slogo_commands;

/**
 * @author Larissa
 * purpose: implements OR command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class OR extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		boolean or=false;
		for(int i=0; i<params.size(); i++){
			or=or || (Double.parseDouble((params.get(i)))!=0);
		}
		if (or){
			return "1";
		}
		return "0";
	}

}
