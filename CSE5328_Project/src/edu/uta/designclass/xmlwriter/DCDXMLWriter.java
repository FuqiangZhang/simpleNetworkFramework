package edu.uta.designclass.xmlwriter;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.uta.config.PropertyFactory;
import edu.uta.designclass.component.DCDAttribute;
import edu.uta.designclass.component.DCDClass;
import edu.uta.designclass.component.DCDComponent;
import edu.uta.designclass.component.DCDMethod;

public class DCDXMLWriter {
	
	// create the XML file of dcd
	public static boolean writeDCDData(ArrayList<DCDClass> classArray) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element DCD = doc.createElement("classdiagram");
			doc.appendChild(DCD);

			DCD.setAttribute("name", "Design Class Diagram");

			for (DCDClass element : classArray) {
				// class elements
				Element classOb = doc.createElement("class");
				DCD.appendChild(classOb);

				// set attributes to class element
				classOb.setAttribute("name", element.getName());
				classOb.setAttribute("type", "Concrete");

				for (DCDComponent atrb : element.getAttributesArray()) {
					// attributes
					Element attrb = doc.createElement("attribute");
					classOb.appendChild(attrb);
					
					// name and type for attributes
					Element attrType = doc.createElement("type");
					attrType.appendChild(doc.createTextNode(((DCDAttribute)atrb).getType()));
					Element attrName = doc.createElement("name");
					attrName.appendChild(doc.createTextNode(((DCDAttribute)atrb).getName()));
					attrb.appendChild(attrType);
					attrb.appendChild(attrName);
				}

				for (DCDComponent meth : element.getMethodsArray()) {
					// methods
					Element mthd = doc.createElement("method");
					mthd.setAttribute("name", ((DCDMethod)meth).getName());
					mthd.setAttribute("returntype",  ((DCDMethod)meth).getReturn_type());
					classOb.appendChild(mthd);

					// method parameters
					Element param = doc.createElement("parameter");
					Element paramType = doc.createElement("type");
					paramType.appendChild(doc.createTextNode("String"));
					Element paramName = doc.createElement("name");
					paramName.appendChild(doc.createTextNode("param1"));
					param.appendChild(paramType);
					param.appendChild(paramName);
					mthd.appendChild(param);
				}
				// for (String rela : element.getRelationshipsArray()) {
				// // relationships
				// Element relations = doc.createElement("relationship");
				// relations.setAttribute("name", rela);
				// relations.setAttribute("type", "Association");
				// DCD.appendChild(relations);
				//
				// // relationship params
				// Element relSource = doc.createElement("source");
				// relSource.appendChild(doc.createTextNode("Class1"));
				// Element relDest = doc.createElement("destination");
				// relDest.appendChild(doc.createTextNode("Class2"));
				// Element relSrcRole = doc.createElement("sourcerolename");
				// relSrcRole.appendChild(doc.createTextNode("Rolename1"));
				// Element relSrcMulti = doc
				// .createElement("sourcemultiplicity");
				// relSrcMulti.appendChild(doc.createTextNode("1"));
				// Element relDestRole = doc
				// .createElement("destinationrolename");
				// relDestRole.appendChild(doc.createTextNode("Rolename2"));
				// Element relDestMulti = doc
				// .createElement("destinationmultiplicity");
				// relDestMulti.appendChild(doc.createTextNode("2"));
				// relations.appendChild(relSource);
				// relations.appendChild(relDest);
				// relations.appendChild(relSrcRole);
				// relations.appendChild(relSrcMulti);
				// relations.appendChild(relDestRole);
				// relations.appendChild(relDestMulti);
				// }

			}
			// write the content into xml file
			createDir();
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(PropertyFactory.getProperties().getProperty("DCDXML"));
			transformer.transform(source, result);
			return true;

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		}
	}
	
	private static void createDir() {
		File file = new File("dcd");
		if (!file.exists()) {
			if (file.mkdir()) {
				// System.out.println("Directory is created!");
			} else {
				// System.out.println("Failed to create directory!");
			}
		}
	}

}
