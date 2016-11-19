package GraphStructure;
import java.util.HashMap;

public class Noun extends GraphObject {

	public static HashMap<String, Noun> all = new HashMap<String, Noun>();

	protected Noun(String string) {
		super(string); 
		all.put(this.name, this);
	}

	//Creates a new instance of a GraphObject if it doesn't already exist
	public static Noun create(String name) {
		if(GraphObject.all.containsKey(name)) return all.get(name);
		else return new Noun(name);
	}

}
