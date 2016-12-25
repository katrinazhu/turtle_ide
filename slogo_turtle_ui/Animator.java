package slogo_turtle_ui;

import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Handles turtle animations, such as movement
 */
public class Animator {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private TurtleAnimationQueue queue;
	private ImageView turtle;
	private TurtleSprite turtleSprite;
	private Color penColor;
	private double translatedOriginX;
	private double translatedOriginY;
	private double turtleSpeed;
	private TurtleAnimation runningAnimation;
	private ResourceBundle myResources;
	
	public Animator(TurtleSprite turtleSprite, double x, double y) {
		this.turtle = turtleSprite.getTurtleImageView();
		this.turtleSprite = turtleSprite;
		this.translatedOriginX = x;
		this.translatedOriginY = y;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.turtleSpeed = Integer.parseInt(myResources.getString("TurtleSpeed"));
		penColor = Color.CYAN;
		this.queue = new TurtleAnimationQueue(this, runningAnimation);
	}
	
	/**
	 *  Plays animations that are queued up.
	 *  If animations are still playing, queues up animations to end of
	 *  currently playing queue of animations.
	 */
	public void playAnimations() {
		queue.playQueuedAnimations();
	}
	
	/**
	 * If paused, play only the next animation
	 */
	public void step() {
		if (runningAnimation.getNext() != null) {
			TurtleAnimation next = runningAnimation.getNext();
			if (next.isPendown()) {
				runningAnimation.getAnimation().setOnFinished((event)-> {
					endLine();
					bindLineToTurtle(next.getAnimation());
					runningAnimation = next;
					});
			} else {
				runningAnimation.getAnimation().setOnFinished((event)-> {
					endLine();
					runningAnimation = next;
					});
			}
		}
		if (runningAnimation.getAnimation() != null) {
			resume();
		}
	}
	
	/**
	 * Reset current running animation
	 */
	public void reset() {
		runningAnimation.getAnimation().stop();
		turtleSprite.getTurtleImageView().setX(turtleSprite.getPrevX());
		turtleSprite.getTurtleImageView().setY(turtleSprite.getPrevY());
		runningAnimation.getAnimation().jumpTo(Duration.ZERO);
		
	}
	
	/**
	 * Resume a paused animation
	 */
	public void resume() {
		runningAnimation.getAnimation().play();
	}
	
	/**
	 * Pause the currently running animation
	 */
	public void pause() {
		runningAnimation.getAnimation().pause();
	}

	/**
	 * Adds to queue an animation that fades turtle by changing opacity 
	 * from @param start 
	 * to @param end
	 */
	public void fade(int start, int end) {
		FadeAnimation fade = new FadeAnimation();
		fade.createAnimation(start, end, Integer.parseInt(myResources.getString("DefaultAnimationTime")), turtleSpeed, turtle);
		queue.add(fade);
	}

	/**
	 * Adds the animation to move the turtle to x and y coordinates to queue
	 * @param x
	 * @param y
	 */
	public void move(double x, double y, boolean pendown, double distMoved) {
		double animationDuration = Integer.parseInt(myResources.getString("DefaultAnimationTime")) * (distMoved/100);
		MoveAnimation moveAnimation= new MoveAnimation();
		double translatedX = translatedOriginX + x;
		double translatedY = translatedOriginY - y;
		moveAnimation.createAnimation(
				turtle.xProperty(), turtle.yProperty(), translatedX, translatedY, 
				animationDuration, turtleSpeed, pendown);
		queue.add(moveAnimation);
	}
	
	/**
	 * Adds a rotation animation to the queue of animations to be played
	 * @param bearing
	 */
	public void rotate(double bearing) {
		RotateAnimation rotate = new RotateAnimation();
		rotate.createAnimation(-bearing, Integer.parseInt(myResources.getString("DefaultAnimationTime")), turtleSpeed, turtle);
		queue.add(rotate);
	}
		
	/**
	 * End the line that is currently bound to the turtle sprite
	 */
	public void endLine() {
		turtleSprite.getPen().endLine();
	}
	
	/**
	 * Checks if animation is a move animation, then binds
	 * a new line to the turtle.
	 * @param Animation
	 */
	public void bindLineToTurtle(Animation head) {
		if (head instanceof Timeline) {
			turtleSprite.getPen().bindLineToTurtle(penColor);
		}
	}

	/**
	 * Getters and setters for Animator
	 */
	public double getTranslatedOriginX() {
		return translatedOriginX;
	}
	
	public double getTranslatedOriginY() {
		return translatedOriginY;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}
	
	public void setTurtleSpeed(double turtleSpeed) {
		this.turtleSpeed = turtleSpeed;
	}
}
