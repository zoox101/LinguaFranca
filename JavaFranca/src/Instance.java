
public class Instance extends GraphObject {
	private static final long serialVersionUID = 7567653108793116251L;
	
	GraphObject abstraction;

	public Instance(String name, GraphObject abstraction) {
		super(name);
		this.abstraction = abstraction;
	}
	
	

}
