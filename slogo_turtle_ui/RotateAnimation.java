package slogo_turtle_ui;

import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Handles on screen rotation animation for turtle
 *
 */
public class RotateAnimation extends TurtleAnimation{
	
	public RotateAnimation() {
		this.setPendown(false);
	}
	
	/**
	 * Returns animation to rotate turtle to bearing
	 * @param bearing
	 * @return RotateTransition
	 */
	public void createAnimation(double bearing, double animationDuration, double turtleSpeed, ImageView turtleImageView) {
		RotateTransition rotateTransition = new RotateTransition(Duration.millis(animationDuration), turtleImageView);
		rotateTransition.setByAngle(bearing);
		rotateTransition.setRate(turtleSpeed);
		
		this.setAnimation(rotateTransition);
	}
}
