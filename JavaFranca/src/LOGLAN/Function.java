package LOGLAN;

public class Function {
	
	String name;
	
	public Function(String function) {
		
		//NAME: ~POF (~POF Params[ALL] & ~IOF "Name")
		String[] split = function.split("\\(|\\)|: ");
		name = split[0];
		
		for(String string: split)
			System.out.println(string);
		
		
	}
	
	//Strings given in the form: "(...)..."
	private String match(String string) {
		
		for()
		
	}

}
