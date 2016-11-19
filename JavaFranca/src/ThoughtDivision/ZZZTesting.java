package ThoughtDivision;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ResponseModel.SimpleQuery;
import ZZZOld.User;

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
		
		//new SimpleQuery("who became important under the first french republic");
		new SimpleQuery(User.getString("Enter Query: ").toLowerCase());
		
	}

}
