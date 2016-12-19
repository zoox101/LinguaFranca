package ParseFromFile;
import java.io.IOException;

import Draw.Draw;
import GraphStructure.GraphObject;

public class ZZZTesting {

	public static void main(String[] args) throws IOException {
				
		new GraphFromFile("Data/SimpleNapoleon.txt"); 
		new Draw(GraphObject.create("Son"));
		
	}
}
