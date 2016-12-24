package LOGLAN;
import java.io.IOException;
import Draw.Draw;
import GraphStructure.GraphObject;
import GraphStructure.Relation;
import LOGLAN.MicroFunction.Type;
import ZZZOld.User;

@SuppressWarnings("unused")
public class ZZZTesting {

	public static void main(String[] args) throws IOException {
		
		//new Draw(GraphObject.create("Napoleon"));
		//ParseMain.fromFile("Data/SimpleNapoleon.txt");
		
		//(IOF 'albums' | IOF 'songs') & OBJ (~SUBJ 'the beatles' & ~VERB 'made')
		
		ParseMain.fromFile("data/MusicTest.txt");
		//draw("let it be", 30);
		
		String string = User.getString("Query: ");
		while(!string.toLowerCase().trim().equals("exit")) {
			run(string);
			string = User.getString("Query: ");
		}

	}
	
	public static void run(String string) {
		if(string.contains(":")) {
			new MicroFunction(string);
			System.out.println("Function Accepted");
		}
		else
			System.out.println(MicroFunction.execute(string));
	}
	
	public static void draw(String string, int value) {
		Draw draw = new Draw(GraphObject.create(string));
	}
}
