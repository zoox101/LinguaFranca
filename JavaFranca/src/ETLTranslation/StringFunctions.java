package ETLTranslation;

public class StringFunctions {
	
	public static boolean execute(String command, String string) {
		
		if(command.equals("CAPITALIZED")) {
			String allcaps = string.toUpperCase();
			if(string.charAt(0) == allcaps.charAt(0)) return true;
			else return false;
		}
		
		return false;
		
	}

}
