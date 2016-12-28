package ETLTranslation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import LOGLAN.GraphList;
import LOGLAN.MicroFunction;
import LOGLAN.Triplet;

public class ETLRules extends ArrayList<String> {
	private static final long serialVersionUID = 1792362341638722831L;

	ArrayList<String[]> rules;
	ArrayList<String[]> results;

	//Creates a data structure containing the results and commands
	public ETLRules() {
		super();		
		this.rules = new ArrayList<String[]>();
		this.results = new ArrayList<String[]>();
	}

	//Creates an ETLRules object from an ArrayList of strings
	public ETLRules(ArrayList<String> strings) {
		this();
		for(String string: strings) this.add(string);
	}

	//Gets the rules from a file
	public static ETLRules fromFile(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArrayList<String> lines = new ArrayList<String>(); String line;
		while((line = reader.readLine()) != null) lines.add(line.trim());
		reader.close();
		return new ETLRules(lines);
	}

	//Applies rules to the text in a file
	public void executeFile(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line; String corpus = "";
		while((line = reader.readLine()) != null) corpus += line;
		reader.close();
		this.execute(corpus);
	}

	@Override
	public boolean add(String string) {
		super.add(string);
		rules.add(string.split(" -> ")[0].split(" \\+ "));
		results.add(string.split(" -> ")[1].split(" \\+ "));
		return true;
	}

	//Runs all the rules on a corpus of text.
	public void execute(String string) {

		//Splits the sentences apart
		String[] sentences = string.split("\\.");

		//Run all the rules on all the sentences
		for(int i=0; i<sentences.length; i++)
			for(int j=0; j<this.size(); j++) 
				this.execute(rules.get(j), results.get(j), sentences[i].trim());
	}

	//Runs a single rule on a single sentence
	private void execute(String[] rule, String[] result, String sentence) {

		ArrayList<String> memory = new ArrayList<String>();
		String[] words = sentence.split(" ");
		int rulecount = 0; String word = "";

		//Removing all comma "," instances
		for(int i=0; i<words.length; i++)
			if(words[i].charAt(words[i].length()-1) == ',')
				words[i] = words[i].substring(0, words[i].length()-1);

		//Loop over each word
		for(int i=0; i<words.length; i++) {

			word += words[i] + " ";
			if(rulecount == rule.length) break;
			ArrayList<String> subrule = split(rule[rulecount]);

			//System.out.println(subrule.get(0) + " -- " + word.trim());
			//If the rule fits...
			if(fits(subrule.get(0), word.trim())) {

				//Remembering the string if required
				int memoryindex = counter(subrule.get(subrule.size()-1));
				if(memoryindex != -1) {
					while(memory.size() < memoryindex+1)
						memory.add("");
					String memorystring = memory.get(memoryindex) + word.trim() + " ";
					memory.set(memoryindex, memorystring);
				}

				word = ""; rulecount++;

				//If the next word fits the current rule and *, run the rule again.
				if(subrule.contains("*") && i+1 != words.length && fits(subrule.get(0), words[i+1])) rulecount--;

			}
		}

		if(rulecount != rule.length) return;
		//The string matched the rule
		for(int i=0; i<result.length; i++)
			ETLRules.formTriplets(result[i].substring(1, result[i].length()-1), memory);
	}

	//Splits a string w/o disrupting the parentheses
	public static ArrayList<String> split(String string) {

		boolean counting = true;
		int parencounter = 0;
		ArrayList<Integer> spaces = new ArrayList<Integer>();

		spaces.add(-1);
		//Finding all the spaces outside the parentheses
		for(int i=0; i<string.length(); i++) {
			if(string.charAt(i) == '\'') counting = !counting;
			if (counting) {
				if(string.charAt(i) == ' ' && parencounter == 0) spaces.add(i);
				if(string.charAt(i) == '(' || string.charAt(i) == '{') parencounter++;
				if(string.charAt(i) == ')' || string.charAt(i) == '}') parencounter--;
			}
		}
		spaces.add(string.length());

		//Creating array of strings
		ArrayList<String> strings = new ArrayList<String>();
		for(int i=0; i<spaces.size()-1; i++) 
			strings.add(string.substring(spaces.get(i)+1, spaces.get(i+1)));

		return strings;
	}

	//Gets the counter out of a string like [3]. Invalid strings return -1.
	private static int counter(String string) {
		if(string.charAt(0) == '[') {
			string = string.substring(1, string.length()-1);
			if(string.contains("i")) 
				string = string.substring(0, string.length()-1);
			return Integer.parseInt(string);
		}
		return -1;
	}

	//Determine if the word fits the given rule
	private boolean fits(String rule, String string) {

		//Gets the first character and removes the parentheses
		char firstchar = rule.charAt(0);
		rule = rule.substring(1, rule.length()-1);

		//If the first character is "(" execute the LOGLAN function
		if(firstchar == '(') {
			MicroFunction function = new MicroFunction(rule);
			GraphList set = function.execute();
			return set.contains(string);
		}

		//If the first character is "{" execute the JAVA function
		if(firstchar == '{') {
			return StringFunctions.execute(rule, string);
		}

		return false; 
	}

	//Replaces all the variables of the form "[1]" and creates triplets
	private static void formTriplets(String result, ArrayList<String> memory) {
		
		ArrayList<ArrayList<String>> triplet = new ArrayList<ArrayList<String>>();
		ArrayList<String> split = split(result);
		
		//Creates a containing all valid interpretations of the string
		for(int i=0; i<split.size(); i++) {
			triplet.add(new ArrayList<String>());
			//If the string is pointing to a memory location...
			if(counter(split.get(i)) != -1) {
				//Get the string from that location...
				String memstring = memory.get(counter(split.get(i))).trim();
				//If the string is of the form "[1i]", split the memory string
				if(split.get(i).contains("i")) {
					String[] memsplit = memstring.split(" ");
					for(int j=0; j<memsplit.length; j++)
						triplet.get(i).add("'" + memsplit[j] + "'");
				}
				//Otherwise just add the string
				else triplet.get(i).add("'" + memstring + "'");
			}
			//Otherwise just add the string
			else triplet.get(i).add(split.get(i));
		}

		//Creating triplets from matrix
		for(String subject: triplet.get(0))
			for(String verb: triplet.get(1))
				for(String object: triplet.get(2)) {
					System.out.println("Triplet: " + subject + " " + verb + " " + object);
					new Triplet(subject + " " + verb + " " + object);
				}
		
	}

}
