
public class ZZZTest {
	
	public static void main(String[] args) {
		
	}
	
	/*
	public static void main(String[] args) {
		
		new GraphObject("album");
		new GraphObject("band");
		new Verb("made");
		new Verb("are");
		new Verb("is");
		new Verb("inspired");
		connect("band", "made", "album");
		
		//Subject verb object
		String input;
		while((input = User.getString("User: ")).toLowerCase() != "exit") {
			
			String[] sentence = parse(input);
			String subject = sentence[0];
			String verb = sentence[1];
			String object = sentence[2];
			
			if(!inst.containsKey(subject)) {
				System.out.println("Robby: What is " + subject + "?");
				String[] response = parse(User.getString("User: "));
				String abstraction = response[2];
				String[] split = abstraction.split(" ");
				Instance instance = new Instance(subject);
				inst.put(instance.name, instance);
				GraphObject.all.get(split[split.length-1]).addInstance(instance);
				System.out.println("Robby: Ok, so " + subject + " is a " + inst.get(subject).abstraction + ".");
			}
			
			if(!inst.containsKey(object)) {
				System.out.println("Robby: What is " + object + "?");
				String[] response = parse(User.getString("User: "));
				String abstration = response[2];
				String[] split = abstration.split(" ");
				Instance instance = new Instance(object);
				inst.put(instance.name, instance);
				GraphObject.all.get(split[split.length-1]).addInstance(instance);
				System.out.println("Robby: Ok, so " + object + " is a " + inst.get(object).abstraction + ".");
			}
			
			boolean contains = false;
			for(SuperPointer pointer: inst.get(subject).abstraction.out)
				if(pointer.verb.name.equals(verb))
					contains = true;
			if(!contains) {
				System.out.println("Robby: Can " + inst.get(subject).abstraction + " " + verb + " " + inst.get(object).abstraction);
				if(User.getBoolean("User: ")) 
					GraphObject.all.get(subject).addConnection(new Verb(verb), GraphObject.all.get(object));
			}
			
			System.out.println("Robby: Cool, the " + 
					inst.get(subject).abstraction + " " + inst.get(subject) + " " + verb + " the " + 
					inst.get(object).abstraction + " " + inst.get(object) + ".");
		}
	}
	
	static void connect(String subject, String verb, String object) {
		GraphObject subjectobj = GraphObject.get(subject);
		Verb verbobj = Verb.get(verb);
		GraphObject objectobj = GraphObject.get(object);
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
	*/
	
}
