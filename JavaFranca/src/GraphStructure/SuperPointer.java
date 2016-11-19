package GraphStructure;

public class SuperPointer {
	
	public GraphObject in;
	public GraphObject out;
	public Relation relation;
	
	SuperPointer(GraphObject in, Relation relation, GraphObject out) {
		this.in = in;
		this.relation = relation; 
		this.out = out;
	}
	
	public String toString() {
		return relation + ": " + out.name;
	}
	
}
