package slogo_general;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import slogo_parsing.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo_logic.LogicManager;
/**
 * @author Christopher Lu
 * @author Katrina Zhu
 * Implements user input text area. Allows users to input commands to be executed, or save and load files that reflect the desired commands.
 */
public class InputText {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private sLogoController controller;
	private ScrollPane textPane;
	private VBox textBox;
	private TextArea textArea;
    private ResourceBundle myResources;
    private String textValue;
    private Button enter = new Button("Enter");
    private String langValue;
    private ProgramParser lang;
    private Button saveFile;
    private Button loadFile;
    private Stage stage;
    private TraverseTree traverseTree;
    
    /**
     * Constructor for InputText
     * 
     * @param root a BorderPane object 
     * @param controller an instance of the sLogoController
     * @param logicManager an instance of the LogicManager class
     * @param stage an instance of the Stage class 
     */
    public InputText(BorderPane root, sLogoController controller, LogicManager logicManager, Stage stage) {
    	this.controller = controller;
    	this.textBox = new VBox();
    	this.textPane = new ScrollPane(this.textBox);
    	this.textArea = new TextArea();
    	this.stage=stage;
    	root.setLeft(textPane);
    	this.myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "View");
    	createTextPane();
    	createTextArea();
    	this.langValue = "English";
    	lang=setLanguage(langValue);
		traverseTree=new TraverseTree(lang, logicManager);
    }
    /**
     * Updates the value of textValue
     * 
     * @param newVal the desired new value of the textValue string
     */
    public void setText(String newVal) {
    	textValue = newVal;
    }
    
    /**
     * Returns the value of textValue
     * 
     * @return the value of textValue
     */
    public String getText(){
    	return textValue;
    }
    
    /**
     * Sets the value for the ProgramParser object lang
     * 
     * @param language The string indicating which language the user intends use to write commands
     */
    protected void setLangValue(String language) {
    	if (language != null && !language.isEmpty()) {
	    	langValue = language;
	    	lang=setLanguage(langValue);
	    	traverseTree.setLang(lang);
    	}
    }    
    
   /**
    * Initializes the pane that the TextArea will be placed in.
    */
    private void createTextPane() {
    	textPane.setPrefSize(controller.getWidth()*0.2, controller.getHeight()*0.7);
    	textPane.setContent(textBox);    	
    }   
    
    /**
     * Initializes the JavaFX TextArea object where the user will type in expressions.
     */
    private void createTextArea() {
    	Label inputLabel = new Label("Enter Your Expressions Here: ");
    	textBox.getChildren().add(inputLabel);
    	textArea.setPrefSize(controller.getWidth()*0.2, controller.getHeight()*0.75);
    	textBox.getChildren().add(textArea);
    	setUpEnter();
    	setUpSaveFile();
    	setUpLoadFile();
    }   
    
    /**
     * Creates the Enter button in the user input area that, when clicked, passes the text to the parser.
     */
    private void setUpEnter(){
    	textBox.getChildren().add(enter);
    	enter.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	@Override
        		public void handle (MouseEvent e) {
        			boolean executed=traverseTree.executeTextActions(textArea.getText());
        			if(executed){
        				textValue=textArea.getText();
        				controller.updateTable();
        			}
        			textArea.setText("");
        		}
        	});
    }
    
    /**
     * Creates the Load button that, when clicked, reads a non-null text file and sets the TextArea to the text in the file. 
     * Error dialogue boxes show up if the file does not exist or if the file format is invalid.
     */
    private void setUpLoadFile(){
		loadFile = new Button(myResources.getString("LoadFileButton"));
    	textBox.getChildren().add(loadFile);
    	FileChooser fileChooser = new FileChooser();
		loadFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
				public void handle(final ActionEvent e) {
					File file = fileChooser.showOpenDialog(stage);
					if (file != null){
	            		String fileInput="";
	            		try {
	    					fileInput=readFileToString(file);
	    				} catch (FileNotFoundException e1) {
	    					ErrorBox.displayError("File Not Found");
	    				}
		        		textArea.setText(fileInput);
					}
				}
		});
    }
    
    /**
     * Creates the save button that, when clicked, saves the text into a file that is also named by the user upon save request.
     * This method also displays an error box if data is not saved to file.
     */
    private void setUpSaveFile(){
		saveFile = new Button(myResources.getString("SaveFileButton"));
    	textBox.getChildren().add(saveFile);
    	saveFile.setOnMouseClicked(new EventHandler<MouseEvent>(){
    		@Override
    			public void handle(MouseEvent e){
    			TextInputDialog input = new TextInputDialog("Please name file");
    	        Optional<String> response = input.showAndWait();
    	        if (response.isPresent()) {
    	        	WriteFile data = new WriteFile(response.get());
    	        	try {
						data.writeToFile(textArea.getText());
					} catch (IOException e1) {
						ErrorBox.displayError("Could not save file.");
					}
    	        }
    		}
    	});
    }
    
    /**
     * Given a file, returns the String reflecting the text in the file
     * 
     * @param f the file which is to be translated to a String
     * @return the file's text as a String
     */
    private String readFileToString (File f) throws FileNotFoundException {
        final String END_OF_FILE = "\\z";
        Scanner input = new Scanner(f);
        input.useDelimiter(END_OF_FILE);
        String result = input.next();
        input.close();
        return result;
    }
    
    /**
     * Uses hard coded methods to link the string indicating a language and resource files of those languages.
     * 
     * @param l a string indicating which language the user intends to use to program
     * @return a ProgramParser object with the intended language patterns, as well as syntax patterns, added
     */
    private ProgramParser setLanguage(String l){
    	ProgramParser lang=new ProgramParser();
    	if("Chinese".equals(l)){
    		lang.addPatterns("resources/languages/Chinese");
    	}
    	else if("English".equals(l)){
    		lang.addPatterns("resources/languages/English");
    	}
    	else if("French".equals(l)){
    		lang.addPatterns("resources/languages/French");
    		System.out.println("Did this run");
    	}
    	else if("German".equals(l)){
    		lang.addPatterns("resources/languages/German");
    	}
    	else if("Italian".equals(l)){
    		lang.addPatterns("resources/languages/Italian");
    	}
    	else if("Portuguese".equals(l)){
    		lang.addPatterns("resources/languages/Portuguese");
    	}
    	else if("Russian".equals(l)){
    		lang.addPatterns("resources/languages/Russian");
    	}
    	else if("Spanish".equals(l)){
    		lang.addPatterns("resources/languages/Spanish");
    	}
    	lang.addPatterns("resources/languages/Syntax");
    	return lang;
    }    
}