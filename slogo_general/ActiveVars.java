package slogo_general;

import javafx.beans.property.SimpleStringProperty;

public class ActiveVars {
	
	private SimpleStringProperty Name;
	private SimpleStringProperty Value;
	
	public ActiveVars(String name, String value)  {
		this.Name = new SimpleStringProperty(name);
		this.Value = new SimpleStringProperty(value);
	}
	
	public void setName(String newValue) {
		this.Name.set(newValue);
	}
	
	public void setValue(String newValue) {
		this.Value.set(newValue);
	}
	
	public String getName() {
		return Name.get();
	}
	
	public String getValue() {
		return Value.get();
	}
	
}
