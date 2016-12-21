package LOGLAN;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import GraphStructure.Relation;

public class ParseMain {

	//Parses a number of strings
	public ParseMain(ArrayList<String> strings) {
		for(String string: strings)
			identifyAndParse(string);
	}

	public static ParseMain fromFile(String filename) throws IOException {
		//Getting strings from file
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArrayList<String> lines = new ArrayList<String>(); String line;
		while((line = reader.readLine()) != null)
			lines.add(line);
		reader.close();
		return new ParseMain(lines);
	}

	private void identifyAndParse(String string) {
		String[] split = string.split(" ");
		if(string.length() == 0);
		else if(split.length == 3 && relationContains(split[1]))
			new Triplet(string);
		else if(string.contains(":"))
			new MicroFunction(string);
		else {
			MicroFunction function = new MicroFunction(string);
			System.out.println(function.execute());
		}
	}

	private boolean relationContains(String string) {
		for(Relation relation: Relation.values())
			if(relation.name().equals(string))
				return true;
		return false;	
	}

}
