package slogo_commands;

/**
 * @author Larissa
 * purpose: implements QUOTIENT command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class QUOTIENT extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		double quotient=Double.parseDouble(params.get(0));
			for(int i=1; i<params.size(); i++){
				quotient/=Double.parseDouble(params.get(i));
			}
			return String.valueOf(quotient);
		}

}
