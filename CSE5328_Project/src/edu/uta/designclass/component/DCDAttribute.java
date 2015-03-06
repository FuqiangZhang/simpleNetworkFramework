package edu.uta.designclass.component;

import java.awt.Graphics2D;

public class DCDAttribute extends DCDComponent {

	private String type;
	private String name;
	private String visvible;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisvible() {
		return visvible;
	}

	public void setVisvible(String visvible) {
		this.visvible = visvible;
	}

	@Override
	public void draw(Graphics2D graphic, int x, int y) {
		drawString(graphic, toString(), x, y);
	}

	@Override
	public String toString() {
		return this.visvible + name + ":" + type;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		DCDAttribute otherAttribute = (DCDAttribute) obj;
		if (this.name.equals(otherAttribute.getName())) {
			return true;
		} else {
			return false;
		}
	}

}
