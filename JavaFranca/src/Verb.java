import java.util.ArrayList;
import java.util.HashMap;

public class Verb {
	
	public static HashMap<String, Verb> all = new HashMap<String, Verb>();
	public static Verb get(String string) {return all.get(string);}
	
	String name;
	ArrayList<ArrayList<String>> tenses = new ArrayList<ArrayList<String>>();
	
	public Verb(String name) {
		this.name = name;
		for(int i=0; i<3; i++)
			tenses.add(new ArrayList<String>());
		all.put(this.name, this);
	}
	
	public boolean is(String that) {
		if(this.name.equals(that)) return true;
		for(ArrayList<String> tense: tenses)
			if(tense.contains(that))
				return true;
		return false;
	}
	
	public boolean is(Verb that) {return this.is(that.name);}
	
	public String toString() {return name;}
	
	public boolean equals(Object that) {
		if(that instanceof Verb)
			if(this.is((Verb)that))
				return true;
		if(that instanceof String)
			if(this.is((String) that))
					return true;
		return false;
	}

}
