package slogo_general;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo_general.Toolbar;
import slogo_general.MainMenu;
import slogo_turtle_ui.TurtleView;

import slogo_parsing.ProgramParser;
import slogo_general.Variable;
import slogo_logic.LogicManager;
import slogo_logic.Turtle;

/**
 * @author Christopher Lu
 * The sLogoController will be responsible for setting up the simulation and transferring data between the front end and the back end. It initializes a multitude of objects,
 * such as the turtle, logicManager, turtleHistory, etc.
 */
public class sLogoController {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public int screenWidth;
	public int screenHeight;
	private ResourceBundle myResources;
	private Stage stage;
	private String title;
	private BorderPane root;
	private Scene scene;
	private Scene mainMenu;
	private InputText userInput;
	private Toolbar toolBar;
	private TurtleView turtleView;
	private LogicManager logicManager;
	private Turtle turtle;
	private TurtleManager turtleManager;
	private TurtleData turtleData;
	private TopMenuBar menuBar;
	
	
	public sLogoController(Stage stage) throws IOException {
		this.stage = stage;
		setUpScreenResolution();
		root = new BorderPane();
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		turtle = new Turtle();
		logicManager=new LogicManager(turtle);
		userInput = new InputText(root, this, logicManager, stage);
		turtleData = new TurtleData(root, this, userInput, turtle);
		turtleView = new TurtleView(root);
		turtleManager = new TurtleManager(turtleView);
		menuBar = new TopMenuBar(root, turtleManager, turtle, stage, logicManager);
		toolBar = new Toolbar(root, this, turtleManager, userInput, stage, turtleView);		
		scene = new Scene(root, screenWidth, screenHeight, Color.WHITE);
		MainMenu menu = new MainMenu(this, stage);
		mainMenu = menu.init();
		stage.setScene(mainMenu);
		stage.show();		
		title = myResources.getString("WindowTitle");
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getWidth() {
		return screenWidth;
	}
	
	public int getHeight() {
		return screenHeight;
	}
	
	public void updateTable() {
		turtleData.createTurtleData();
	}

	
	/**
	 * Determines size of user's screen.
	 */
	private void setUpScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
	
	/**
	 * Initializes actual in-simulation screen. Called when start button is pressed.
	 */
	public void initSim() {
		stage.setScene(scene);
		turtleManager.addTurtle(turtle);
	}
	
}
