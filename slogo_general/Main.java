package slogo_general;

import slogo_general.sLogoController;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Christopher Lu
 * Initializes controller. Controller initializes everything else.
 */

public class Main extends Application {
	
	 private static final int MILLISECOND_DELAY = 200;
	 private static final double SECOND_DELAY = MILLISECOND_DELAY/1000;

	 private sLogoController controller;
	
	/**
	 * Sets things up at the beginning.
	 * @throws IOException 
	 */
	@Override
	public void start (Stage s) throws IOException {
		controller = new sLogoController(s);
		s.setTitle(controller.getTitle());
	}
	
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

}
