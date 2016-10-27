
public class SuperPointer {
	
	Noun in;
	Noun out;
	Verb verb;
	
	SuperPointer(Noun in, Verb verb, Noun out) {
		this.in = in;
		this.verb = verb; 
		this.out = out;
	}
	
	public String toString() {
		return verb + ": " + out.name;
	}

}
