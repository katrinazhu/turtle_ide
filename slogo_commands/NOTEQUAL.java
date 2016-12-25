package slogo_commands;

/**
 * @author Larissa
 * purpose: implements NOTEQUAL command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class NOTEQUAL extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		for(int i=0; i<params.size()-1; i++){
			for (int j=i+1; j<params.size(); j++){
				if(Double.parseDouble(params.get(i))==Double.parseDouble(params.get(j))){
					return "0";
				}
			}
		}
		return "1";
	}

}
