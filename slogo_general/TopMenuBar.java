package slogo_general;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo_logic.LogicManager;
import slogo_logic.Turtle;
import slogo_turtle_ui.TurtleSprite;

/**
 * @author Christopher Lu
 * Creates the top menu bar allowing the user to open, close, save, and create a new workspace. Also allows the user to set pen style pen width, pen color, and pen status.
 */

public class TopMenuBar {
	
	private enum Color {
		Red, Orange, Yellow, Green, Blue, Purple, White, Black, Grey, Cyan
	}
	
	private enum Style {
		Solid, Dashed, CoolDash, Dotted
	}
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Stage stage;
	private TurtleManager manager;
	private HBox topContainer;
	private MenuBar mainMenu;
	private Turtle turtle;
	private int NUM_WEIGHT_OPTIONS = 20;
	private double[] weightArray;
	private ResourceBundle myResources;
	private BorderPane root;
	private ActiveVars variable;
	private ObservableList<ActiveVars> varList = FXCollections.observableArrayList();
	private LogicManager logic;
	
	public TopMenuBar(BorderPane root, TurtleManager manager, Turtle turtle, Stage stage, LogicManager logic) {
		this.stage = stage;
		this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
		this.manager = manager;
		this.turtle = turtle;
		this.root = root;
		this.logic = logic;
		topContainer = new HBox();
		mainMenu = new MenuBar();
		topContainer.getChildren().add(mainMenu);
		createMainMenu();
		createDebugger();
		this.root.setTop(topContainer);
	}
	
	/**
	 * Creates the menus for the menuBar.
	 */
	private void createMainMenu() {
		Menu file = new Menu(myResources.getString("TopMenuFileLabel"));
		fileOptions(file);
		Menu pen = new Menu(myResources.getString("TopMenuPenLabel"));
		penOptions(pen);
		Menu var = new Menu(myResources.getString("TopMenuVarLabel"));
		varOptions(var);
		mainMenu.getMenus().addAll(file, pen, var);
	}
	
	/**
	 * Creates the buttons that allow user to resume, pause, reset, and step the simulation. Also creates checkBox and slider that control turtle speed and
	 * turtle highlight status.
	 */
	private void createDebugger() {
		Button resume = createResumeButton();
		Button pause = createPauseButton();
		Button reset = createResetButton();
		Button step = createStepButton();
		CheckBox checkBox = createCheckbox();
		Slider slider = createSlider();
		topContainer.getChildren().addAll(resume, pause, reset, step, slider, checkBox);
	}
	
	/**
	 * This checkbox allows user to highlight/not highlight the turtle.
	 * @return
	 */
	private CheckBox createCheckbox() {
		CheckBox checkBox = new CheckBox(myResources.getString("CheckBox"));
		checkBox.setIndeterminate(false);
		manager.setCheckbox(checkBox);
		return checkBox;
	}
	
	/**
	 * This slider allows the user to change the speed at which a turtle moves during the simulation.
	 * @return
	 */
	private Slider createSlider() {
		Slider slider = new Slider(0, 50, 1);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(10);
		slider.setBlockIncrement(2.5);
		slider.setPrefWidth(400);
		slider.setStyle("-fx-padding: 0 20 0 20");
		manager.setSlider(slider);
		return slider;
	}
	
