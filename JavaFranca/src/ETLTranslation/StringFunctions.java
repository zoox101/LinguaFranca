package ETLTranslation;
import LOGLAN.GraphList;
import LOGLAN.LOGLAN;

public class StringFunctions {
	
	public static boolean execute(String command, String string) {
		
		if(command.equals("PROPERNOUN")) {
			GraphList pronouns = LOGLAN.parse("IOF 'pronoun'");
			String nocaps = string.toLowerCase();
			String allcaps = string.toUpperCase();
			if(string.charAt(0) == allcaps.charAt(0) && !pronouns.contains(nocaps)) 
				return true;
			else return false;
		}
		
		if(command.equals("WORD")) 
			return true;
		
		return false;
		
	}

}
