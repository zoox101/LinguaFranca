package LOGLAN;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import GraphStructure.Relation;

public class LOGLAN {
	
	public static GraphList parse(String string) {
		return LOGLAN.identifyAndParse(string);
	}
	
	public static ArrayList<GraphList> parse(ArrayList<String> strings) {
		ArrayList<GraphList> outputs = new ArrayList<GraphList>();
		for(String string: strings)
			if(LOGLAN.identifyAndParse(string) != null)
				outputs.add(LOGLAN.identifyAndParse(string));
		return outputs;
	}

	public static ArrayList<GraphList> fromFile(String filename) throws IOException {
		//Getting strings from file
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArrayList<String> lines = new ArrayList<String>(); String line;
		while((line = reader.readLine()) != null)
			lines.add(line.trim());
		reader.close();
		return parse(lines);
	}

	//Checks LOGLAN structure and executes appropriately
	private static GraphList identifyAndParse(String string) {
		
		//Skip empty lines
		if(string.length() == 0);
		//If the first word is a command parse a function
		else if(firstWordIsCommand(string)) {
			MicroFunction function = new MicroFunction(string);
			//If unnamed, execute the function
			if(!string.contains(":")) return function.execute();
		}
		//If the first word is not a command, create a new triplet 
		else new Triplet(string);
		return null;
	}

	//Checks to see if the string is part of the Relation Enum
	private static boolean relationContains(String string) {
		for(Relation relation: Relation.values())
			if(relation.name().equals(string))
				return true;
		return false;	
	}

	//Checks if the first word is a command
	public static boolean firstWordIsCommand(String string) {
		
		int counter1 = Integer.MAX_VALUE-2;
		int counter2 = Integer.MAX_VALUE;
		
		//Finding the beginning of the first word
		for(int i=0; i<string.length(); i++)
			if(string.charAt(i) == ':' && i<counter1)
				counter1 = i;
		counter1 += 2;
		if(counter1 == Integer.MAX_VALUE) counter1 = 0;
		
		//Finding the end of the first word
		for(int i=counter1; i<string.length(); i++)
			if(string.charAt(i) == ' ' && i<counter2)
				counter2 = i;
		
		//Checking if the word is a command
		String command = string.substring(counter1, counter2);
		String altcommand = string.substring(counter1+1, counter2);
		if(relationContains(command) || relationContains(altcommand) || 
				MicroFunction.all.containsKey(command))
			return true;
		else return false;
	}

}
