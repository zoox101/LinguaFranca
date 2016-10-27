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
	private static String get(String input) {
		while(true) {
			if(input != null) 
				System.out.print(input);
			try {return reader.readLine();} 
			catch(IOException e) {}
		}
	}

	/**
	 * Gets a string from the user.
	 * @return a string given by the user.
	 */
	public static String getString(String input) {
		return get(input);
	}
	
	/**
	 * Gets a string from the user.
	 * @return a string given by the user.
	 */
	public static String getString() {
		return get(null);
	}

	/**
	 * Gets an integer from the user.
	 * @return an integer from the user.
	 */
	public static int getInteger(String input) {
		while(true) {
			try{return Integer.parseInt(get(input));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets an integer from the user.
	 * @return an integer from the user.
	 */
	public static int getInteger() {
		while(true) {
			try{return Integer.parseInt(get(null));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a double from the user.
	 * @return a double from the user.
	 */
	public static double getDouble(String input) {
		while(true) {
			try{return Double.parseDouble(get(input));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a double from the user.
	 * @return a double from the user.
	 */
	public static double getDouble() {
		while(true) {
			try{return Double.parseDouble(get(null));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a long from the user.
	 * @return a double from the user.
	 */
	public static long getLong(String input) {
		while(true) {
			try{return Long.parseLong(get(input));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a long from the user.
	 * @return a double from the user.
	 */
	public static long getLong() {
		while(true) {
			try{return Long.parseLong(get(null));}
			catch(NumberFormatException e) {System.out.println("Invalid input");};
		}
	}
	
	/**
	 * Gets a boolean from the user.
	 * @return a boolean from the user.
	 */
	public static boolean getBoolean(String input) {
		String userinput = get(input).toLowerCase();
		if(userinput.charAt(0) == 'y') return true;
		else return false;
	}
	
	/**
	 * Gets a boolean from the user.
	 * @return a boolean from the user.
	 */
	public static boolean getBoolean() {
		String userinput = get(null).toLowerCase();
		if(userinput.charAt(0) == 'y') return true;
		else return false;
	}
}
