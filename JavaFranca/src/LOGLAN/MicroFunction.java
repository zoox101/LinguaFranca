package LOGLAN;

public class MicroFunction {
	public enum Type {NAME, RULE, CAT}
	
	Type type;
	String function;
	String object;
	int pointer;
		
	public MicroFunction(Type type, String function, String object) {
		this.type = type;
		this.function = function;
		this.object = object;
	}
	
	public MicroFunction (Type type, String function, int pointer) {
		this.type = type;
		this.function = function;
		this.pointer = pointer; 
	}
	
	@Override
	public String toString() {
		String string = type.toString() + " " + function + " ";
		if(object != null) return string + object;
		else return string + pointer;
	}

}
