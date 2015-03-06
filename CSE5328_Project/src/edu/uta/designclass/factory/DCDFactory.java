package edu.uta.designclass.factory;

import java.util.ArrayList;

import edu.uta.designclass.component.DCDMethod;
import edu.uta.designclass.component.DCDRelationship;
import edu.uta.designclass.component.DCDClass;
import edu.uta.designclass.component.DesignClassDiagram;
import edu.uta.designclass.sequencediagramcomponent.*;

public class DCDFactory {

	private static DesignClassDiagram dcd = null;

	public static DesignClassDiagram createDesignClassDiagram(SequenceDiagram sd) {
		if (dcd == null) {
			dcd = new DesignClassDiagram();
			ArrayList<DCDClass> classesArray = dcd.getClasses();
			ArrayList<DCDRelationship> relationshipArrayList = dcd
					.getRelationships();
			

			// create the dcdclass from the sequence diagram
			ArrayList<ClassObject> classObjects = sd.getObjectArrayList();
			ArrayList<Message> msgArrayList = sd.getMessageArrayList();
			for (ClassObject co : classObjects) {
				DCDClass dcdClass = new DCDClass(co.getName());
				dcdClass.setId(co.getId());
				classesArray.add(dcdClass);
			}

			/**
			 * add method and relationship to the class
			 * 
			 * trying to add some attributes from setX getX message
			 * */

			for (Message msgMessage : msgArrayList) {
				// add the methods
				int desId = msgMessage.getId_Destination();
				int sourceID = msgMessage.getId_Source();
				DCDClass desClass = dcd.getDcdClassbyID(desId);
				DCDClass sourceClass = dcd.getDcdClassbyID(sourceID);

				String msgName = msgMessage.getName();

				DCDMethod method = new DCDMethod();
				method.setName(msgName);
				method.setPrameArrayList(msgMessage.getPrameArrayList());
				method.setReturn_type(msgMessage.getReturnTypeString());
				method.setType("+");
				desClass.addMethod(method);

				// try to add attributes by the method (setX() getX() )
				desClass.addAttribute(method);

				// add the relationship
				// sourceClass.addRelationship(desClass.getName());
				DCDRelationship relationship = new DCDRelationship();
				relationship.setSource(sourceClass);
				relationship.setDestination(desClass);
				relationship.setType(msgMessage.getKindString());
				relationshipArrayList.add(relationship);
			}

		}

		return dcd;

	}
}
