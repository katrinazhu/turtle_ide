package slogo_general;

import javafx.scene.Scene;
import java.io.File;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import slogo_general.sLogoController;

/**
 * @author Christopher Lu
 * This class generates the main menu with start button.
 */
public class MainMenu {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final int menuWidth = 600;
	private static final int menuHeight = 400;
	private Scene scene;
	private BorderPane root;
	private sLogoController controller;
	private Stage stage;
	private ResourceBundle myResources;

	/**
	 * Constructor for main menu class
	 * 
	 * @param controller
	 * @param stage
	 */
	public MainMenu(sLogoController controller, Stage stage) {
		this.stage = stage;
		this.controller = controller;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.root = new BorderPane();
		this.scene = new Scene(root, menuWidth, menuHeight);
	}

	/**
	 * initializes all values for the main menu to be displayed
	 * 
	 * @return
	 */
	public Scene init() {
		root.getChildren().add(createBackgroundImage());
		root.setCenter(createText());

		Button b = createButton(myResources.getString("StartButton"));
		BorderPane.setAlignment(b, Pos.BASELINE_CENTER);
		root.setBottom(b);
		return scene;
	}

	/**
	 * Creates a start button
	 * 
	 * @param buttonText
	 * @return
	 */
	private Button createButton(String buttonText) {
		Button button = new Button(buttonText);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				controller.initSim();
			}
		});
		return button;
	}

	/**
	 * generates the text for Main Menu
	 * @return
	 */
	private Text createText() {
		Text t = new Text(myResources.getString("Title"));
		t.setFont(Font.font("Verdana", 70));
		t.setFill(Color.BLUE);
		return t;
	}

	/**
	 * creates and sets the background image
	 * @return
	 */
	private ImageView createBackgroundImage() {
		ImageView background = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream("resources/MainMenuBackgroundImage.jpg")));
		background.setX(0);
		background.setY(0);
		return background;
	}
}
