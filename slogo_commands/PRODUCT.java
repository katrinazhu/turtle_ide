package slogo_commands;

/**
 * @author Larissa
 * purpose: implements PRODUCT command
 * dependencies: abstract class MathBoolCommand and interface Command
 */

import java.util.List;

import slogo_logic.MathBoolCommand;

public class PRODUCT extends MathBoolCommand{

	@Override
	protected String execute(List<String> params) {
		double product = 1;
		for(int i = 0; i<params.size(); i++){
			product*=Double.parseDouble(params.get(i));
		}
		return String.valueOf(product);
	}

}
