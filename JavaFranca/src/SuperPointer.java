
public class SuperPointer {
	
	GraphObject graphobject;
	String type;
	
	SuperPointer(GraphObject graphobject, String type) {
		this.graphobject = graphobject;
		this.type = type; 
	}
	
	public String toString() {
		return type + ": " + graphobject.name;
	}

}
