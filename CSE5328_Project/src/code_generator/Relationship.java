package code_generator;

public class Relationship {
        public static String ASSOCIATION = "Association";
        public static String INHERITANCE = "Inheritance";
        public static String AGGREGATION = "Aggregation";
    
	private String name;
	private String type;
	private String source;
	private String destination;
	private String sourceRoleName;
	private String destinationRoleName;
	
	// Multiplicities are String instead of int to easily represent
	// one-to-many types of relationships
	private String sourceMultiplicity;
	private String destinationMultiplicity;
	
	public Relationship(String name, String type, String source, String destination,
			String sourceRole, String destRole, String sourceMult, String destMult) {
		this.name = name;
		this.type = type;
		this.source = source;
		this.destination = destination;
		this.sourceRoleName = sourceRole;
		this.destinationRoleName = destRole;
		this.sourceMultiplicity = sourceMult;
		this.destinationMultiplicity = destMult;
	}
        
        public String getType() {
            return type;
        }

    String getSource() {
        return source;
    }
    
    String getDestination() {
        return destination;
    }
        
}
