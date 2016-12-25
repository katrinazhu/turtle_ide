package slogo_general;

import java.util.ResourceBundle;

import slogo_general.sLogoController;
import slogo_logic.Turtle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.beans.binding.*;
import javafx.collections.ObservableList;

/**
 * TurtleStat is called by the GridController when the user enters the simulation environment. It displays some basic turtle stats, such as (x,y) position, 
 * the orientation of the turtle, and the pen status.
 * @author Christopher Lu
 */

public class TurtleStat {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
		
	private BorderPane root;
	private sLogoController controller;
	private HBox status;
    private ResourceBundle myResources;
    private ObservableList<Variable> turtleVals;
	private Text xPos = new Text();
	private Text yPos = new Text();
	private Text heading = new Text();
	private Text penStatus = new Text();
    private Text turtleShowing = new Text();
	
    public TurtleStat() {}
    
    public TurtleStat(BorderPane root, sLogoController controller, ObservableList<Variable> turtleVals) {
    	this.root = root;
    	this.controller = controller;
    	this.status = new HBox(20);
    	this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
    	this.turtleVals = turtleVals;
    	getStatus();
    	this.status.setAlignment(Pos.CENTER);
    	BorderPane.setAlignment(this.status, Pos.CENTER);
    	this.root.setPadding(new Insets(10, 10, 10, 10));
       	this.root.setTop(this.status);
    }
    
    /**
     * This method, which is called by the costructor, creates all the text elements displaying information about the turtle and pen to the user.
     */
    public void getStatus() {
    	xPos.setFont(new Font(20));
    	yPos.setFont(new Font(20));
    	heading.setFont(new Font(20));
    	penStatus.setFont(new Font(20));
    	turtleShowing.setFont(new Font(20));
    	xPos.setText(turtleVals.get(0).getName() + turtleVals.get(0).getValue());
    	yPos.setText(turtleVals.get(1).getName() + turtleVals.get(1).getValue());
    	heading.setText(turtleVals.get(2).getName() + turtleVals.get(2).getValue());
    	penStatus.setText(turtleVals.get(3).getName() + turtleVals.get(3).getValue());
    	turtleShowing.setText(turtleVals.get(4).getName() + turtleVals.get(4).getValue());
    	status.getChildren().add(xPos);
    	status.getChildren().add(yPos);
    	status.getChildren().add(heading);
    	status.getChildren().add(penStatus);
    	status.getChildren().add(turtleShowing);
    }
    
    /**
     * Converts numerical value of penStatus to corresponding string value.
     * @param penStatus
     * @return
     */
    private String determinePen(Turtle turtle) {
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
     * Converts numerical value of turtleShow to corresponding string value.
     * @param turtleShow
     * @return
     */
    private String turtleHide(Turtle turtle) {
    	double turtleShow = turtle.isShowing();
    	if (turtleShow == 1.0) {
    		return "showing";
    	}
    	else {
    		return "hiding";
    	}
    }
    
    /**
     * Updates TurtleStat after every "turn".
     */
    public void updateStatus(Turtle turtle) {
    	xPos.setText("xPos: " + Double.toString(turtle.getX()));
    	yPos.setText("yPos: " + Double.toString(turtle.getY()));
    	heading.setText("heading: " + Double.toString(turtle.getDHeading()));
    	penStatus.setText("Pen Status: " + determinePen(turtle));
    	turtleShowing.setText("Turtle: " + turtleHide(turtle));
    }
    
}
