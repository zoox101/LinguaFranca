package ParseFromFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import GraphStructure.GraphObject;
import GraphStructure.Relation;

public class GraphFromFile extends ArrayList<GraphObject> {
	private static final long serialVersionUID = 5534540815875830673L;
	
	public GraphFromFile(String filename) throws IOException {
		
		//Getting strings from file
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArrayList<String> lines = new ArrayList<String>(); String line;
		while((line = reader.readLine()) != null)
			lines.add(line);
		reader.close();
		
		//Parsing Triplet
		for(String string: lines) {
			String[] triplet = string.split(" ");
			GraphObject subject = GraphObject.create(triplet[0]);
			GraphObject object = GraphObject.create(triplet[2]);
			subject.addConnection(Relation.valueOf(triplet[1]), object);
			if(!this.contains(subject)) this.add(subject);
			if(!this.contains(object)) this.add(object);
		}
		
	}
	
}
