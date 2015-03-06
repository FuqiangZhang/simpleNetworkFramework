package edu.uta.designclass.sequencediagramcomponent;

import java.util.ArrayList;

public class SequenceDiagram  extends SDElement{
	
	
	@Override
	public String toString() {
		return "SequenceDiagram [id=" + id + ", nameString=" + nameString
				+ ", objectArrayList=" + objectArrayList
				+ ", messageArrayList=" + messageArrayList + "]";
	}

	private int id;
	private String nameString;
	private ArrayList<ClassObject> objectArrayList;
	private ArrayList<Message> messageArrayList;

	public ArrayList<ClassObject> getObjectArrayList() {
		return objectArrayList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public void setObjectArrayList(ArrayList<ClassObject> objectArrayList) {
		this.objectArrayList = objectArrayList;
	}

	public ArrayList<Message> getMessageArrayList() {
		return messageArrayList;
	}

	public void setMessageArrayList(ArrayList<Message> messageArrayList) {
		this.messageArrayList = messageArrayList;
	}
}
