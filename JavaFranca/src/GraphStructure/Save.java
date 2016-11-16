package GraphStructure;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	
	public static void toFile(String filename) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
		
		writer.write("VERBS\n");
		for(String key: Verb.all.keySet())
			writer.write(Verb.all.get(key) + "\n");
		writer.write("\nNOUNS\n");
		for(String key: Noun.all.keySet())
			writer.write(Noun.all.get(key) + "\n");
		writer.write("\nPOINTERS\n");
		for(String key: Noun.all.keySet())
			for(SuperPointer point: Noun.all.get(key).in)
				writer.write(point.in + " - " + point.verb + " - " + point.out + "\n");
		
		writer.flush();
		writer.close();
	}
	
	public static void fromFile(String filename) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line = reader.readLine(); int counter = 0; 
		while((line = reader.readLine()) != null) {
			if(line.equals("")) {counter++; reader.readLine(); line = reader.readLine();}
			
			if(counter == 0)
				Verb.all.put(line, new Verb(line));
			if(counter == 1)
				Noun.all.put(line, new Noun(line));
			if(counter == 2) {
				String[] split = line.split(" - ");
				Noun subject = Noun.get(split[0]);
				Noun object = Noun.get(split[2]);
				Verb verb = Verb.get(split[1]);
				subject.addConnection(verb, object);
			}
		}
		
		reader.close();
	}

}
