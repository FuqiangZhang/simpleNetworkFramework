package edu.uta.designclass.component;

import java.awt.Graphics2D;
import java.util.ArrayList;

import edu.uta.designclass.controller.DCDController;

public class DCDClass extends DCDComponent {
	private String name;
	private int id;
	private ArrayList<DCDComponent> attributesArray;
	private ArrayList<DCDComponent> methodsArray;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private int row;
	private int col;

	// add a method to the dcdclass

	public void addMethod(DCDMethod methodname) {
		if (!methodsArray.contains(methodname)) {
			this.methodsArray.add(methodname);
		}
	}

	// add a attribute to the class
	public void addAttribute(DCDAttribute attribute) {
		if (!attributesArray.contains(attribute)) {
			this.attributesArray.add(attribute);

		}
	}

	/**
	 * add a attribute by the method (getX(), setX(), isX() etc.)
	 * */
	public void addAttribute(DCDMethod method) {
		String methodName = method.getName();
		if (methodName.startsWith("get")
				|| (methodName.startsWith("set") && method.getPrameArrayList()
						.size() == 1)
				|| (methodName.startsWith("is") && method.getReturn_type()
						.equalsIgnoreCase("boolean"))) {

			DCDAttribute attribute = new DCDAttribute();
			if (methodName.startsWith("get")) {
				methodName = methodName.substring(3);
				attribute.setType(method.getReturn_type());
			} else if (methodName.startsWith("set")) {
				methodName = methodName.substring(3);
				attribute.setType(method.getPrameArrayList().get(0).getType());
			} else if (methodName.startsWith("is")) {
				methodName = methodName.substring(2);
				attribute.setType(method.getReturn_type());
			}

			methodName = Character.toLowerCase(methodName.charAt(0))
					+ methodName.substring(1);
			attribute.setName(methodName);
			attribute.setVisvible("-");
			addAttribute(attribute);
		}

	}

	public void draw(Graphics2D graphic, int x, int y) {
		int xPosition = x;
		int yPosition = y;
		setxPos(xPosition);
		setyPos(yPosition);

		// gets the minimum width needed
		int nameWidth = getName().length();
		int attributesWidth = DCDController.getDCD().findLongestStringInArray(
				getAttributesArray());
		int methodsWidth = DCDController.getDCD().findLongestStringInArray(
				getMethodsArray());

		int blockWidth = nameWidth;
		if (blockWidth < attributesWidth)
			blockWidth = attributesWidth;
		if (blockWidth < methodsWidth)
			blockWidth = methodsWidth;
		blockWidth = blockWidth * 7;

		// gets the minimum height needed
		// and save the height for the methods and attributes
		int nameHeight = 15;
		int lineBetweenNameAndAttributes = 1;
		int attributesHeight = DCDController.getDCD().getArrayHeight(
				getAttributesArray());
		int lineBetweenAttributesAndMethods = 1;
		int methodsHeight = DCDController.getDCD().getArrayHeight(
				getMethodsArray());
		int blockHeight = nameHeight + lineBetweenNameAndAttributes
				+ attributesHeight + lineBetweenAttributesAndMethods
				+ methodsHeight;

		// draws the block
		drawBlock(graphic, blockWidth, blockHeight, xPosition, yPosition);
		// draws the class name
		drawString(graphic, getName(), xPosition + 5, yPosition + nameHeight);
		drawLine(graphic, xPosition, yPosition + nameHeight
				+ lineBetweenNameAndAttributes, xPosition + blockWidth,
				yPosition + nameHeight + lineBetweenNameAndAttributes);
		// draws the class attributes
		int arraysize = getAttributesArray().size();
		for (int i = 0; i < arraysize; i++) {
			DCDComponent attribute = getAttributesArray().get(i);

			attribute.draw(graphic, xPosition + 5, yPosition + nameHeight
					+ lineBetweenNameAndAttributes + 13 + (nameHeight * i));
			// don't know why but 13 works well..
			// drawString(graphic, text, xPosition + 5, yPosition + nameHeight
			// + lineBetweenNameAndAttributes + 13 + (nameHeight * i));
		}
		drawLine(graphic, xPosition, yPosition + nameHeight
				+ lineBetweenNameAndAttributes + attributesHeight
				+ lineBetweenAttributesAndMethods, xPosition + blockWidth,
				yPosition + nameHeight + lineBetweenNameAndAttributes
						+ attributesHeight + lineBetweenAttributesAndMethods);

		// draws the method
		arraysize = getMethodsArray().size();
		for (int i = 0; i < arraysize; i++) {
			DCDComponent method = getMethodsArray().get(i);
			method.draw(graphic, xPosition + 5, yPosition + nameHeight
					+ lineBetweenNameAndAttributes + 13 + (nameHeight * i)
					+ attributesHeight + lineBetweenAttributesAndMethods);

		}

	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public ArrayList<DCDComponent> getAttributesArray() {
		return attributesArray;
	}

	public void setAttributesArray(ArrayList<DCDComponent> attributesArray) {
		this.attributesArray = attributesArray;
	}

	public ArrayList<DCDComponent> getMethodsArray() {
		return methodsArray;
	}

	public void setMethodsArray(ArrayList<DCDComponent> methodsArray) {
		this.methodsArray = methodsArray;
	}

	public DCDClass(String className) {
		this.name = className;
		this.attributesArray = new ArrayList<DCDComponent>();
		this.methodsArray = new ArrayList<DCDComponent>();
		this.xPos = 0;
		this.yPos = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
