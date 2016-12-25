package slogo_turtle_ui;

import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import slogo_logic.Turtle;

/**
 * TurtleSprite is the turtle image seen on the user interface.
 * This class holds information about the turtle's position, heading,
 * and general status about how the turtle should be displayed.
 * 
 * This class is an observer of the Turtle class so that it can listen
 * for changes and animate the sprite accordingly.
 *
 */
public class TurtleSprite {
	
	public static final String DEFAULT_IMAGE_PATH = "resources/turtle.png";

	private String turtleImagePath = "resources/turtle.png";
	
	private double prevX;
	private double prevY;
	
	private boolean active;
	private boolean highlightLocked;
	private boolean pendown;
	
	private Pane canvas;
	private Image turtleImage;
	private ImageView turtleImageView;
	
	private Turtle turtle;
	private Animator animator; // set in turtle manager
	private Pen pen;
	
	private ColorAdjust brighten;
	
	// This observer updates when anything changes in turtle
	private TurtleObserver turtleObserver;
	

	public TurtleSprite(Turtle turtle, Pane canvas) {
		this.turtle = turtle;
		this.canvas = canvas;
		this.brighten = new ColorAdjust();
		this.highlightLocked = false;
		initTurtleSprite();
		pendown = true;
		turtleObserver = new TurtleObserver(this);
	}
	
	


	/**
	 * Initializes a turtle sprite by setting image
	 * and adding imageview to canvas
	 */
	private void initTurtleSprite() {
		// set turtle image view
		turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(turtleImagePath));
		turtleImageView = new ImageView(turtleImage);
		double widthToCenter = turtleImageView.getFitWidth()/2;
		double heightToCenter = turtleImageView.getFitHeight()/2;
		
		canvas.getChildren().add(turtleImageView);
		
		// Center turtle's position inside of the pane
		double centerXofPane = canvas.getBoundsInParent().getWidth()/2 - widthToCenter;
		double centerYofPane = canvas.getBoundsInParent().getHeight()/2 - heightToCenter;
		centerTurtleOnCanvas(centerXofPane, centerYofPane);
		
		pen = new Pen(canvas, this);
		
		// Set the origin for turtle animations
		animator = new Animator(this, centerXofPane, centerYofPane);
		
		createHighlightHandlers();
	}

	
	
	
	
	/**
	 * Create mouse click and hover over events to properly highlight this turtle
	 */
	private void createHighlightHandlers() {
		
		// Mouse click handler to handle clicking on turtle
		active = false;
		EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                if (!active) {
                	highlight();
                	active = true;
                } else {
                	unhighlight();
                	active = false;
                }
            }
        };
        
        // Set mouse click, mouse enter, and mouse exit events to highlight
		turtleImageView.setOnMouseClicked(clickHandler);
		turtleImageView.setOnMouseEntered(e -> highlight());
		turtleImageView.setOnMouseExited(e-> {
			if(!active) {
				unhighlight();
			}
		});
	}
	
	
	

	
	/**
	 * Centers the turtle to
	 * @param centerXofPane
	 * @param centerYofPane
	 */
	private void centerTurtleOnCanvas(double centerXofPane, double centerYofPane) {
		prevX = centerXofPane;
		prevY = centerYofPane;
		turtleImageView.setX(centerXofPane);
		turtleImageView.setY(centerYofPane);
	}
	
	
	
	
	
	/**
	 * Highlights the turtle image view
	 * @param brighten
	 */
	public void highlight() {
		if (!highlightLocked) {
			brighten.setBrightness(0.3);
			turtleImageView.setEffect(brighten);
			turtleImageView.setCache(true);
			turtleImageView.setCacheHint(CacheHint.SPEED);
		}
	}
	
	
	
	

	/**
	 * Un-highlights the turtle image view
	 * @param brighten
	 */
	public void unhighlight() {
		brighten.setBrightness(0);
		turtleImageView.setEffect(brighten);
		turtleImageView.setCache(true);
		turtleImageView.setCacheHint(CacheHint.SPEED);
	}
	
	
	
	
	
	
	/********************
	 * Getters and setters
	 * *******************
	 */
	
	/**
	 * Sets image as image found in
	 * @param newImagePath
	 */
	public void setImage(String newImagePath) {
		turtleImagePath = newImagePath;
		initTurtleSprite();
	}
	
	public Pane getCanvas(){
		return canvas;
	}
	
	/**
	 * Sets pen color to
	 * @param color
	 */
	public void setPenColor(Color color) {
		animator.setPenColor(color);
		pen.setExistingLinesColor(color);
	}
	
	/**
	 * Sets pen weight to
	 * @param weight
	 */
	public void setPenWeight(Double weight) {
		pen.setExistingLinesWeight(weight);
	}
	
	/**
	 * Set pen style to
	 * @param style
	 */
	public void setPenStyle(String style) {
		pen.setExistingLinesStyle(style);
	}

	public Turtle getTurtle() {
		return turtle;
	}
	
	public void setAnimator(Animator animator) {
		this.animator = animator;
	}
	
	public Pen getPen() {
		return pen;
	}
	
	public boolean getPendown() {
		return pendown;
	}
	
	public ImageView getTurtleImageView() {
		return turtleImageView;
	}

	public double getPrevX() {
		return prevX;
	}

	public void setPrevX(double prevX) {
		this.prevX = prevX;
	}

	public double getPrevY() {
		return prevY;
	}

	public void setPrevY(double prevY) {
		this.prevY = prevY;
	}

	public Animator getAnimator() {
		return animator;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void lockHighlighting() {
		highlightLocked = true;
	}
	
	public void unlockHighlighting() {
		highlightLocked = false;
	}

	public void setTurtleSpeed(double turtleSpeed) {
		animator.setTurtleSpeed(turtleSpeed);
	}
}
