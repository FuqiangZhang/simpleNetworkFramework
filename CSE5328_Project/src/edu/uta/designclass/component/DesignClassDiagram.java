package edu.uta.designclass.component;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class DesignClassDiagram extends DCDComponent {

	private ArrayList<DCDClass> classesArray;
	private ArrayList<DCDRelationship> relationshipsArrayList;

	public DesignClassDiagram() {
		classesArray = new ArrayList<DCDClass>();
		relationshipsArrayList = new ArrayList<DCDRelationship>();
	}

	public void draw(Graphics2D graphic, int horiMax, int vertMax) {
		horiMax = getMaxWidth(classesArray);
		vertMax = getMaxHeight(classesArray);
		int rowSpace = 20;
		int colSpace = 50;

		int topStart = 30;
		int leftStart = 30;
		int tempCount = 0;
		int classCnt = classesArray.size();
		int rowCnt = classCnt / 3;
		if ((rowCnt * 3) < classCnt)
			rowCnt++;

		for (int i = 1; i <= rowCnt; i++) {
			for (int j = 1; j <= 3; j++) {
				if (tempCount >= classCnt)
					break;
				leftStart = leftStart + horiMax + colSpace;
				classesArray.get(tempCount).setCol(j);
				classesArray.get(tempCount).setRow(i);
				classesArray.get(tempCount).draw(graphic, leftStart, topStart);
				tempCount++;
			}
			topStart = topStart + vertMax + rowSpace;
			leftStart = 30;
		}

		for (DCDRelationship relationship : relationshipsArrayList) {
			relationship.draw(graphic, horiMax, 0);
		}

	}

	public int getMaxHeight(ArrayList<DCDClass> classesArray) {
		int arraysize = classesArray.size();
		int nameHeight;
		int lineBetweenNameAndAttributes;
		int attributesHeight;
		int lineBetweenAttributesAndMethods;
		int methodsHeight;
		int blockHeight;
		int maxHeight = 0;
		for (int i = 0; i < arraysize; i++) {
			nameHeight = 15;
			lineBetweenNameAndAttributes = 1;
			attributesHeight = getArrayHeight(classesArray.get(i)
					.getAttributesArray());
			lineBetweenAttributesAndMethods = 1;
			methodsHeight = getArrayHeight(classesArray.get(i)
					.getMethodsArray());
			blockHeight = nameHeight + lineBetweenNameAndAttributes
					+ attributesHeight + lineBetweenAttributesAndMethods
					+ methodsHeight;
			if (blockHeight > maxHeight)
				maxHeight = blockHeight;
			classesArray.get(i).setHeight(blockHeight);
		}
		return maxHeight;
	}

	public int getArrayHeight(ArrayList array) {
		int heightSize = 0;
		int arraySize = array.size();
		heightSize = 15 * arraySize;
		return heightSize;
	}

	public int getMaxWidth(ArrayList<DCDClass> classesArray) {
		int arraysize = classesArray.size();
		int maxWidth = 0;
		int blockWidth;
		int nameWidth;
		int attributesWidth;
		int methodsWidth;
		for (int i = 0; i < arraysize; i++) {
			nameWidth = classesArray.get(i).getName().length();
			attributesWidth = findLongestStringInArray(classesArray.get(i)
					.getAttributesArray());
			methodsWidth = findLongestStringInArray(classesArray.get(i)
					.getMethodsArray());

			blockWidth = nameWidth;
			if (blockWidth < attributesWidth)
				blockWidth = attributesWidth;
			if (blockWidth < methodsWidth)
				blockWidth = methodsWidth;
			blockWidth = blockWidth * 7;
			if (blockWidth > maxWidth)
				maxWidth = blockWidth;
			classesArray.get(i).setWidth(blockWidth);
		}
		return maxWidth;
	}

	public int findLongestStringInArray(ArrayList stringArray) {
		int longestString = 0;
		int tempStringLen = 0;
		int arraySize = stringArray.size();

		for (int i = 0; i < arraySize; i++) {
			tempStringLen = stringArray.get(i).toString().length();
			if (tempStringLen > longestString)
				longestString = tempStringLen;
		}
		return longestString;
	}

	public ArrayList<DCDClass> getClasses() {
		if (classesArray == null)
			classesArray = new ArrayList<DCDClass>();
		return classesArray;
	}

	public ArrayList<DCDRelationship> getRelationships() {
		if (relationshipsArrayList == null)
			relationshipsArrayList = new ArrayList<DCDRelationship>();
		return relationshipsArrayList;
	}

	public DCDClass getDcdClassbyID(int id) {
		for (DCDClass dcdClass : classesArray) {
			if (dcdClass.getId() == id) {
				return dcdClass;
			}
		}
		return null;
	}

}