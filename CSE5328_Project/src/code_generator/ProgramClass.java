package code_generator;

import java.util.ArrayList;
import java.util.List;

public class ProgramClass {
        public static final String INTERFACE = "Interface";
        public static final String CONCRETE = "Concrete";
	public static final String ABSTRACT = "Abstract";
    
	private String name;
	private String type; // Interface/Concrete/Abstract
	private List<ClassAttribute> attributes;
	private List<ProgramMethod> methods;
	private List<String> superClass;
	private List<String> implementedInterfaces;
	
	public ProgramClass(String name, String type, List<ClassAttribute> attributes, List<ProgramMethod> methods) {
		this.name = name;
		this.type = type;
		this.attributes = attributes;
		this.methods = methods;
		this.superClass = new ArrayList<String>();
		this.implementedInterfaces = new ArrayList<String>();
	}
        
        public void addAttribute(ClassAttribute attr) {
            this.attributes.add(attr);
        }
        
        public void addSuperClass(String s) {
            superClass.add(s);
        }
        
        public void addInterface(String i) {
            implementedInterfaces.add(i);
        }
        
        public List<String> getSuperClass() {
            return superClass;
        }
        
        public List<String> getInterface() {
            return implementedInterfaces;
        }
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public List<ClassAttribute> getAttributes() {
		return attributes;
	}
	
	public List<ProgramMethod> getMethods() {
		return methods;
	}
	
}