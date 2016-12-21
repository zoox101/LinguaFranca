package LOGLAN;
import java.io.IOException;
import Draw.Draw;
import GraphStructure.GraphObject;
import GraphStructure.Relation;
import LOGLAN.MicroFunction.Type;

@SuppressWarnings("unused")
public class ZZZTesting {

	public static void main(String[] args) throws IOException {
		
		//new Draw(GraphObject.create("Napoleon"));
		ParseMain.fromFile("Data/SimpleNapoleon.txt"); 
		run("NAME (~EOF (ADJ (FIND 'Napoleon') & IOF 'Father'))");
		
	}
	
	public static void run(String string) {
		System.out.println(MicroFunction.execute(string));
	}
}
