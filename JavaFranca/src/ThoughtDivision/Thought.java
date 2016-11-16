package ThoughtDivision;
import java.util.ArrayList;
import GraphStructure.*;

@SuppressWarnings("serial")
public class Thought extends ArrayList<String>{
	
	public Thought(String sentence) {
		this.addAll(split(sentence));
	}
	
	ArrayList<String> split(String sentence) {
		return ToolBox.split(sentence, Verb.all.keySet());
	}

}
