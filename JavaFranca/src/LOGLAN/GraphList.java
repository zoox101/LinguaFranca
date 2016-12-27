package LOGLAN;
import java.util.ArrayList;
import GraphStructure.GraphObject;
import GraphStructure.Relation;

//Executes a Microfunction
public class GraphList extends ArrayList<GraphObject> {
	private static final long serialVersionUID = -9209999960520471413L;

	//Adds the graph object corresponding to the string to the set
	public GraphList(String string) {
		super();
		GraphObject object = GraphObject.create(string);
		this.addAll(object.searchUp(Relation.EOF, null));
	}
	
	//Creates a new empty GraphSet
	public GraphList() {super();}
	
	//Handles atomic operations
	public static GraphList execute(String command) {
		//Remove parenthetical -- Ex: "'the beatles'" -> "the beatles"
		if(command.charAt(0) == '\'')
			return new GraphList(command.substring(1, command.length()-1));
		else
			return MicroFunction.execute(command);
	}
	
	//Executes a command for a single set -- IOF, POF, SUBJ, etc...
	public static GraphList execute(String command, GraphList set1) {
		
		if(command.equals("PARAM") || MicroFunction.all.containsKey(command))
			return set1;
		
		//Creating a new list to store the data
		GraphList newlist = new GraphList();
		
		//Searching up
		if(command.charAt(0) == '~') {
			command = command.substring(1, command.length());
			Relation relation = Relation.valueOf(command);
			for(GraphObject object: set1)
				newlist.addAll(object.getDown(relation));
		}
		//Searching down
		else {
			Relation relation = Relation.valueOf(command);
			for(GraphObject object: set1)
				newlist.addAll(object.getUp(relation));
		}
		
		//Running EOF on all objects
		GraphList returnlist = new GraphList();
		for(GraphObject object: newlist)
			returnlist.addAll(object.searchUp(Relation.EOF, null));
		
		return newlist;
	}
	
	//AND/OR sets together. EX: "'Bands' & 'English'"
	public static GraphList execute(String command, GraphList set1, GraphList set2) {
		
		//And sets together
		if(command.equals("AND")) {
			GraphList newlist = new GraphList();
			for(GraphObject object: set1)
				if(set2.contains(object))
					newlist.add(object);
			return newlist;
		}
		
		//Or sets together
		if(command.equals("OR")) {
			GraphList newlist = new GraphList();
			newlist.addAll(set1);
			for(GraphObject object: set2)
				if(!newlist.contains(object))
					newlist.add(object);
			return newlist;
		}
			
		return null;
	}
	
	

}
