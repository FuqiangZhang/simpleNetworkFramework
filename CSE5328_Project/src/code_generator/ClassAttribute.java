package code_generator;

public class ClassAttribute {
	private String name;
	private String type;
	
	public ClassAttribute(String name, String type) { 
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
}
