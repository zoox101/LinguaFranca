package LOGLAN;
import java.util.HashMap;


public class MicroFunction {

	public enum Type {NAME, PARENTHETICAL, CONCATENATION, DUPLET, ATOM};
	public static HashMap<String, MicroFunction> all = new HashMap<String, MicroFunction>();

	Type type;
	MicroFunction func1;
	MicroFunction func2;
	String string;

	static int idcounter = 0;
	int id;

	//Creates a new MicroFunction from a string
	public MicroFunction(String string) {

		this.id = idcounter++;
		this.type = identify(string);
		//System.out.println(string + " -- " + type);

		if(type == Type.NAME) {
			String[] split = name(string);
			this.string = split[0];
			this.func1 = new MicroFunction(split[1]);
			MicroFunction.all.put(split[0], new MicroFunction(split[1]));
		}

		if(type == Type.PARENTHETICAL) {
			String[] split = parenthetical(string);
			this.string = split[0];
			this.func1 = new MicroFunction(split[1]);
			if(MicroFunction.all.containsKey(this.string)) {
				this.func1 = MicroFunction.all.get(this.string);
				this.replaceParam(split[1]);
			}
		}

		if(type == Type.CONCATENATION) {
			String[] split = concatenation(string);
			this.string = split[0];
			this.func1 = new MicroFunction(split[1]);
			this.func2 = new MicroFunction(split[2]);
		}

		if(type == Type.DUPLET) {
			String[] split = string.split(" ");
			this.string = split[0];
			this.func1 = new MicroFunction(split[1]);
			if(MicroFunction.all.containsKey(this.string)) {
				this.func1 = MicroFunction.all.get(this.string);
				this.replaceParam(split[1]);
			}
		}

		if(type == Type.ATOM) {
			this.string = string;
		}
	}
		
	//Replacing all instances of PARAM
	public void replaceParam(String param) {
		
		if(this.func1 != null) {
			this.func1.replaceParam(param);
			if(this.func2 != null)
				this.func2.replaceParam(param);
		}	
		else if(this.string.equals("PARAM")) {
			this.type = Type.DUPLET;
			this.func1 = new MicroFunction(param);
		}
		
	}

	//Executes the function given by the string
	public static GraphList execute(String string) {
		String[] split = string.split(" ");
		if(split.length == 1)
			return MicroFunction.all.get(string).execute();
		else {
			MicroFunction function = new MicroFunction(string);
			return function.execute();
		}
	}

	//Execute a given GraphList object
	public GraphList execute() {

		if(type == Type.NAME) 
			return func1.execute();

		if(type == Type.PARENTHETICAL) 
			return GraphList.execute(string, func1.execute());

		if(type == Type.CONCATENATION)
			return GraphList.execute(string, func1.execute(), func2.execute());

		if(type == Type.DUPLET)
			return GraphList.execute(string, func1.execute());

		if(type == Type.ATOM)
			return GraphList.execute(string);

		return null;
	}

	//Identifies the next operation to parse
	private static Type identify(String string) {

		if(length(string) == 1)
			return Type.ATOM;
		else if(length(string) != 2) {
			if(string.contains(":")) return Type.NAME;
			else return Type.CONCATENATION;
		}
		else {
			if(string.contains("(")) return Type.PARENTHETICAL;
			return Type.DUPLET;
		}		
	}

	//Returns the length of the given function
	private static int length(String string) {
		int counter = 1;
		for(int i=0; i<string.length(); i++) {
			if(string.charAt(i) == ' ')
				counter++;
			else if(string.charAt(i) == '(') {
				int parencount = 1;
				while(parencount != 0) {
					i++;
					if(string.charAt(i) == '(') parencount++;
					if(string.charAt(i) == ')') parencount--;
				}
			}
		}
		return counter;
	}

	//Returns the first instance of a character in a string
	private static int getFirst(char character, String string) {
		int location = Integer.MAX_VALUE;
		for(int i=0; i<string.length(); i++)
			if(string.charAt(i) == character && location == Integer.MAX_VALUE)
				location = i;
		return location;
	}

	//Pulls off the header
	private static String[] name(String string) {

		//Finding the location of the first colon
		int colon = getFirst(':', string);

		//Splitting the string and creating the substrings
		String[] split = new String[2];
		split[0] = string.substring(0, colon);
		split[1] = string.substring(colon+2, string.length());
		return split;
	}

	//Returns the strings in a parenthetical statement
	private static String[] parenthetical(String string) {

		String[] split = new String[2];
		int space = getFirst(' ', string);
		split[0] = string.substring(0, space);
		String second = string.substring(space+1, string.length());

		int counter = 1;
		for(int i=1; i<second.length(); i++) {
			if(second.charAt(i) == '(')
				counter++;
			if(second.charAt(i) == ')')
				counter--;
			if(counter == 0) {
				split[1] = second.substring(1, i);
				break;
			}
		}
		return split;
	}

	//Returns the first concatenation of a string
	private static String[] concatenation(String string) {

		String[] split = new String[3];

		//Finding the correct place for the & or | symbol
		int place = Integer.MAX_VALUE;
		int parencounter = 0;
		for(int i=0; i<string.length(); i++) {
			if(string.charAt(i) == '(')
				parencounter++;
			if(string.charAt(i) == ')')
				parencounter--;
			if((string.charAt(i) == '|' || string.charAt(i) == '&') && 
					parencounter == 0 && place == Integer.MAX_VALUE)
				place = i;
		}

		if(string.charAt(place) == '&') split[0] = "AND";
		if(string.charAt(place) == '|') split[0] = "OR";
		split[1] = string.substring(0, place-1);
		split[2] = string.substring(place+2, string.length());
		return split;
	}

	@Override
	//Returns all dependent MicroFunctions
	public String toString() {
		String retstring = "";
		if(func1 != null && func2 != null) {
			retstring += this.id + ": " + string + "\t" + func1.id + "\t" + func2.id;
			retstring += "\n" + func1.toString();
			retstring += "\n" + func2.toString();
		}
		else if(func1 != null) {
			retstring += this.id + ": " + string + "\t" + func1.id;
			retstring += "\n" + func1.toString();
		}
		else {
			retstring += this.id + ": " + string;
		}
		return retstring;
	}

}
