package code_generator;
/* todo: The programs shouldnt have relationships anymore they should simply have classes
 *      and the classes should illustrate the relationships in the code.
 *
 *      *Error checking**
 */
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	private final String DCD_TAG = "classdiagram";
	private final String CLASS_TAG = "class";
	private final String ATTRIBUTE_TAG = "attribute";
	private final String METHOD_TAG = "method";
	private final String PARAMETER_TAG = "parameter";
	private final String NAME_TAG = "name";
	private final String TYPE_TAG = "type";
	private final String RETURN_TYPE_TAG = "returntype";
	
	private final String RELATIONSHIP_TAG = "relationship";
	private final String SOURCE_TAG = "source";
	private final String DEST_TAG = "destination";
	private final String SOURCE_ROLE_TAG = "sourcerolename";
	private final String DEST_ROLE_TAG = "destinationrolename";
	private final String SOURCE_MULT_TAG = "sourcemultiplicity";
	private final String DEST_MULT_TAG = "destinationmultiplicity";
	
	public XMLParser() {
		System.out.println("Parser constructed");
	}
	
	/**
	 * Parses an XML file and returns a Program object based on the data in the XML file.
	 * @param filename The filename of the XML file to parse.
	 * @return Program object that contains the data represented by the XML file.
	 */
	public Program parse(String filename) {
		String programName = null;
		ArrayList<ProgramClass> classes = new ArrayList<ProgramClass>();
		ArrayList<Relationship> relationships = new ArrayList<Relationship>();
		System.out.println("parse variables initialized");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File(filename);
			if(file.exists()) {
	  			System.out.println("file opened");
				Document doc = db.parse(file);
				System.out.println("Document object created");
				Element docEle = doc.getDocumentElement();
				
				// Print root element of the document
				System.out.println("Root element of the document: "
						+ docEle.getNodeName());
				
				System.out.println("Building program...");
				// Get program name
				programName = docEle.getAttribute(NAME_TAG);
				System.out.println("    program name = " + programName);
				
				// Get the classes in the program
				NodeList classList = docEle.getElementsByTagName(CLASS_TAG);
				if(classList != null && classList.getLength() > 0) {
					for(int i = 0; i < classList.getLength(); i++) {
						Node node = classList.item(i);
						
						if(node.getNodeType() == Node.ELEMENT_NODE) {
							Element classElement = (Element) node;
							ProgramClass tempClass = buildClass(classElement);
							classes.add(tempClass);
						}
						
					}
				}
				
				// Get the relationships in the program
				NodeList relList = docEle.getElementsByTagName(RELATIONSHIP_TAG);
				if(relList != null && relList.getLength() > 0) {
					for(int i = 0; i < relList.getLength(); i++) {
						Node node = relList.item(i);
						
						if(node.getNodeType() == Node.ELEMENT_NODE) {
							Element relElement = (Element) node;
							Relationship tempRelationship = buildRelationship(relElement);
							relationships.add(tempRelationship);
						}
						
					}
				}
				
			}
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		Program program = new Program(programName, classes);
                for(Relationship relationship : relationships) {
                    System.out.println("Adding " + relationship.getType() + " relationship.");
                    program.addRelationship(relationship);
                }
		return new Program(programName, classes);
	}
	
	/**
	 * Builds a Method object out of data in an XML element node.
	 * @param el The element node that has data representing a method.
	 * @return A Method object built from the data in the XML element.
	 */
	private ProgramMethod buildMethod(Element el) {
		System.out.println("Building method...");
		String name = el.getAttribute(NAME_TAG);
		System.out.println("    name = " + name);
		String returnType = el.getAttribute(RETURN_TYPE_TAG);
		System.out.println("    return type = " + returnType);
		
		ArrayList<String> params  = new ArrayList<String>();
		NodeList paramList = el.getElementsByTagName(PARAMETER_TAG);
		if(paramList != null && paramList.getLength() > 0) {
			for(int i = 0; i < paramList.getLength(); i++) {
				Node node = paramList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element attrElement = (Element) node;
					String param = buildParameter(attrElement);
					params.add(param);
				}
			}
		}
		
		return new ProgramMethod(name, returnType, params);
	}
	
	/**
	 * Builds a Relationship object out of data in an XML element node.
	 * @param el The element node that has data representing a relationship.
	 * @return A Relationship object built from the data in the XML element.
	 */
	private Relationship buildRelationship(Element el) {
		System.out.println("========================");
		System.out.println("Building relationship...");
		String name = el.getAttribute(NAME_TAG);
		System.out.println("    name = " + name);
		String type = el.getAttribute(TYPE_TAG);
		System.out.println("    type = " + type);
		String source = getNodeValue(SOURCE_TAG, el);
		System.out.println("    source = " + source);
		String destination = getNodeValue(DEST_TAG, el);
		System.out.println("    destination = " + destination);
		String sourceRole = getNodeValue(SOURCE_ROLE_TAG, el);
		System.out.println("    source role name = " + sourceRole);
		String destRole = getNodeValue(DEST_ROLE_TAG, el);
		System.out.println("    destination role name = " + destRole);
		String sourceMult = getNodeValue(SOURCE_MULT_TAG, el);
		System.out.println("    source multiplicity = " + sourceMult);
		String destMult = getNodeValue(DEST_MULT_TAG, el);
		System.out.println("    destination multiplicity = " + destMult);
		
		return new Relationship(name, type, source, destination,
				sourceRole, destRole, sourceMult, destMult);
	}
	
	/**
	 * Builds a Class object out of data in an XML element node.
	 * @param el The element node that has data representing a class.
	 * @return A Class object built from the data in the XML element.
	 */
	private ProgramClass buildClass(Element el) {
		System.out.println("================");
		System.out.println("Buiding class...");
		// get class name
		String name = el.getAttribute(NAME_TAG);
		System.out.println("    name = " + name);
		// get class type
		String type = el.getAttribute(TYPE_TAG);
		System.out.println("    type = " + type);
		// get class attributes
		ArrayList<ClassAttribute> attributes = new ArrayList<ClassAttribute>();
		NodeList attrList = el.getElementsByTagName(ATTRIBUTE_TAG);
		if(attrList != null && attrList.getLength() > 0) {
			for(int i = 0; i < attrList.getLength(); i++) {
				Node node = attrList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element attrElement = (Element) node;
					ClassAttribute attribute = buildAttribute(attrElement);
					attributes.add(attribute);
				}
			}
		}
		
		// get methods in class
		ArrayList<ProgramMethod> methods = new ArrayList<ProgramMethod>();
		NodeList methodList = el.getElementsByTagName(METHOD_TAG);
		if(methodList != null && methodList.getLength() > 0) {
			for(int i = 0; i < methodList.getLength(); i++) {
				Node node = methodList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element methodElement = (Element) node;
					ProgramMethod method = buildMethod(methodElement);
					methods.add(method);
				}
			}
		}
		
		return new ProgramClass(name, type, attributes, methods);
	}
	
	/**
	 * Builds a parameter string (type + parameter name) out of data in an XML element node.
	 * @param el The element node that has data representing a parameter of a method.
	 * @return A parameter string built from the data in the XML element.
	 */
	private String buildParameter(Element el) {
		System.out.println("Building method parameter...");
		String type = getNodeValue(TYPE_TAG, el);
		System.out.println("    type = " + type);
		String name = getNodeValue(NAME_TAG, el);
		System.out.println("    name = " + name);
		return type + " " + name;
	}
	
	/**
	 * Builds a ClassAttribute object out of data in an XML element node.
	 * @param el The element node that has data representing a class attribute.
	 * @return A ClassAttribute object built from the data in the XML element.
	 */
	private ClassAttribute buildAttribute(Element el) {
		System.out.println("Building class attribute...");
		String name = getNodeValue(NAME_TAG, el);
		System.out.println("    name = " + name);
		String type = getNodeValue(TYPE_TAG, el);
		System.out.println("    type = " + type);
		return new ClassAttribute(name, type);
	}
	
	/**
	 * Gets the value inside an XML tag.
	 * @param tag The tag that's value is wanted.
	 * @param element The element that the tag is inside of.
	 * @return The value inside the tag.
	 */
	private static String getNodeValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
	
}
