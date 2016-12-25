package slogo_turtle_ui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * PenLine keeps track of lines tracking 
 * a specific turtle's movement when pen is down.
 * 
 * First bindLineToTurtle, then endLine after animation complete.
 * 
 * On pen up, clearLines.
 *
 */
public class Pen {
	private List<Line> lines;
	private Pane canvas;
	private TurtleSprite turtleSprite;
	private int currentIndex;
	
	public Pen(Pane canvas, TurtleSprite turtleSprite) {
		this.currentIndex = -1;
		this.canvas = canvas;
		this.turtleSprite = turtleSprite;
		this.lines = new ArrayList<Line>();
	}
	
	
	
	
	/**
	 * Draws a line on the canvas following turtle sprite.
	 * 
	 */
	public void bindLineToTurtle(Color color) {
		currentIndex++;
		Line line = new Line();
		line.setStroke(color);
		
		// Calculate center of turtle image view within the canvas
		double widthToCenter = turtleSprite.getTurtleImageView().getBoundsInLocal().getWidth()/2;
		double heightToCenter = turtleSprite.getTurtleImageView().getBoundsInLocal().getHeight()/2;

		double prevX = turtleSprite.getPrevX() + widthToCenter;
		double prevY = turtleSprite.getPrevY() + heightToCenter;
        line.setStartX(prevX);
        line.setStartY(prevY);
        
        DoubleBinding xProp = turtleSprite.getTurtleImageView().xProperty().add(widthToCenter);
        DoubleBinding yProp = turtleSprite.getTurtleImageView().yProperty().add(heightToCenter);
        
        // Bind the end (x,y) of line to turtle's (x,y)
	    line.endXProperty().bind(xProp);
	    line.endYProperty().bind(yProp);
        
        lines.add(line);
        
        canvas.getChildren().add(line);
	}
	
	
	
	
	
	
	/**
	 * Ends the current line at current turtle sprite position.
	 * Also sets turtle sprite's previous (x,y).
	 */
	public void endLine() {
		double prevX = turtleSprite.getTurtleImageView().getX();
		double prevY = turtleSprite.getTurtleImageView().getY();
	    turtleSprite.setPrevX(prevX);
	    turtleSprite.setPrevY(prevY);
		for (Line l: lines) {
			l.endXProperty().unbind();
			l.endYProperty().unbind();
		}
	}
	
	
	
	
	
	/**
	 * Clears canvas of all lines.
	 */
	public void clearLines() {
		currentIndex = -1;
		canvas.getChildren().removeAll(lines);
		lines.clear();
	}
	
	
	
	
	
	
	/**
	 * Set all lines to @param color
	 */
	public void setExistingLinesColor(Color color) {
		for (Line l: lines) {
			l.setStroke(color);
		}
	}
	
	/**
	 * Set all lines' weights to
	 * @param weight
	 */
	public void setExistingLinesWeight(Double weight) {
		for (Line l : lines) {
			l.setStrokeWidth(weight);
		}
	}
	
	/**
	 * Set all line's styles to
	 * @param style
	 */
	public void setExistingLinesStyle(String style) {
		for (Line l : lines) {
			if (style.equals("Dotted")) {
				l.getStrokeDashArray().addAll(10d);
			} else if (style.equals("Dashed")) {
				l.getStrokeDashArray().addAll(20d, 12d);
			} else if (style.equals("CoolDash")) {
				l.getStrokeDashArray().addAll(20d, 10d, 5d);
			} else {
				l.getStrokeDashArray().addAll(0d);
			}
		}
	}
}
