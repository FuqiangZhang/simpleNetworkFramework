package code_generator;

import java.util.ArrayList;
import java.util.List;

public class ProgramMethod {
	private String name;
	private List<String> parameters;
	private String returnType;
	private String code;
	
	public ProgramMethod(String name, String returnType, List<String> params) {
		this.name = name;
		this.parameters = params;
		this.returnType = returnType;
		this.parameters = params;
	}
	
	public void addParam(String param) {
		parameters.add(param);
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getParameters() {
		return parameters;
	}
	
	public String getReturnType() {
		return returnType;
	}

}
