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
		if(name == null) return create();
		name = name.toLowerCase();
		if(GraphObject.all.containsKey(name)) return all.get(name);
		else return new GraphObject(name);
	}

	//Adds a connection to another graph object
	public void addConnection(Relation relation, GraphObject graphobject) {
		//System.out.println("Triplet: " + this + " " + relation + " " + graphobject); //DEBUGGING
		//Add pointer one direction
		SuperPointer pointer = new SuperPointer(this, relation, graphobject);
		graphobject.in.add(pointer);
		this.out.add(pointer);
		//If EOF relationship add pointer the other direction
		if(relation == Relation.EOF) {
			SuperPointer eofpointer = new SuperPointer(graphobject, relation, this);
			graphobject.out.add(eofpointer);
			this.in.add(eofpointer);
		}
	}
	
	//Gets all the EOFS to a given object
	public ArrayList<GraphObject> eSet() {return this.searchUp(Relation.EOF, null);}
	public static ArrayList<GraphObject> eSet(ArrayList<GraphObject> list) {
		ArrayList<GraphObject> newlist = new ArrayList<GraphObject>();
		for(GraphObject object: list) { 
			ArrayList<GraphObject> currentsearch = object.searchUp(Relation.EOF, null);
			for(GraphObject searchobject: currentsearch)
				if(!newlist.contains(searchobject))
					newlist.add(searchobject);
		}
		return newlist;
	}
	
	//Gets all the objects of that relation in the equivalence set
	public ArrayList<GraphObject> getUp(Relation relation) {
		
		if(relation == Relation.EOF) return this.eSet();
		
		ArrayList<GraphObject> allobjects = new ArrayList<GraphObject>();
		//Searches over the entire equivalence set
		ArrayList<GraphObject> eset = this.eSet();
		for(GraphObject object: eset) {
			ArrayList<GraphObject> relationset = object.singleUp(relation);
			for(GraphObject relationobject: relationset)
				if(!allobjects.contains(relationobject))
					allobjects.add(relationobject);
		}
		return allobjects;
	}

	//Returns any inputs with the given relation
	private ArrayList<GraphObject> singleUp(Relation relation) {
		
		if(relation == Relation.IOF || relation == Relation.POF) 
			return searchUp(relation, null);
				
		ArrayList<GraphObject> inputs = new ArrayList<GraphObject>();
		for(SuperPointer pointer: this.in)
			if(pointer.relation == relation)
				inputs.add(pointer.in);
		return inputs;
	}
	
	//Recursive method that finds all nodes connected by the given relation
	//Call with object.searchUp(relation, null)
	private ArrayList<GraphObject> searchUp(Relation relation, ArrayList<GraphObject> objects) {
		if(objects == null) objects = new ArrayList<GraphObject>();
		if(objects.contains(this)) return objects;
		objects.add(this);
		for (SuperPointer pointer: this.in)
			if(pointer.relation == relation)
				objects = pointer.in.searchUp(relation, objects);
		return objects;
	}
	
	//Gets all the objects of that relation in the equivalence set
	public ArrayList<GraphObject> getDown(Relation relation) {
		
		if(relation == Relation.EOF) return this.eSet();
		
		ArrayList<GraphObject> allobjects = new ArrayList<GraphObject>();
		//Looks over entire equivalence set
		ArrayList<GraphObject> eset = this.eSet();
		for(GraphObject object: eset) {
			ArrayList<GraphObject> relationset = object.singleDown(relation);
			for(GraphObject relationobject: relationset)
				if(!allobjects.contains(relationobject))
					allobjects.add(relationobject);
		}
		return allobjects;
	}

	//Returns any outputs with the given relation
	private ArrayList<GraphObject> singleDown(Relation relation) {
		
		if(relation == Relation.IOF || relation == Relation.POF)
			return searchDown(relation, null);
				
		ArrayList<GraphObject> outputs = new ArrayList<GraphObject>();
		for(SuperPointer pointer: this.out)
			if(pointer.relation == relation)
				outputs.add(pointer.out);
		return outputs;
	}
	
	//Recursive method that finds all nodes connected by the given relation
	//Call with object.searchDown(relation, null)
	private ArrayList<GraphObject> searchDown(Relation relation, ArrayList<GraphObject> objects) {
		if(objects == null) objects = new ArrayList<GraphObject>();
		if(objects.contains(this)) return objects;
		objects.add(this);
		for (SuperPointer pointer: this.out)
			if(pointer.relation == relation)
				objects = pointer.out.searchDown(relation, objects);
		return objects;
	}

	//Decides if two graph objects are equal
	public boolean equals(Object that) {
		if(that instanceof GraphObject)
			if(this.name.equals(((GraphObject) that).name))
				return true;
		if(that instanceof String)
			if(this.name.equals(that))
				return true;
		return false;
	}

	//Converts the graph object to a string
	public String toString() {
		return name;
	}

}
