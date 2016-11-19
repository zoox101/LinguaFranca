package ResponseModel;

import java.util.ArrayList;
import java.util.HashMap;

import GraphStructure.GraphObject;
import GraphStructure.Relation;

public class SimpleQuery {
	
	public SimpleQuery(String string) {
		
		String[] keys = string.toLowerCase().trim().split(" ");
		ArrayList<GraphObject> objects = new ArrayList<GraphObject>();
		for(int i=0; i<keys.length; i++)
			if(GraphObject.all.containsKey(keys[i]))
				objects.add(GraphObject.all.get(keys[i]));
		
		HashMap<GraphObject, Integer> tally = new HashMap<GraphObject, Integer>();
		for(GraphObject object: objects) {
			ArrayList<GraphObject> synonyms = object.queryOut(Relation.UUU, null);
			for(GraphObject synonym: synonyms) {
				if(!tally.containsKey(synonym)) tally.put(synonym, 1);
				else tally.put(synonym, tally.get(synonym)+1);
			}
		}
		
		GraphObject maxobject = null; int max = 0;
		for(GraphObject key: tally.keySet()) {
			if(tally.get(key) > max) {
				maxobject = key;
				max = tally.get(key);
			}
		}
		
		System.out.println(max);
		System.out.println(maxobject + " -- " + maxobject.in + " -- " + maxobject.out );
		
		GraphObject thought = maxobject.getOut(Relation.OBJ).get(0);
		GraphObject subject = thought.getIn(Relation.SUBJ).get(0);
		System.out.println(subject.queryIn(Relation.UUU, null));
		
	}
	
	/*
	//In the form of Who Verb Object
	public SimpleQuery(String string) {
		
		String[] strings = string.split(" ");
		String verbkey = strings[1];
		
		String objectkey = "";
		for(int i=2; i<strings.length; i++)
			objectkey += strings[i] + " ";
		objectkey = objectkey.trim();
		
		Verb verb = Verb.create(verbkey);
		Noun noun = Noun.create(objectkey);
		
		ArrayList<GraphObject> verbouts = verb.getOut(Relation.VERB);
		ArrayList<GraphObject> nounouts = noun.getOut(Relation.OBJ);
		
		ArrayList<GraphObject> combinedouts = new ArrayList<GraphObject>();
		for(GraphObject object: verbouts)
			if(nounouts.contains(object))
				combinedouts.add(object);
		
		if(combinedouts.size() != 0) {
			GraphObject object = combinedouts.get(0);
			GraphObject subject = object.getIn(Relation.SUBJ).get(0);
			System.out.println(subject.name);
		}
		else System.out.println("Unknown");
		
	}
	*/

}
