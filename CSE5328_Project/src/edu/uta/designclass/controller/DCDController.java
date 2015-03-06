package edu.uta.designclass.controller;

import javax.swing.*;

import edu.uta.config.PropertyFactory;
import edu.uta.designclass.component.DesignClassDiagram;
import edu.uta.designclass.factory.DCDFactory;
import edu.uta.designclass.factory.SequenceDiagramFactory;
import edu.uta.designclass.sequencediagramcomponent.SequenceDiagram;
import edu.uta.designclass.ui.DCDGUI;
import edu.uta.designclass.ui.FKCanvas;
import edu.uta.designclass.xmlwriter.DCDXMLWriter;

public class DCDController {

	private static DesignClassDiagram sdClassDiagram;

	public static DesignClassDiagram getDCD() {
		if (sdClassDiagram == null) {
			sdClassDiagram = DCDFactory.createDesignClassDiagram(getSD());
		}
		return sdClassDiagram;
	}

	public static SequenceDiagram getSD() {
		SequenceDiagramFactory sdf = new SequenceDiagramFactory();
		SequenceDiagram sd = sdf.createSD("Sequence-Diagram", PropertyFactory
				.getProperties().getProperty("SDXML"));
		return sd;
	}
	public static void displayTable() {
//		guiDcdgui.ShowTable();
	}
	
	public static boolean writeXML() {
		if (sdClassDiagram != null && sdClassDiagram.getClasses() != null) {
			return DCDXMLWriter.writeDCDData(sdClassDiagram.getClasses());
		} else {
			return false;
		}
	}
	private static DCDGUI guiDcdgui;
	public static void setGUI(DCDGUI gui) {
		guiDcdgui = gui;
	}
	
	public static DCDGUI getDcdgui(){
		return guiDcdgui;
	}

}