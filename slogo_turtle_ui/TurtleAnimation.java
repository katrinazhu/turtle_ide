package slogo_turtle_ui;

import javafx.animation.Animation;

/**
 * Parent class for all types of turtle animations.
 * Holds information about previous and next animations and pendown status.
 *
 */
public class TurtleAnimation{
	private Animation animation;
	private TurtleAnimation next;
	private TurtleAnimation prev;
	private boolean pendown;
	
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	public boolean isPendown() {
		return pendown;
	}
	public void setPendown(boolean pendown) {
		this.pendown = pendown;
	}
	public TurtleAnimation getNext() {
		return next;
	}
	public void setNext(TurtleAnimation next) {
		this.next = next;
	}
	public TurtleAnimation getPrev() {
		return prev;
	}
	public void setPrev(TurtleAnimation prev) {
		this.prev = prev;
	}
	
}