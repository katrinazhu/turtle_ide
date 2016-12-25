package slogo_general;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import slogo_logic.Turtle;
import slogo_turtle_ui.TurtleSprite;
import slogo_turtle_ui.TurtleView;

/**
 * Keeps a list of TurtleSprites. 
 * 
 * Back-end can addTurtle or removeTurtle
 * to specify a specific TurtleSprite corresponding to the Turtle instance.
 *
 */
public class TurtleManager {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private List<Turtle> turtles;
	private List<TurtleSprite> turtleSprites;
	private TurtleView turtleView;
	private String turtleImagePath;
	private int screenHeight;
	private int screenWidth;
	private ResourceBundle myResources;
	
	public TurtleManager(TurtleView turtleView) {
		this.turtleView = turtleView;
		this.turtleSprites = new ArrayList<TurtleSprite>();
		this.turtles = new ArrayList<Turtle>();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		setPenColor(myResources.getString("DefaultPenColor"));
	}
	
	
	
	/**
	 * Adds a turtle to the turtle manager given a Turtle backend object.
	 * Creates new turtle sprite, new animation, & adds self to canvas.
	 * @param turtle
	 */
	public void addTurtle(Turtle turtle) {
		TurtleSprite newSprite = new TurtleSprite(turtle, turtleView.getCanvas());
		turtleSprites.add(newSprite);
		turtles.add(turtle);
	}
	
	
	
	
	/**
	 * Removes a specific turtle from sprite list, animations, and canvas.
	 * @param turtle
	 */
	public void removeTurtle(Turtle turtle) {
		TurtleSprite found = null;
		
		for(int i = 0; i < turtleSprites.size(); i++) {
			if (turtleSprites.get(i).getTurtle() == turtle) {
				found = turtleSprites.get(i);
				// remove turtle sprite from list
				turtleSprites.remove(i);
			}
		}
		
		// remove turtle image view from canvas
		turtleView.getCanvas().getChildren().remove(found.getTurtleImageView());
		
		// remove turtle
		turtles.remove(turtle);
	}
	
	
	
	
	
	/**
	 * Toggles on highlighting of all active turtles
	 */
	private void showActiveTurtles() {
		for (TurtleSprite turtle: turtleSprites) {
			
			// Allow turtles to be highlighted again
			turtle.unlockHighlighting();
			
			if (turtle.isActive()) {
				turtle.highlight();
			}
		}
	}
	
	
	
	
	
	/**
	 * Toggles off highlighting for all turtles
	 */
	private void hideActiveTurtles() {
		for (TurtleSprite turtle: turtleSprites) {
			// Prevent turtles from being highlighted
			turtle.lockHighlighting();
			
			// Un-highlight all turtles
			if (turtle.isActive()) {
				turtle.unhighlight();
			}
		}
	}
	
	
	
	
	
	
	/**
	 * Sets pen color to color for all turtles.
	 * Doesn't do anything if input is null.
	 * @param color
	 * @param turtles
	 */
	public void setPenColor(String color) {
		if (color != null && !color.isEmpty()) {
			ColorMapper colorMap = new ColorMapper();
			for(TurtleSprite ts: turtleSprites) {
					ts.setPenColor(colorMap.stringToColor(color));
			}
		}
	}
	
	/**
	 * Sets pen weight as weight for all turtles.
	 * Doesn't do anything if input is null.
	 * @param weight
	 */
	
	public void setPenWeight(Double weight) {
		if (weight != null && !weight.isInfinite()) {
			for (TurtleSprite ts : turtleSprites) {
				ts.setPenWeight(weight);
			}
		}
	}
	
	/**
	 * Sets the styling for a pen
	 * @param style
	 */
	public void setPenStyle(String style) {
		if (style != null && !style.isEmpty()) {
			for (TurtleSprite ts : turtleSprites) {
				ts.setPenStyle(style);
			}
		}
	}
	
	
	
	
	
	
	/**
	 * Sets all turtles' image to specified image found in path
	 * @param path
	 */
	public void setTurtleImage(String path) {
		setUpScreenResolution();
		if (!path.isEmpty()) {
			turtleImagePath = path;
		}
		else {
			turtleImagePath = myResources.getString("DefaultImagePath");
		}
		
		for(TurtleSprite ts: turtleSprites) {
			ImageView turtleImageView = ts.getTurtleImageView();
			Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(turtleImagePath));
			turtleImageView.setImage(turtleImage);
			turtleImageView.setFitHeight(screenHeight*0.05);
			turtleImageView.setFitWidth(screenWidth*0.05);
		}
	}
	
	/**
	 *  Set up screen width and height variables (resolution)
	 */
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	/**
	 * Sets the background color for the turtle view.
	 * Doesn't do anything if input is null.
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		if (color != null) {
			turtleView.setBackgroundColor(color);
		}
	}
	
	public void setCheckbox(CheckBox checkBox) {
		checkBox.setOnMouseClicked(e-> {
			if (checkBox.isSelected()) {
				showActiveTurtles();
			} else {
				hideActiveTurtles();
			}
		});
	}
	
	/**
	 * Sets slider value listener so that when the slider is dropped to a 
	 * new value, turtle speed updates to that value.
	 * @param slider
	 */
	public void setSlider(Slider slider) {
		slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {

                if (!changing) {
                    for (TurtleSprite turtle: turtleSprites) {
                    	turtle.setTurtleSpeed(slider.getValue());
                    }
                }
            }
        });
	}
	
	
	
	/**
	 * Getter for all turtle sprites on the canvas
	 * @return
	 */
	public List<TurtleSprite> getTurtleSprites() {
		return turtleSprites;
	}
	
	/**
	 * Getter for list of all alive turtles.
	 * @return all turtles
	 */
	public List<Turtle> getTurtles() {
		return turtles;
	}
	
	
	
}
