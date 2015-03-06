package edu.uta.designclass.sequencediagramcomponent;

import java.util.ArrayList;

public class Message  extends SDElement{
	private String gardCondition;
	private String name;
	private String kind;
	private ArrayList<Param> prameArrayList;
	private String Return_type;
	private int source;
	@Override
	public String toString() {
		return "Message [gardCondition=" + gardCondition + ", name=" + name
				+ ", kindString=" + kind + ", prameArrayList="
				+ prameArrayList + ", Return_type=" + Return_type + ", source="
				+ source + ", destination=" + destination + "]";
	}

	private int destination;

	public String getReturnTypeString() {
		return Return_type;
	}

	public void setReturnTypeString(String returnTypeString) {
		this.Return_type = returnTypeString;
	}

	public int getId_Source() {
		return source;
	}

	public void setId_Source(int id_Source) {
		this.source = id_Source;
	}

	public int getId_Destination() {
		return destination;
	}

	public void setId_Destination(int id_Destination) {
		this.destination = id_Destination;
	}

	public String getGardCondition() {
		return gardCondition;
	}

	public void setGardCondition(String gardCondition) {
		this.gardCondition = gardCondition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKindString() {
		return kind;
	}

	public void setKindString(String kindString) {
		this.kind = kindString;
	}

	public ArrayList<Param> getPrameArrayList() {
		return prameArrayList;
	}

	public void setPrameArrayList(ArrayList<Param> prameArrayList) {
		this.prameArrayList = prameArrayList;
	}

}
