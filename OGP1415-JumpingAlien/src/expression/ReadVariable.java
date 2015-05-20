package expression;
import type.Type;

public class ReadVariable extends Expression<Type> {
	
	public ReadVariable(String variableName, Type variableType){
		this.name = variableName;
		this.type = variableType;
	}
	
	
	@Override
	public Type evaluate() {
		return getProgram().getGlobalVariables().get(name);
	}
	
	String name;
	Type type;

}
