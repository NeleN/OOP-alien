package expression;
import be.kuleuven.cs.som.annotate.Value;
import type.Type;

@Value
public class ReadVariable extends Expression<Type> {
	
	public ReadVariable(String variableName, Type variableType){
		this.name = variableName;
		this.type = variableType;
	}
	
	
	@Override
	public Type evaluate() {
		if (getProgram() != null){
			return (Type) getProgram().getGlobalVariables().get(name);
		}
		else{
			return null;
		}
	}
	
	String name;
	Type type;

}
