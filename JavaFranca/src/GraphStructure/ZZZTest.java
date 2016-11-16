package GraphStructure;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ThoughtDivision.Thought;

@SuppressWarnings("unused")
public class ZZZTest {
	
	//Algorithm
	//Remove all verbs
	
	public static void main(String[] args) throws IOException {
		
		Save.fromFile("megafile.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader("Bonaparte.txt"));
		ArrayList<String> strings = new ArrayList<String>(); String line;
		while((line = reader.readLine()) != null) strings.add(line); reader.close();
		
		//Splitting the paragraph by sentences and by commas
		strings = split(strings, "\\.");
		strings = split(strings, ",");
		
		//Removing all dependent clauses
		ArrayList<String> stringstemp = new ArrayList<String>();
		for(String string: strings) {
			if(contains(string, Verb.all.keySet()))
				stringstemp.add(string.toLowerCase().trim());
		}
		strings = stringstemp;
		
		//
		for(String string: strings) {
			Thought thought = new Thought(string);
			if(thought.size() < 4)
				System.out.println(thought);
		}		
		
	}
	
	static boolean contains(String string, Set<String> set) {
		String[] split = string.split(" ");
		for(int i=0; i<split.length; i++)
			if(set.contains(split[i]))
				return true;
		return false;
	}
	
	static ArrayList<String> split(ArrayList<String> strings, String splitstring) throws IOException {
		ArrayList<String> outputs = new ArrayList<String>();
		for(String string: strings) {
			String[] split = string.split(splitstring);
			for(int i=0; i<split.length; i++)
				outputs.add(split[i]);
		}
		return outputs;
	}
	
	//Manual
	static void verbHunt(String filein, String fileout) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(filein));
		
		HashSet<String> verbs = new HashSet<String>();
		String line; 
		while((line = reader.readLine()) != null) {
			String[] split = line.split(" ");
			for(int i=0; i<split.length; i++) {
				if(Verb.all.containsKey(split[i]));
				else if(verbs.contains(split[i]));
				else if(User.getBoolean(split[i] + " "))
					verbs.add(split[i]);
			}
		}
		
		System.out.println("Confirm Verbs");
		ArrayList<String> verbarraylist = new ArrayList<String>();
		verbarraylist.addAll(verbs);
		ArrayList<String >checked = new ArrayList<String>();
		for(String string: verbarraylist)
			if(User.getBoolean(string + " "))
				checked.add(string);
		for(String string: checked)
			new Verb(string);
		
		Save.toFile(fileout);
		reader.close();
	}
	
	static void connect(String subject, String verb, String object) {
		Noun subjectobj = Noun.get(subject);
		Verb verbobj = Verb.get(verb);
		Noun objectobj = Noun.get(object);
		subjectobj.addConnection(verbobj, objectobj);
		}
	
	static String[] parse(String string) {
		
		string = string.toLowerCase();
		int verbposition = -1;
		String[] arraysplit = string.split(" ");
		for(int i=0; i<arraysplit.length; i++)
			if(Verb.all.containsKey(arraysplit[i]))
				verbposition = i;
		
		//Creating subject
		String subject = "";
		for(int i=0; i<verbposition; i++)
			subject += (arraysplit[i] + " ");
		subject = subject.trim();
		
		//Creating verb
		String verb = arraysplit[verbposition];
		
		//Creating object
		String object = "";
		for(int i=verbposition+1; i<arraysplit.length; i++)
			object += (arraysplit[i] + " ");
		object = object.trim();
		
		String[] output = {subject, verb, object};
		return output;
		
	}
	
}
