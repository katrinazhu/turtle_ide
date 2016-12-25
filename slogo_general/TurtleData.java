package slogo_general;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import slogo_logic.Turtle;

import java.util.ResourceBundle;

/**
 * @author Christopher Lu
 * This class is responsible for creating the table of turtle data that provides a live, visual, numerical source of turtle information to the user that updates upon each expression.
 * The user will be able to see current turtle x and y positions, the heading of the turtle, the status of the turtle's pen and the status of the turtle show/hide.
 * The user will also be able to see the previous expressions.
 */

public class TurtleData extends TableView{
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myResources;
	private BorderPane root;
	private sLogoController controller;
	private Turtle turtle;
	private InputText userInput;
	private ObservableList<Variable> turtleVals;
	private TableView<Variable> table;
	private Variable xPos;
	private Variable yPos;
	private Variable Heading;
	private Variable PenStatus;
	private Variable Hide;
	private Variable Empty;
	private Variable Selected;
	
	public TurtleData(BorderPane root, sLogoController controller, InputText userInput, Turtle turtle) {
		this.root = root;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.turtle = turtle;
		this.controller = controller;
		this.userInput = userInput;
		this.turtleVals  = FXCollections.observableArrayList();
		this.table = new TableView<Variable>();
		table.setPrefHeight(controller.screenHeight*0.8);
		TableColumn<Variable, String> firstCol = new TableColumn<Variable, String>(myResources.getString("TurtleDataColumn1"));
		firstCol.setCellValueFactory(new PropertyValueFactory<Variable, String>(myResources.getString("TurtleDataColumn1")));
		TableColumn<Variable, String> secondCol = new TableColumn<Variable, String>(myResources.getString("TurtleDataColumn2"));
		secondCol.setCellValueFactory(new PropertyValueFactory<Variable, String>(myResources.getString("TurtleDataColumn2")));
		TableColumn<Variable, String> thirdCol = new TableColumn<Variable, String>(myResources.getString("TurtleDataColumn3"));
		thirdCol.setCellValueFactory(new PropertyValueFactory<Variable, String>(myResources.getString("TurtleDataColumn3")));
		table.getColumns().addAll(firstCol, secondCol, thirdCol);
		createTurtleData();
		this.root.setRight(this.table);
	}
	
	/**
	 * Creates overall layout, columns, column names for the table.
	 */
	public void createTurtleData() {
		xPos = new Variable(myResources.getString("XPosition"), Double.toString(turtle.getX()), userInput.getText());
		turtleVals.add(xPos);
		yPos = new Variable(myResources.getString("YPosition"), Double.toString(turtle.getY()), userInput.getText());
		turtleVals.add(yPos);
		Heading = new Variable(myResources.getString("Heading"), Double.toString(turtle.getHeading()), userInput.getText());
		turtleVals.add(Heading);
		PenStatus = new Variable(myResources.getString("PenStatus"), determinePen(), userInput.getText());
		turtleVals.add(PenStatus);
		Hide = new Variable(myResources.getString("TurtleShowing"), turtleHide(), userInput.getText());
		turtleVals.add(Hide);
		Empty = new Variable(myResources.getString("Col1Spacer"),myResources.getString("Col2Spacer"), myResources.getString("Col3Spacer"));
		turtleVals.add(Empty);
		table.setItems(turtleVals);
		Selected = table.getSelectionModel().getSelectedItem();
	}
	
	/**
	 * Converts the numerical values of 1 and 0 to string values of pen up and pen down for more user friendly information.
	 * @return
	 */
	 private String determinePen() {
	    	double penStatus = turtle.isPendown();
	    	;
	    	if (penStatus == 1.0) {
	    		return "down";
	    	}
	    	else {
	    		return "up";
	    	}
	    }
		 
	 /**
	  * Converts the numerical values of 1 and 0 to string values of showing and hiding for more user friendly information.
	  * @return
	  */
	    private String turtleHide() {
	    	double turtleShow = turtle.isShowing();
	    	if (turtleShow == 1.0) {
	    		return "showing";
	    	}
	    	else {
	    		return "hiding";
	    	}
	    }
	    
	    public Variable getSelected() {
	    	return Selected;
	    }
	
}
