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
		this.add(GraphObject.create(string));
	}
	
	//Creates a new empty GraphSet
	public GraphList() {super();}
	
	//Creates a new GraphSet from the string passed
	public static GraphList execute(String command) {
		if(command.charAt(0) == '\'')
			return new GraphList(command.substring(1, command.length()-1));
		else
			return MicroFunction.execute(command);
	}
	
	//Executes a command for a single set
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
		
		return newlist;
	}
	
	//Chooses the correct operation and executes the command
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
