package slogo_logic;

/**
 * @author Larissa
 * purpose: class that stores and manipulates the data of the Turtle to be displayed and uses
 * 	observable structure in order to relay that information to the display
 * assumptions: assumes that observer class will correctly observe changes and display turtle
 * example: when fd 50 is called, setPosition is called in such a way that moves the turtle 50 steps in the direction of
 * 	its heading
 */

import java.util.Observable;

public class Turtle extends Observable{
	private double myX;
	private double myY;
	private double dPosition;
	private double myHeading;
	private double dHeading;
	private int pendown;
	private int showing;
	private boolean clearTail;
	private int backgroundColor;
	private int penColor;
	private double penSize;
	private int turtleShape;
	private int stamped;
	
	
	public Turtle(int x, int y, double heading){
		myX = x;
		myY = y;
		myHeading = 90;
		pendown = 1;
		showing = 1;
		clearTail = false;
		stamped=0;
	}
	
	public Turtle(){
		this(0,0,0);
	}
	
	/**
	 * getter for x coordinate
	 */
	public double getX(){
		return myX;
	}
	
	/**
	 * getter for y coordinate
	 */
	public double getY(){
		return myY;
	}
	
	/**
	 * getter for most recent change in position
	 */
	public double getDPosition(){
		return dPosition;
	}
	
	/**
	 * getter for turtle heading
	 */
	public double getHeading(){
		return myHeading;
	}
	
	/**
	 * getter for most recent change in heading
	 */
	public double getDHeading(){
		return dHeading;
	}

	/**
	 * returns true if the turtle's pen is down
	 */
	public double isPendown(){
		return pendown;
	}
	
	public double isStamped(){
		return stamped;
	}
	
	/**
	 * returns true if the turtle is showing
	 */
	public double isShowing(){
		return showing;
	}
	
	/**
	 * returns true if tail is cleared
	 */
	public boolean isClearTail() {
		return clearTail;
	}
	
	/**
	 * returns the index of the current background color
	 */
	public int getBackgroundColor(){
		return backgroundColor;
	}
	
	/**
	 * returns the index of the current pen color
	 */
	public int getPenColor(){
		return penColor;
	}
	
	/**
	 * returns the current pen size
	 */
	public double getPenSize(){
		return penSize;
	}
	
	/**
	 * returns the index of the turtle's current shape
	 */
	public int getTurtleShape(){
		return turtleShape;
	}
	
	/**
	 * sets background color index to color
	 * @param color - index of color to be set as background
	 */
	public void setBackgroundColor(int color){
		backgroundColor = color;
	}
	
	/**
	 * sets pen color index to color
	 * @param color - index of color to be set as pen color
	 */
	public void setPenColor(int color){
		penColor = color;
	}
	
	/**
	 * sets the size of the pen to size
	 * @param size - the new size for pen
	 */
	public void setPenSize(double size){
		penSize = size;
	}
	
	/**
	 * sets the shape index to shape
	 * @param shape - index of the shape to be set as turtle shape
	 */
	public void setTurtleShape(int shape){
		turtleShape = shape;
	}
	
	/**
	 * sets the position of the turtle to (x,y) and the most recent change in distance to be dist
	 * notifies observers that turtle has moved
	 * @param x - new x position
	 * @param y - new y position
	 * @param dist - the change in position
	 */
	public void setPosition(double x, double y, double dist){
		dPosition = dist;
		myX = x;
		myY = y;
		notifyTurtleMoved();
	}
	
	/**
	 * sets the heading of the turtle to heading and the most recent change in heading to be dDegrees
	 * notifies observers that turtle has rotated
	 * @param dDegrees - the change in degrees
	 * @param heading - the new heading
	 */
	public void setHeading(double dDegrees, double heading){
		dHeading = dDegrees;
		myHeading = heading;
		notifyTurtleRotate();
	}
	
	/**
	 * sets clear tail to true, notifying the display to remove the current tail
	 * notifies observers that tail should be removed
	 */
	public void clearScreen(){
		clearTail = true;
		notifyClearLines();
	}
	
	/**
	 * sets the pen to be either down isDown is true or up if it's false
	 * notifies observers that pen should be updated
	 * @param isDown - boolean determining whether pen will be up or down 
	 */
	public void setPen(double isDown){
		pendown = (int) isDown;
		notifyTurtlePen();
	}
	
	/**
	 * sets the turtle to be either showing if isShown is true or hidden if it's false 
	 * notifies observers that turtle visibility should be updated
	 * @param isShown - boolean determining whether turtle will be shown or hidden
	 */
	public void setShowing(double isShown){
		showing = (int) isShown;
		notifyTurtleShow();
	}
	
	public void setStamp(double isStamp){
		stamped = (int) isStamp;
		notifyTurtleStamped();
	}
	private void notifyClearLines() {
		setChanged();
		notifyObservers("clear");
	}
	
	private void notifyTurtleMoved() {
		if (!clearTail) {
			setChanged();
			notifyObservers("move");
		} else {
			clearTail = false;
		}
		
	}
	
	private void notifyTurtleRotate() {
		setChanged();
		notifyObservers("rotate");
	}
	
	private void notifyTurtleStamped(){
		setChanged();
		notifyObservers("stamp");
	}
	private void notifyTurtleShow() {
		setChanged();
		notifyObservers("show");
	}
	
	private void notifyTurtlePen() {
		setChanged();
		notifyObservers("pen");
	}
}
