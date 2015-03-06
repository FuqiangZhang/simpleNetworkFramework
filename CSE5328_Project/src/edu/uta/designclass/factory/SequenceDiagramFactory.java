package edu.uta.designclass.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Object;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.uta.designclass.sequencediagramcomponent.ClassObject;
import edu.uta.designclass.sequencediagramcomponent.Message;
import edu.uta.designclass.sequencediagramcomponent.Param;
import edu.uta.designclass.sequencediagramcomponent.SequenceDiagram;

public class SequenceDiagramFactory {

	public SequenceDiagram createSD(String name, String path) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = null;
		Document doc = null;
		InputStream is = null;
		try {
			db = dbf.newDocumentBuilder();
			is = new FileInputStream(new File(path));
			doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName(name);
			if (nodes == null) {
				throw new Exception("null nodes with tagName " + name);
			}

			Element node = (Element) nodes.item(0);// sequence diagram node

			SequenceDiagram sdDiagram = new SequenceDiagram();

			String packagePathString = sdDiagram.getClass().getPackage()
					.getName();

			// set id and name for sequence diagram
			sdDiagram.setId(Integer.valueOf(node.getAttribute("id")));
			sdDiagram.setNameString(node.getAttribute("name"));
			// create and set class objects for the sequence diagram
			ArrayList<ClassObject> objs = new ArrayList<ClassObject>();
			// objects nodes
			NodeList ObjNodes = node.getElementsByTagName("ClassObject");

			// add class object
			for (int j = 0; j < ObjNodes.getLength(); j++) {
				Element cnode = (Element) ObjNodes.item(j);
				ClassObject cObj = null;
				String classNameString = Character.toUpperCase(cnode
						.getNodeName().charAt(0))
						+ cnode.getNodeName().substring(1);
				cObj = (ClassObject) xml2Object(cnode, packagePathString + "."
						+ classNameString);
				objs.add(cObj);
			}
			// set objects array list for the sequence diagram
			sdDiagram.setObjectArrayList(objs);
			// message nodes
			NodeList msgNodeList = node.getElementsByTagName("Message");
			ArrayList<Message> msgs = new ArrayList<Message>();
			for (int j = 0; j < msgNodeList.getLength(); j++) {
				Element cnode = (Element) msgNodeList.item(j);
				Message msg = null;
				String classNameString = Character.toUpperCase(cnode
						.getNodeName().charAt(0))
						+ cnode.getNodeName().substring(1);
				msg = (Message) xml2Object(cnode, packagePathString + "."
						+ classNameString);

				// param nodes
				NodeList paramNodeList = cnode.getElementsByTagName("param");
				ArrayList<Param> pList = new ArrayList<Param>();
				for (int k = 0; k < paramNodeList.getLength(); k++) {
					Element pnode = (Element) paramNodeList.item(k);
					Param param = null;
					classNameString = Character.toUpperCase(pnode.getNodeName()
							.charAt(0)) + pnode.getNodeName().substring(1);
					param = (Param) xml2Object(pnode, packagePathString + "."
							+ classNameString);
					pList.add(param);
				}
				msg.setPrameArrayList(pList);
				msgs.add(msg);
			}
			sdDiagram.setMessageArrayList(msgs);

			return sdDiagram;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// create an object with the XML node and given class name
	public Object xml2Object(Element node, String className) {
		NamedNodeMap attributesElement = node.getAttributes();
		NodeList childElements = node.getChildNodes();
		Class cls = null;
		Object object = null;
		try {
			cls = Class.forName(className);
			object = cls.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < attributesElement.getLength(); i++) {
			Node a_node = attributesElement.item(i);
			setField(object, a_node);
		}

		for (int i = 0; i < childElements.getLength(); i++) {
			Node a_node = childElements.item(i);
			setField(object, a_node);
		}

		return object;
	}

	// set field to the object with the XML node's value
	private Object setField(Object object, Node a_node) {
		Field[] fields = object.getClass().getDeclaredFields();
		String name = a_node.getNodeName();
		name = name.replace("-", "_");
		String valueString = a_node.getTextContent() == null ? a_node
				.getNodeValue() : a_node.getTextContent();
		for (int j = 0; j < fields.length; j++) {

			if (fields[j].getName().equalsIgnoreCase(name)) {
				fields[j].setAccessible(true);
				try {
					if (fields[j].getType().getName().equalsIgnoreCase("int")) {
						fields[j].set(object, Integer.valueOf(valueString));
					} else {
						fields[j].set(object, valueString);
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return object;
	}
}
