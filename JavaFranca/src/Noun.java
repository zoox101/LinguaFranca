import java.util.ArrayList;
import java.util.HashMap;

public class Noun {
	
	public static HashMap<String, Noun> all = new HashMap<String, Noun>();
	public static Noun get(String string) {return all.get(string);}
	
	ArrayList<SuperPointer> in;
	ArrayList<SuperPointer> out;
	
	public String name;
	
	public Noun(String name) {
		this.name = name;
		this.in = new ArrayList<SuperPointer>();
		this.out = new ArrayList<SuperPointer>();
		all.put(this.name, this);
	}
	
	public boolean addConnection(Verb verb, Noun graphobject) {
		SuperPointer pointer = new SuperPointer(this, verb, graphobject);
		graphobject.in.add(pointer);
		return out.add(pointer);
	}
	
	public String toString() {
		return name;
	}

}
