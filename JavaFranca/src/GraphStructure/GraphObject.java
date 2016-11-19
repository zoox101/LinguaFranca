package GraphStructure;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphObject {

	//Stores all graph objects
	public static HashMap<String, GraphObject> all = new HashMap<String, GraphObject>();
	public static HashMap<Integer, GraphObject> allid = new HashMap<Integer, GraphObject>();
	public static int idtotal = 0;

	public ArrayList<SuperPointer> in; public ArrayList<SuperPointer> out;
	public String name; public int id; 

	//Creates a graph object from a string
	protected GraphObject(String name) {
		this.name = name;
		this.in = new ArrayList<SuperPointer>();
		this.out = new ArrayList<SuperPointer>();
		this.id = idtotal++;
		all.put(this.name, this);
		allid.put(this.id, this);
	}

	//Default Constructor -- Used for unnamed graph objects
	public static GraphObject create() {
		return new GraphObject(String.valueOf(idtotal));
	}
	
	//Creates a new instance of a GraphObject if it doesn't already exist
	public static GraphObject create(String name) {
		if(GraphObject.all.containsKey(name)) return all.get(name);
		else return new GraphObject(name);
	}

	//Adds a connection to another graph object
	public boolean addConnection(Relation relation, GraphObject graphobject) {
		SuperPointer pointer = new SuperPointer(this, relation, graphobject);
		graphobject.in.add(pointer);
		return out.add(pointer);
	}
	
	//Returns any inputs with the given relation
	public ArrayList<GraphObject> getIn(Relation relation) {
		ArrayList<GraphObject> inputs = new ArrayList<GraphObject>();
		for(SuperPointer pointer: this.in)
			if(pointer.relation == relation)
				inputs.add(pointer.in);
		return inputs;
	}
	
	//Returns any outputs with the given relation
	public ArrayList<GraphObject> getOut(Relation relation) {
		ArrayList<GraphObject> outputs = new ArrayList<GraphObject>();
		for(SuperPointer pointer: this.out)
			if(pointer.relation == relation)
				outputs.add(pointer.out);
		return outputs;
	}
	
	//Recursive method that finds all nodes connected by the given relation
		public ArrayList<GraphObject> queryIn(Relation relation, ArrayList<GraphObject> objects) {
			if(objects == null) objects = new ArrayList<GraphObject>();
			objects.add(this);
			for (SuperPointer pointer: this.in)
				if(pointer.relation == relation)
					objects = pointer.in.queryIn(relation, objects);
			return objects;
		}
	
	//Recursive method that finds all nodes connected by the given relation
	public ArrayList<GraphObject> queryOut(Relation relation, ArrayList<GraphObject> objects) {
		if(objects == null) objects = new ArrayList<GraphObject>();
		objects.add(this);
		for (SuperPointer pointer: this.out)
			if(pointer.relation == relation)
				objects = pointer.out.queryOut(relation, objects);
		return objects;
	}

	//Decides if two graph objects are equal
	//TODO: Make this equals method more robust
	public boolean equals(Object that) {
		if(that instanceof GraphObject)
			if(((GraphObject) that).name == this.name)
				return true;
		return false;
	}

	//Converts the graph object to a string
	public String toString() {
		String classname = this.getClass().toString();
		String[] cnamesplit = classname.split("\\.");
		return cnamesplit[cnamesplit.length-1] + "," + name;
	}

	//Gets an appropriate graph object from a string
	public static GraphObject fromString(String string) {
		String[] strings = string.split(",");
		if(strings[0].equals("GraphObject")) return new GraphObject(strings[1]);
		if(strings[0].equals("Noun")) return new Noun(strings[1]);
		if(strings[0].equals("Verb")) return new Verb(strings[1]);
		return null;
	}

}
