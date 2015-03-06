package edu.uta.designclass.sequencediagramcomponent;

public class Param  extends SDElement{
	public String getType() {
		return param_type;
	}

	@Override
	public String toString() {
		return "Param [param_type=" + param_type + ", param_name=" + param_name
				+ "]";
	}

	public void setType(String type) {
		this.param_type = type;
	}

	public String getName() {
		return param_name;
	}

	public void setName(String name) {
		this.param_name = name;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Param otherParam = (Param) obj;
		if (otherParam.getType().equalsIgnoreCase(param_name))
			return true;
		return false;
	}

	private String param_type;
	private String param_name;

}
