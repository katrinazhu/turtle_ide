package slogo_turtle_ui;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Fade animation that fades turtle in and out of view
 *
 */
public class FadeAnimation extends TurtleAnimation{
	public FadeAnimation() {
		this.setPendown(false);
	}
	/**
	 * Sets fade from and to opacities
	 * @param from (must be between 0 and 1)
	 * @param to (must be between 0 and 1)
	 */
	public void createAnimation(double from, double to, double duration, double turtleSpeed, ImageView turtle) {
		FadeTransition ft = new FadeTransition(Duration.millis(duration), turtle);
		ft.setFromValue(from);
		ft.setToValue(to);
		ft.setRate(turtleSpeed);
		this.setAnimation(ft);
	}
}
