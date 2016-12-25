package slogo_commands;

/**
 * @author Larissa
 * purpose: implements EQUAL command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class EQUAL extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		double firstElem=Double.parseDouble(params.get(0));
		for(int i=1; i<params.size(); i++){
			if(Double.parseDouble(params.get(i))!=firstElem){
				return "0";
			}
		}
		return "1";
	}

}
