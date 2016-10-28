import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("unused")
public class ZZZTest {
	
	public static void main(String[] args) throws IOException {
		
		
		
	}
	
	static void connect(String subject, String verb, String object) {
		Noun subjectobj = Noun.get(subject);
		Verb verbobj = Verb.get(verb);
		Noun objectobj = Noun.get(object);
		subjectobj.addConnection(verbobj, objectobj);
		}
	
	static String[] parse(String string) {
		
		string = string.toLowerCase();
		int verbposition = -1;
		String[] arraysplit = string.split(" ");
		for(int i=0; i<arraysplit.length; i++)
			if(Verb.all.containsKey(arraysplit[i]))
				verbposition = i;
		
		//Creating subject
		String subject = "";
		for(int i=0; i<verbposition; i++)
			subject += (arraysplit[i] + " ");
		subject = subject.trim();
		
		//Creating verb
		String verb = arraysplit[verbposition];
		
		//Creating object
		String object = "";
		for(int i=verbposition+1; i<arraysplit.length; i++)
			object += (arraysplit[i] + " ");
		object = object.trim();
		
		String[] output = {subject, verb, object};
		return output;
		
	}
	
}
