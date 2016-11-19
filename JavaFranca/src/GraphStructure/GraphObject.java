package GraphStructure;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphObject {
	
	public static HashMap<String, GraphObject> all;
	public static GraphObject get(String string) {return all.get(string);}
	
	ArrayList<SuperPointer> in; ArrayList<SuperPointer> out;
	public String name;
	
	public GraphObject(String name) {
		this.name = name;
		this.in = new ArrayList<SuperPointer>();
		this.out = new ArrayList<SuperPointer>();
		all.put(this.name, this);
	}
	
	
	
	

}
