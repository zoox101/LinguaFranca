package GraphStructure;
import java.util.ArrayList;
import java.util.Set;

public class ToolBox {

	//Splits an arraylist of strings by any words in the collection
	public static ArrayList<String> split(String line, Set<String> collection) {
		ArrayList<String> fragments = new ArrayList<String>();
		String fragment = ""; 
		String[] split = line.split(" ");
		for(int j=0; j<split.length; j++) {
			if(collection.contains(split[j])) {
				fragments.add(fragment.trim());
				fragments.add(split[j].trim());
				fragment = "";
			}
			else fragment += (split[j] + " ");
		}
		fragments.add(fragment.trim());
		return fragments;
	}

}
