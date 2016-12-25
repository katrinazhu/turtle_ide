package slogo_turtle_ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import slogo_general.ColorMapper;

/**
 * Canvas (user view) of turtles & turtle environment
 */
public class TurtleView{
	
	private Pane canvas;
	
	/**
	 * Constructor that initializes canvas and a turtle sprite on the canvas
	 * @param root
	 * @param controller
	 * @param turtle
	 */
	public TurtleView(BorderPane root) {
		// set canvas
		canvas = new Pane();
		canvas.setStyle("-fx-background-color: blue;");
		
//		new TurtleSprite(turtle, canvas, controller);
		
		// set turtle view as center view on UI
		root.setCenter(canvas);
	}
	
	public Pane getCanvas() {
		return canvas;
	}
	
	/**
	 * Sets background color of canvas to user selected option
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		canvas.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
//		ColorMapper colorMap = new ColorMapper();
//		canvas.setBackground(
//				new Background(
//						new BackgroundFill(
//								colorMap.stringToColor(color), 
//								CornerRadii.EMPTY, 
//								Insets.EMPTY)));
	}
	
	

}
