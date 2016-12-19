package ParseFromDocument;
import java.util.ArrayList;
import java.util.Set;

import GraphStructure.GraphObject;
import GraphStructure.Relation;
import GraphStructure.Verb;

public class Parse {
	
	static ArrayList<String> pronouns;
	static ArrayList<String> badwords;
	static GraphObject subject;

	public Parse(ArrayList<String> strings) {
		
		pronouns = new ArrayList<String>();
		pronouns.add("he");
		pronouns.add("his");
		
		badwords = new ArrayList<String>();
		badwords.add("the");
		badwords.add("of");

		//Splitting the paragraph by sentences and by commas
		strings = split(strings, "\\.");
		strings = split(strings, ",");

		ArrayList<String> stringstemp;
		
		//Removing all dependent clauses
		stringstemp = new ArrayList<String>();
		for(String string: strings) {
			if(contains(string, Verb.all.keySet()))
				stringstemp.add(string);
		}
		strings = stringstemp;
		
		for(String string: strings) {
			ArrayList<String> thought = new ArrayList<String>();
			thought.addAll(Parse.split(string, Verb.all.keySet()));
			//If the sentence only contains one verb...
			if(thought.size() == 3) {
				System.out.println(thought);
				GraphObject object = GraphObject.create();
				subject = superNoun(thought.get(0), Relation.SUBJ, object);
				superVerb(thought.get(1), object);
				superNoun(thought.get(2), Relation.OBJ, object);
			}
		}
	}

	//TODO: Add more actual parsing here
	static GraphObject superNoun(String string, Relation relation, GraphObject object) {
		GraphObject noun = GraphObject.create();
		String[] strings = string.split(" ");
		for(int i=0; i<strings.length; i++) {
			GraphObject subnoun;
			if(pronouns.contains(strings[i])){ 
				subnoun = subject;
				subnoun.addConnection(Relation.UUU, noun);
			}
			else if(badwords.contains(strings[i]));
			else{
				subnoun = GraphObject.create(strings[i]);
				subnoun.addConnection(Relation.UUU, noun);
			}
		}
		noun.addConnection(relation, object);
		return noun;
	}
	
	//TODO: Add actual parsing here
	static GraphObject superVerb(String string, GraphObject object) {
		GraphObject verb = GraphObject.create(string);
		verb.addConnection(Relation.VERB, object);
		return verb;
	}

	//Splits a string array for anything in a collection
	public static ArrayList<String> split(ArrayList<String> strings, Set<String> collection) {

		ArrayList<String> fragments = new ArrayList<String>();
		String fragment = "";

		//For each string in the array list...
		for(String string: strings) {
			String[] split = string.split(" ");
			//Split it and loop over those strings...
			for(int i=0; i<split.length; i++) {
				//If the string is in the collection, split
				if(collection.contains(split[i])) {
					fragments.add(fragment.trim());
					fragments.add(split[i].trim());
					fragment = "";
				}
				//Else join it back togeather
				else fragment += split[i] + " ";
			}
			//Add the fragment at the end of a string
			fragments.add(fragment.trim());
		}
		return fragments;
	}

	//Splits a string array for a given string
	static ArrayList<String> split(ArrayList<String> strings, String splitstring) {
		ArrayList<String> outputs = new ArrayList<String>();
		for(String string: strings) {
			String[] split = string.split(splitstring);
			for(int i=0; i<split.length; i++)
				outputs.add(split[i].trim());
		}
		return outputs;
	}
	
	//Converts string and splits
	public static ArrayList<String> split(String string, Set<String> collection) {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(string);
		return split(strings, collection);
	}
	
	//Converts string and splits
	static ArrayList<String> split(String string, String splitstring) {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(string);
		return split(strings, splitstring);
	}
	
	
	//Returns true if the sentence contains any string from the given set
	static boolean contains(String string, Set<String> set) {
		String[] split = string.split(" ");
		for(int i=0; i<split.length; i++)
			if(set.contains(split[i]))
				return true;
		return false;
	}

}
