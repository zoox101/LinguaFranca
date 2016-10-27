import java.util.HashMap;

public class AAADriver {
	
	static HashMap<String, GraphObject> abs = new HashMap<String, GraphObject>();
	
	public static void main(String[] args) {
		
		put("music");
		put("artists");
		put("genres");
		put("characteristics");
		
		connect("artists", "make", "music");
		connect("music", "comes in", "genres");
		connect("genres", "have shared", "characteristics");
		connect("music", "has", "characteristics");
		connect("artists", "specialize in", "genres");
		
		print("music");
		print("artists");
		print("genres");
		print("characteristics");
	}
	
	static void put(String name) {abs.put(name, new GraphObject(name));}
	static void connect(String subject, String verb, String object) {abs.get(subject).add(verb, abs.get(object));}
	static void print(String string) {System.out.println(abs.get(string).toSentence());}

}
