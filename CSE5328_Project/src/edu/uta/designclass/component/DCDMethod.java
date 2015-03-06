package edu.uta.designclass.component;

import java.awt.Graphics2D;
import java.util.ArrayList;

import edu.uta.designclass.sequencediagramcomponent.Param;

public class DCDMethod extends DCDComponent {

	private String name;
	private String type;
	private ArrayList<Param> prameArrayList;
	private String Return_type;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		DCDMethod otherMethod = (DCDMethod) obj;
		if (otherMethod.getName().equals(this.getName())
				&& otherMethod.getPrameArrayList().equals(prameArrayList))
			return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Param p : getPrameArrayList()) {
			sb.append(p.getName() + ":");
			sb.append(p.getType());
			sb.append(",");
		}
		// remove the last ','
		if (sb.length() > 1)
			sb.deleteCharAt(sb.length() - 1);
		String returnString = "";
		if (Return_type != null && !Return_type.equalsIgnoreCase("")) {
			returnString = ":" + Return_type;
		}
		return type + name + "(" + sb + ")" + returnString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Param> getPrameArrayList() {
		return prameArrayList;
	}

	public void setPrameArrayList(ArrayList<Param> prameArrayList) {
		this.prameArrayList = prameArrayList;
	}

	public String getReturn_type() {
		return Return_type;
	}

	public void setReturn_type(String return_type) {
		Return_type = return_type;
	}

	@Override
	public void draw(Graphics2D graphic, int x, int y) {
		drawString(graphic, toString(), x, y);
	}

}
