package code_generator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds information about an object oriented program.
 *
 */
public class Program {
	private String name;
	private List<ProgramClass> classes;
	private List<Relationship> relationships;
	
	public Program(String name, List<ProgramClass> classes) {
		this.name = name;
		this.classes = classes;
		this.relationships = new ArrayList<Relationship>();
	}
	
	public Program(String name) {
		this.name = name;
		this.classes = new ArrayList<ProgramClass>();
		this.relationships = new ArrayList<Relationship>();
	}
	
	public List<ProgramClass> getClasses() {
		return classes;
	}


    void addRelationship(Relationship relationship) {
        if(relationship.getType().equals(Relationship.AGGREGATION)) {
            for(ProgramClass _class : classes) {
                if(_class.getName().equals(relationship.getDestination())) {
                    ClassAttribute attr = new ClassAttribute(
                                relationship.getSource().toLowerCase(), relationship.getSource());
                    _class.addAttribute(attr);
                }
            }
        }
        
        else if(relationship.getType().equals(Relationship.INHERITANCE)) {
            int sourceIndex = -1;
            int destIndex = -1;
            System.out.println("source = " + relationship.getSource());
            System.out.println("dest = " + relationship.getDestination());

            for(int i = 0; i < classes.size(); i++) {
                if(classes.get(i).getName().equals(relationship.getSource())) {
                    sourceIndex = i;
                    System.out.println("found source");
                }
                if(classes.get(i).getName().equals(relationship.getDestination())) {
                    destIndex = i;
                    System.out.println("found dest");
                }
            }
            if(sourceIndex != -1 && destIndex != -1) {
                if(classes.get(destIndex).getType().equals(ProgramClass.INTERFACE)) {
                    classes.get(sourceIndex).addInterface(classes.get(destIndex).getName());
                    System.out.println("added interface");
                }
                else {
                    classes.get(sourceIndex).addSuperClass(classes.get(destIndex).getName());
                    System.out.println("added superclass");
                }
            }
        }
        else {
            // do nothing
        }
    }
    
	
}
