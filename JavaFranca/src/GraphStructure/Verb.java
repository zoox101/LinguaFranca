package GraphStructure;
import java.util.HashMap;

public class Verb extends GraphObject {

	public static HashMap<String, Verb> all = new HashMap<String, Verb>();

	protected Verb(String string){
		super(string);
		all.put(this.name, this);
	}

	//Creates a new instance of a GraphObject if it doesn't already exist
	public static Verb create(String name) {
		if(GraphObject.all.containsKey(name)) return all.get(name);
		else return new Verb(name);
	}

}