	/**
	 * This button allows the user to step through the simulation.
	 * @return
	 */
	private Button createStepButton() {
		Button step = new Button("Step");
		step.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				List<TurtleSprite> turtles = manager.getTurtleSprites();
				for (TurtleSprite ts: turtles) {
					ts.getAnimator().step();
				}
			}
		});
		return step;
	}
	
	/**
	 * This button allows the user to resume the simulation when previously in a paused state.
	 * @return
	 */
	private Button createResumeButton() {
		Button resume = new Button("Resume");
		resume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				List<TurtleSprite> turtles = manager.getTurtleSprites();
				for (TurtleSprite ts: turtles) {
					ts.getAnimator().resume();
				}
			}
		});
		return resume;
	}
	
	/**
	 * This button allows the user to pause an ongoing simulation.
	 * @return
	 */
	private Button createPauseButton() {
		Button pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				List<TurtleSprite> turtles = manager.getTurtleSprites();
				for (TurtleSprite ts: turtles) {
					ts.getAnimator().pause();
				}
			}
		});
		return pause;
	}
	
	/**
	 * This button allows the user to reset the position of the turtle in the animation.
	 * @return
	 */
	private Button createResetButton() {
		Button reset = new Button("Reset");
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				List<TurtleSprite> turtles = manager.getTurtleSprites();
				for (TurtleSprite ts: turtles) {
					ts.getAnimator().reset();
				}
			}
		});
		return reset;
	}
	
	/**
	 * This creates the menu for FILE, allowing the user to open, close, or save a workspace.
	 * @param file
	 */
	private void fileOptions(Menu file) {
		MenuItem newWorkspace = new MenuItem(myResources.getString("FileNew"));
		performNewWorkSpace(newWorkspace);
		MenuItem openWorkspace = new MenuItem(myResources.getString("FileOpen"));
		MenuItem closeWorkspace = new MenuItem(myResources.getString("FileClose"));
		performCloseWorkSpace(closeWorkspace);
		MenuItem saveWorkspace = new MenuItem(myResources.getString("FileSave"));
		performSaveWorkSpace(saveWorkspace);
		file.getItems().addAll(newWorkspace, openWorkspace, closeWorkspace, saveWorkspace);
	}
	
	/**
	 * This creates a list of submenus allowing the user to change various pen properties.
	 * @param pen
	 */
	private void penOptions(Menu pen) {
		Menu penColor = new Menu(myResources.getString("PenColor"));
		penColorSubMenu(penColor);
		Menu penWeight = new Menu(myResources.getString("PenWeight"));
		penWeightSubMenu(penWeight);
		Menu penStyle = new Menu(myResources.getString("PenStyle"));
		penStyleSubMenu(penStyle);
		Menu penStatus = new Menu(myResources.getString("PenStatus"));
		penStatusSubMenu(penStatus);
		pen.getItems().addAll(penColor, penWeight, penStyle, penStatus);
	}
	
	/**
	 * This creates a menu option that, when clicked opens a window that displays active variables and their values in pop up window.
	 * @param var
	 */
	private void varOptions(Menu var) {
		MenuItem ActiveVars = new MenuItem(myResources.getString("ActiveVars"));
		var.getItems().add(ActiveVars);
		ActiveVars.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				BorderPane varRoot = new BorderPane();
				Stage vars = new Stage();
				createVarTable(vars, varRoot);
				vars.setTitle(myResources.getString("VariablesWindow"));
				vars.setScene(new Scene(varRoot, 450, 450));
				vars.show();
			}
		});
	}
	
	/**
	 * This creates the table that holds the variable information.
	 * @param stage
	 * @param root
	 */
	private void createVarTable(Stage stage, BorderPane root) {
		TableView<ActiveVars> table = new TableView<ActiveVars>();
		table.setEditable(true);
		TableColumn<ActiveVars, String> firstCol = new TableColumn<ActiveVars, String>(myResources.getString("VariableTableName"));
		firstCol.setCellValueFactory(new PropertyValueFactory<ActiveVars, String>(myResources.getString("VariableTableName")));
		TableColumn<ActiveVars, String> secondCol = new TableColumn<ActiveVars, String>(myResources.getString("VariableTableValue"));
		secondCol.setCellValueFactory(new PropertyValueFactory<ActiveVars, String>(myResources.getString("VariableTableValue")));
		table.getColumns().addAll(firstCol, secondCol);
		createTableData(table);
		root.setCenter(table);
	}
	
	/**
	 * This method actually extracts the variable information from the existing map of variable data that will be placed in the variable table.
	 * @param table
	 */
	private void createTableData(TableView<ActiveVars> table) {
		varList.clear();
		Map<String, String> varData = logic.getUserVariables();
		for (Map.Entry<String, String> entry : varData.entrySet()) {
			variable = new ActiveVars(entry.getKey(), entry.getValue());
			varList.add(variable);
		}
		table.setItems(varList);
	}
	
	/**
	 * A new workspace in a separate window will be created if the user selects this menu item.
	 * @param newWorkSpace
	 */
	private void performNewWorkSpace(MenuItem newWorkSpace) {
		newWorkSpace.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				Stage newWorkspace = new Stage();
				try {
					sLogoController newController = new sLogoController(newWorkspace);
					newController.initSim();
				} catch (IOException e) {
					ErrorBox.displayError(myResources.getString("URLError"));
				}
			}
		});
	}
	
	/**
	 * The current workspace will be saved if the user selects this menu item.
	 * @param saveWorkSpace
	 */
	private void performSaveWorkSpace(MenuItem saveWorkSpace) {
		saveWorkSpace.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle(myResources.getString("SaveFileButton"));
				File file = fileChooser.showSaveDialog(stage);
			}
		});
	}
	
	/**
	 * The current workspace will close if this menu item is selected.
	 * @param closeWorkSpace
	 */
	private void performCloseWorkSpace(MenuItem closeWorkSpace) {
		closeWorkSpace.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
	}
	
	/**
	 * Creates a submenu of colors that, upon being clicked, will set the pen color.
	 * @param penColor
	 */
	private void penColorSubMenu(Menu penColor) {
		for (Color color: Color.values()) {
			MenuItem hi = new MenuItem(color.toString());
			penColor.getItems().add(hi);
			hi.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					String color = hi.getText();
					manager.setPenColor(color);
				}
			});
		}
	}
	
	/**
	 * Creates an array of pen weights.
	 */
	private void createWeightArray() {
		weightArray = new double[NUM_WEIGHT_OPTIONS];
		for (int i = 0; i < weightArray.length; i++) {
			weightArray[i] = (i+1)*0.5;
		}
	}
	
	/**
	 * Allows the user to set the pen weight based on the array of available weights created in the above method.
	 * @param penWeight
	 */
	private void penWeightSubMenu(Menu penWeight) {
		createWeightArray();
		for (Double weight : weightArray) {
			MenuItem w = new MenuItem(weight.toString());
			penWeight.getItems().add(w);
			w.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					String sWeight = w.getText();
					double dWeight = Double.parseDouble(sWeight);
					manager.setPenWeight(dWeight);
				}
			});
		}
	}
	
	/**
	 * Allows the user to select te style of pen line to draw e.g. dashed, dotted, solid, etc.
	 * @param penStyle
	 */
	private void penStyleSubMenu(Menu penStyle) {
		for (Style s : Style.values()) {
			MenuItem sItem = new MenuItem(s.toString());
			penStyle.getItems().add(sItem);
			sItem.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					String style = sItem.getText();
					manager.setPenStyle(style);
				}
			});
		}
	}
	
	/**
	 * Allows the user to graphically set pen up and pen down.
	 * @param penStatus
	 */
	private void penStatusSubMenu(Menu penStatus) {
		MenuItem up = new MenuItem("Up");
		MenuItem down = new MenuItem("Down");
		penStatus.getItems().addAll(up, down);
		up.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				turtle.setPen(0);
			}
		});
		down.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				turtle.setPen(1);
			}
		});
	}
	
}
