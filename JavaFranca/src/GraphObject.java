import java.util.ArrayList;

public class GraphObject extends ArrayList<SuperPointer> {
	private static final long serialVersionUID = 76900595998536212L;
	
	public String name;
	public ArrayList<String> nicknames; 
	
	public GraphObject(String name) {
		this.name = name;
		this.nicknames = new ArrayList<String>();
	}
	
	public boolean add(String type, GraphObject graphobject) {
		return this.add(new SuperPointer(graphobject, type));
	}
	
	public boolean addNick(String nickname) {
		return nicknames.add(nickname);
	}
	
	public String toString() {
		String string = name + " - " + nicknames;
		for(SuperPointer pointer: this)
			string += ("\n" + pointer);
		return string;
	}
	
	//REMOVE THIS???
	public String toSentence() {
		String string = "";
		for(SuperPointer pointer: this)
			string += this.name + " " + pointer.type + " " + pointer.graphobject.name + "\n";
		return string;
	}

}
