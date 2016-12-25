package slogo_general;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author Katrina Zhu
 * Class that enables the user to create and write to a file
 */
public class WriteFile {
	private String path;
	/**
	 * Constructor for WriteFile.
	 * @param file_path a String representing the file's path
	 */
	public WriteFile(String file_path) {
		path=file_path;
	}
	/**
	 * Enables the user to write the specific text to the file in question.
	 * @param textLine the text that the user wishes to be in the file
	 */
	public void writeToFile(String textLine) throws IOException{
		FileWriter write=new FileWriter(path);
		PrintWriter print_line = new PrintWriter(write);
		print_line.print(textLine);
		print_line.close();
	}
}
