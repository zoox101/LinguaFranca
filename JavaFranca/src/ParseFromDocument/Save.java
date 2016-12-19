package ParseFromDocument;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import GraphStructure.GraphObject;

public class Save {

	//Saves the current graph objects to a file
	public static void toFile(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
		for(String key: GraphObject.all.keySet()) 
			writer.write(GraphObject.all.get(key).toString() + "\n");
		writer.close();
	}

	//Gets the current graph objects from a file
	public static void fromFile(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String string;
		while((string = reader.readLine()) != null)
			GraphObject.fromString(string);
		reader.close();
	}
	
}
