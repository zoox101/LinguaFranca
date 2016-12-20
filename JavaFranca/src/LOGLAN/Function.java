package LOGLAN;

import java.util.ArrayList;

public class Function {
	public enum Type {NAME, PARENTHETICAL, CONCATENATION, DUPLET, ATOM};
	
	//TODO Concatenation & Duplet
	//TODO This should probably be done recursively
	public Function(String string) {
		
		int counter = 0; 
		ArrayList<MicroFunction> microfunctions = new ArrayList<MicroFunction>();
		
		do {
			
			String[] microfunction;
			Type type = identify(string);
			
			if(type == Type.NAME)
				microfunction = name(string);
			else if(type == Type.PARENTHETICAL)
				microfunction = parenthetical(string);
			else 
				microfunction = null;
			
			if(identify(microfunction[1]) != Type.ATOM) {
				counter++;
				microfunctions.add(new MicroFunction(type, microfunction[0], counter))
			}
				
			
			new MicroFunction()
			
			
		} while(microfunctions.get(counter) != null);
	}
	
	public retFunction() {
		
	}
	
	//Identifies the next operation to parse
	public static Type identify(String string) {
		
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
	
	//Returns the length of the current function
	public static int length(String string) {
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
	
	//Pulls off the header
	public static String[] name(String string) {
		
		//Finding the location of the first colon
		int colon = getFirst(':', string);
		
		//Splitting the string and creating the substrings
		String[] split = new String[2];
		split[0] = string.substring(0, colon);
		split[1] = string.substring(colon+2, string.length());
		return split;
	}
	
	//Returns the strings in a parenthetical statement
	public static String[] parenthetical(String string) {
		
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
	
	//Returns the first instance of a character in a string
	public static int getFirst(char character, String string) {
		int location = -1;
		for(int i=0; i<string.length(); i++)
			if(string.charAt(i) == character && location == -1)
				location = i;
		return location;
	}

}
