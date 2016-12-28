package LOGLAN;
import java.io.IOException;
import java.util.ArrayList;
import Draw.Draw;
import GraphStructure.GraphObject;
import GraphStructure.Relation;
import LOGLAN.MicroFunction.Type;

@SuppressWarnings("unused")
public class ZZZTesting {

	public static void main(String[] args) throws IOException {
		
		ParseMain.fromFile("data/MusicAbstract.txt");
		
		//ArrayList<GraphObject> objects = new ArrayList<GraphObject>();
		//GraphObject object = GraphObject.create("to be");
		//System.out.println(object.searchUp(Relation.EOF, null));
		
		draw("the beatles");
		//run("IOF 'noun'");
				
		/*
		String string = User.getString("Query: ");
		while(!string.toLowerCase().trim().equals("exit")) {
			run(string);
			string = User.getString("Query: ");
		}
		*/

	}
	
	public static void run(String string) {
		if(string.contains(":")) {
			new MicroFunction(string);
			System.out.println("Function Accepted");
		}
		else
			System.out.println(MicroFunction.execute(string));
	}
	
	public static void draw(String string) {
		Draw draw = new Draw(GraphObject.create(string));
	}
}
