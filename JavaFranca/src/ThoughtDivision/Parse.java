package ThoughtDivision;
import java.util.ArrayList;
import java.util.Set;

import GraphStructure.GraphObject;
import GraphStructure.Noun;
import GraphStructure.Relation;
import GraphStructure.Verb;

public class Parse {

	public Parse(ArrayList<String> strings) {

		//Splitting the paragraph by sentences and by commas
		strings = split(strings, "\\.");
		strings = split(strings, ",");

		//Removing all dependent clauses
		ArrayList<String> stringstemp = new ArrayList<String>();
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
				GraphObject object = GraphObject.create();
				superNoun(thought.get(0), Relation.SUBJ, object);
				superVerb(thought.get(1), object);
				superNoun(thought.get(2), Relation.OBJ, object);
			}
		}
	}

	//TODO: Add actual parsing here
	static Noun superNoun(String string, Relation relation, GraphObject object) {
		Noun noun = Noun.create(string);
		noun.addConnection(relation, object);
		return noun;
	}
	
	//TODO: Add actual parsing here
	static Verb superVerb(String string, GraphObject object) {
		Verb verb = Verb.create(string);
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
