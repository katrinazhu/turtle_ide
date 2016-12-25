package slogo_turtle_ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

/**
 * Move animation that handles movement of turtle on screen
 *
 */
public class MoveAnimation extends TurtleAnimation{
	
	public MoveAnimation() {
		this.setPendown(true);
	}
	
	/**
	 * Returns an animation to move turtle to x,y coordinates
	 * 
	 */
	public void createAnimation(DoubleProperty startX, DoubleProperty startY, 
			double endX, double endY, double time, double turtleSpeed, boolean pendown) {
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().clear();
		
		// horizontal movement
		KeyValue kv = new KeyValue(startX, endX);
		KeyFrame kf = new KeyFrame(Duration.millis(time), kv);
		timeline.getKeyFrames().add(kf);
		
		// vertical movement
		KeyValue kv2 = new KeyValue(startY, endY);
		KeyFrame kf2 = new KeyFrame(Duration.millis(time), kv2);
		timeline.getKeyFrames().add(kf2);
		
		timeline.setRate(turtleSpeed);
		
		this.setPendown(pendown);
		
		this.setAnimation(timeline);
	}
}
