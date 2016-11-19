package ThoughtDivision;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import GraphStructure.GraphObject;
import GraphStructure.Relation;
import ResponseModel.SimpleQuery;
import ZZZOld.User;

@SuppressWarnings("unused")
public class ZZZTesting {
	
	static String FILENAME = "Data/Bonaparte.txt";
	
	public static void main(String args[]) throws IOException {
		
		Save.fromFile("Data/Dictionary.csv");
		BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
		ArrayList<String> strings = new ArrayList<String>(); String line;
		while((line = reader.readLine()) != null) 
			strings.add(line.toLowerCase().trim()); 
		reader.close();
		new Parse(strings);
		
		
		
		// TEMPORARY TESTING STUFF
		GraphObject object;
		
		/*
		object = GraphObject.allid.get(44);
		System.out.println(object + " -- " + object.out + " -- " + object.in);
		object = GraphObject.allid.get(42);
		System.out.println(object + " -- " + object.out + " -- " + object.in);
		object = GraphObject.allid.get(43);
		System.out.println(object + " -- " + object.out + " -- " + object.in);
		object = GraphObject.allid.get(31);
		System.out.println(object + " -- " + object.out + " -- " + object.in);
		*/
		
		for(int i=0; i<GraphObject.all.size(); i++) System.out.println(GraphObject.allid.get(i) + " -- " + GraphObject.allid.get(i).out);
		System.out.println("");
		
		String input;
		while(!(input = User.getString("Enter Query: ").toLowerCase()).equals("exit")) {
			new SimpleQuery(input);
			System.out.println("");
		}
		
	}

}
