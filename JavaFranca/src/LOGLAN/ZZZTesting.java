package LOGLAN;
import java.io.IOException;

import Draw.Draw;
import GraphStructure.GraphObject;

public class ZZZTesting {

	public static void main(String[] args) throws IOException {
				
		//new GraphFromFile("Data/SimpleNapoleon.txt"); 
		//new Draw(GraphObject.create("Son"));
		
		//new Function("NAME: ~POF (~POF Params[ALL] & ~IOF Name)");
		
		String[] strings = Function.parenthetical("asdf (adsfas (asdf asdf) asdfasdf asdfasdf)");
		System.out.println(strings[0] + "--" + strings[1]);
		
		
	}
}
