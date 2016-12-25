package slogo_general;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo_turtle_ui.TurtleView;

/**
 * @author Christopher Lu
 * @author Alan Guo
 * The tool bar will contain easy and quick to access drop down menus allowing the user to set display background color, images
 * for the turtle, and languages. The HTML help page will also be available through a button on the tool bar.
 */

public class Toolbar {
	private static final ObservableList<String> languages = 
			FXCollections.observableArrayList (
					"Chinese",
					"English",
					"French",
					"German",
					"Italian",
					"Portuguese",
					"Russian",
					"Spanish"						
		);
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private BorderPane root;
	private sLogoController controller;
	private HBox tools;
	private TurtleManager turtleManager;
	private Button Changes;
	private Button Help;
	private InputText text;
	private ComboBox<String> chooseLanguage;
	private Stage stage;
	private TurtleView view;
	private ResourceBundle myResources;
	
	public Toolbar(BorderPane root, sLogoController controller, TurtleManager turtleManager, InputText text, Stage stage, TurtleView view) throws IOException {
		this.root = root;
		this.controller = controller;
		this.turtleManager = turtleManager;
		this.text = text;
		this.stage = stage;
		this.view = view;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.tools = new HBox(10);
		this.root.setBottom(this.tools);
		createToolBar();
		this.tools.setAlignment(Pos.BOTTOM_CENTER);
	}

	/**
	 * Creates all the View Changes and Help Button. Calls the functions that implement the features like Background Color selection,
	 * turtle image selection, language selection, and a link to the HTML help page.
	 */
	private void createToolBar() {
		Changes = new Button(myResources.getString("ToolBarLang"));
		tools.getChildren().add(Changes);
		Help = new Button(myResources.getString("ToolBarHelp"));
		tools.getChildren().add(Help);
		createBackgroundColor();
		createTurtleImage();
		createLanguage();
		createHTMLHelp();
	}

	/**
	 * Adds a JavaFX ColorPicker object to the toolbar. Upon clicking a color, the background color changes to that color. 
	 */
	private void createBackgroundColor() {
		ColorPicker colorPicker = new ColorPicker();
		tools.getChildren().add(colorPicker);
		colorPicker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent e) {
				view.setBackgroundColor((colorPicker.getValue()));
			}
		});
	}
	
	/**
	 * Creates the button that, when clicked allows the user to choose an image from the resource library to set the turtle's appearance to.
	 */
	private void createTurtleImage() {
		Button chooseImage = new Button(myResources.getString("ToolBarImage"));
		tools.getChildren().add(chooseImage);
		FileChooser fileChooser = new FileChooser();
		chooseImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
				public void handle(final ActionEvent e) {
					File file = fileChooser.showOpenDialog(stage);
					if (file != null && file.getName().endsWith(".jpg")) {
						turtleManager.setTurtleImage("resources/" + file.getName());
					}
				}
		});
	}
	
	/**
	 * Creates the ComboBox that allows the user to choose between a variety of languages to input the expressions as.
	 */
	private void createLanguage() {
		chooseLanguage = new ComboBox<String>(languages);
		tools.getChildren().add(chooseLanguage);
		chooseLanguage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				text.setLangValue(chooseLanguage.getSelectionModel().getSelectedItem());
				System.out.println(chooseLanguage.getSelectionModel().getSelectedItem());
			}
		});
	}
	
	/**
	 * Creates the Help button that, when clicked links to an HTML document containing a reference of all the commands.
	 */
	private void createHTMLHelp() {
		Help.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
    		public void handle (MouseEvent e) {
				try {
					JEditorPane editorPane = new JEditorPane();
					editorPane.setEditable(false);
					java.net.URL myURL = new URL(myResources.getString("HelpURL"));
					try {
					    editorPane.setPage(myURL);
					} catch (IOException f) {
						ErrorBox.displayError("Attempted to read a bad URL: " + myURL);
					}
					JFrame frame = new JFrame(myResources.getString("HelpWindowTitle"));
					frame.setPreferredSize(new Dimension(controller.getWidth(), controller.getHeight()));
					frame.add(new JScrollPane(editorPane));
				    frame.setVisible(true);
				    frame.pack();
				} catch (IOException e1) {
					ErrorBox.displayError("Connection failed");
				}
    		}
    	});
	}
}
