package edu.uta.designclass.sequencediagramcomponent;

public class ClassObject extends SDElement{
	private int id;
	private String name;
	private String type;
	private String pattern_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String nameString) {
		this.name = nameString;
	}
	public String getType() {
		return type;
	}
	public void setType(String typeString) {
		this.type = typeString;
	}
	public String getPattern_name() {
		return pattern_name;
	}
	public void setPattern_name(String pattern_nameString) {
		this.pattern_name = pattern_nameString;
	}
	@Override
	public String toString() {
		return "ClassObject [id=" + id + ", name=" + name + ", type=" + type
				+ ", pattern_name=" + pattern_name + "]";
	}
	
}
