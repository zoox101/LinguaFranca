package ResponseModel;
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
	private static String get(String prompt) {
		while(true) { 
			if (prompt != null) System.out.print(prompt);
			try {return reader.readLine();} 
			catch(IOException e) {}
		}
	}

	/**
	 * Gets a string from the user.
	 * @return a string given by the user.
	 */
	public static String getString(String prompt) {return get(prompt);}
	public static String getString() {return getString(null);}

	/**
	 * Gets an integer from the user.
	 * @return an integer from the user.
	 */
	public static int getInteger(String prompt) {
		while(true) {
			try{return Integer.parseInt(get(prompt));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	public static int getInteger() {return getInteger(null);}
	
	/**
	 * Gets a double from the user.
	 * @return a double from the user.
	 */
	public static double getDouble(String prompt) {
		while(true) {
			try{return Double.parseDouble(get(prompt));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	public static double getDouble() {return getDouble(null);}
	
	/**
	 * Gets a long from the user.
	 * @return a double from the user.
	 */
	public static long getLong(String prompt) {
		while(true) {
			try{return Long.parseLong(get(prompt));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	public static long getLong() {return getLong(null);}
	
	/**
	 * Gets a boolean from the user.
	 * @return a boolean from the user.
	 */
	public static boolean getBoolean(String prompt) {
		String input = get(prompt).toLowerCase();
		if(input.charAt(0) == 'y') return true;
		else return false;
	}
	public static boolean getBoolean() {return getBoolean(null);}
}
