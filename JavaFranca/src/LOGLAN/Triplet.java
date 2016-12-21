package LOGLAN;

import GraphStructure.GraphObject;
import GraphStructure.Relation;

public class Triplet {
	
	public Triplet(String string) {
		String[] triplet = string.split(" ");
		GraphObject subject = GraphObject.create(triplet[0]);
		GraphObject object = GraphObject.create(triplet[2]);
		subject.addConnection(Relation.valueOf(triplet[1]), object);		
	}
	
}
