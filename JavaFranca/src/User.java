import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Will Booker
 * A static user class for getting terminal inputs
 */
public class User {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Private getter method. Gets input from the user and handles IOExceptions.
	 * @param type -- The prompt for the type of input being read.
	 * @return a string given by the user.
	 */
	private static String get(String type) {
		while(true) { 
			try {return reader.readLine();} 
			catch(IOException e) {}
		}
	}

	/**
	 * Gets a string from the user.
	 * @return a string given by the user.
	 */
	public static String getString() {
		return get("a string");
	}

	/**
	 * Gets an integer from the user.
	 * @return an integer from the user.
	 */
	public static int getInteger() {
		while(true) {
			try{return Integer.parseInt(get("an integer"));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a double from the user.
	 * @return a double from the user.
	 */
	public static double getDouble() {
		while(true) {
			try{return Double.parseDouble(get("a double"));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a long from the user.
	 * @return a double from the user.
	 */
	public static long getLong() {
		while(true) {
			try{return Long.parseLong(get("a long"));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a boolean from the user.
	 * @return a boolean from the user.
	 */
	public static boolean getBoolean() {
		String input = get("yes/no").toLowerCase();
		if(input.charAt(0) == 'y') return true;
		else return false;
	}
}
