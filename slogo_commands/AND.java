package slogo_commands;

/**
 * @author Larissa
 * purpose: implements AND command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class AND extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		boolean and = true;
		for(int i=0; i<params.size(); i++){
			and=and && (Double.parseDouble((params.get(i)))!=0);
		}
		if (and){
			return "1";
		}
		return "0";
	}

}
