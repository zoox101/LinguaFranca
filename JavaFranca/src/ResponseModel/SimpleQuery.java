package ResponseModel;

import java.util.ArrayList;

import GraphStructure.GraphObject;
import GraphStructure.Noun;
import GraphStructure.Relation;
import GraphStructure.Verb;

public class SimpleQuery {
	
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

}
