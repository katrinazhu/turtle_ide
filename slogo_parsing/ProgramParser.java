package slogo_parsing;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/**
 * @author Katrina Zhu
 * ProgramParser contains symbols linking a user input command to the desired executed command, based on language.
 */
public class ProgramParser {
    private List<Entry<String, Pattern>> mySymbols;
    
	/**
	 * Constructor for ProgramParser. Initializes the list of symbols.
	 */
    public ProgramParser () {
        mySymbols = new ArrayList<>();
    }
    
	/**
	 * Adds the given resource file to ProgramParser's recognized types.
	 * 
	 * @param syntax A string indicating the address of the desired resource file.
	 */
    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                           Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }
    
	/**
	 * Given a user input command, returns the symbol associated with the command.
	 * 
	 * @param text a String which reflects a user input command
	 * @return a String which reflects the command for the input if it exists, or else ERROR
	 */
    public String getSymbol (String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return ERROR;
    }
    
	/**
	 * Builds a map of commands to the number of children they should have 
	 * 
	 * @return a map of commands to the number of children they should have
	 */
    public Map<String, Integer> buildNumChildren(){
        Map<String,Integer>numChildren=new HashMap<String,Integer>();
    	ResourceBundle resources = ResourceBundle.getBundle("resources/languages/CommandChildren");
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            int number = Integer.parseInt(resources.getString(key));
            numChildren.put(key,number);
        }    
        return numChildren;
    }
	/**
	 * Determines whether or not a certain string matches a certain pattern
	 * 
	 * @param text the string in question
	 * @param regex the pattern in question
	 * @return whether or not the string and the pattern match
	 */
    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
}
