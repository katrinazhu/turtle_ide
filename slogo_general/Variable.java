package slogo_general;

import javafx.beans.property.SimpleStringProperty;
/**
 * @author Christopher Lu
 * Creates a Variable object for use in the TurtleData table that displays the name of the turtle property e.g. xPos, yPos, the value of the variable, and the expression that occured.
 */
public class Variable {

	private SimpleStringProperty Name;
	private SimpleStringProperty Value;
	private SimpleStringProperty Expression;
	
	public Variable(String name, String value, String expression)  {
		this.Name = new SimpleStringProperty(name);
		this.Value = new SimpleStringProperty(value);
		this.Expression = new SimpleStringProperty(expression);
	}
	
	public Variable(){}
	
	public void setName(String newValue) {
		this.Name.set(newValue);
	}
	
	public void setValue(String newValue) {
		this.Value.set(newValue);
	}
	
	public void setExpression(String newValue) {
		this.Expression.set(newValue);
	}
	
	public String getName() {
		return Name.get();
	}
	
	public String getValue() {
		return Value.get();
	}
	
	public String getExpression() {
		return Expression.get();
	}
	
}
