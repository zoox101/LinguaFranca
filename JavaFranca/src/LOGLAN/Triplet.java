package LOGLAN;
import GraphStructure.GraphObject;
import GraphStructure.Relation;

public class Triplet {

	//Creates a triplet
	public Triplet(String string) {
		
		//System.out.println(string); //DEBUGGING
		
		//Names the triplet if appropriate
		String name = null;
		if(string.contains(":")) {
			String[] strings = string.split(":");
			name = strings[0];
			string = strings[1].trim();
		}
				
		//Adding the triplet to the graph
		String[] triplet = sanitize(string);
		if(relationContains(triplet[1])) simpleTriplet(triplet);
		else complexTriplet(triplet, name);
		
	}
	
	//Handles strings with clearly defined connections 
	private void simpleTriplet(String[] triplet) {
		GraphObject subject = GraphObject.create(triplet[0]);
		GraphObject object = GraphObject.create(triplet[2]);
		Relation relation = Relation.valueOf(triplet[1]);
		//Prevents Duplication
		if(!subject.getDown(relation).contains(object))
			subject.addConnection(relation, object);
	}
	
	//Handles strings without clearly defined connections
	private GraphObject complexTriplet(String[] triplet, String name) {
		
		//Creating relevant graph objects
		GraphObject subject = GraphObject.create(triplet[0]);
		GraphObject verb = GraphObject.create(triplet[1]);
		GraphObject object = GraphObject.create(triplet[2]);
		GraphObject newnode = GraphObject.create(name);
		
		//Connecting everything
		subject.addConnection(Relation.SUBJ, newnode);
		verb.addConnection(Relation.VERB, newnode);
		object.addConnection(Relation.OBJ, newnode);
		
		return newnode;	
	}
	
	//Splits the string into a sanitized Triplet
	private static String[] sanitize(String string) {
		
		//Initializing variables
		String[] triplet = new String[3];
		int[] spaces = new int[2];

		//Splitting the string appropriately
		boolean counting = true; int pos = 0;
		for(int i=0; i<string.length(); i++) {
			if(string.charAt(i) == ' ' && counting)
				spaces[pos++] = i;
			if(string.charAt(i) == '\'') 
				counting = !counting;
		}
		
		//Assigning substrings
		triplet[0] = string.substring(0, spaces[0]);
		triplet[1] = string.substring(spaces[0]+1, spaces[1]);
		triplet[2] = string.substring(spaces[1]+1);
		
		//Removing quotations
		for(int i=0; i<triplet.length; i++)
			if(triplet[i].contains("'"))
				triplet[i] = triplet[i].substring(1, triplet[i].length()-1);
		return triplet;
	}
	
	//Checks to see if the string is part of the Relation Enum
	private static boolean relationContains(String string) {
		for(Relation relation: Relation.values())
			if(relation.name().equals(string))
				return true;
		return false;	
	}



}
