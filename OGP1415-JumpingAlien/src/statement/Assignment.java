package statement;

import type.Type;
import expression.Expression;

/**
 * A statement that changes the value of the variable with the given name
 * and declared type to the value obtained from the given expression
 */
public class Assignment extends Statement{
	
	public Assignment(String variableName, Type variableType, Expression<Type> value){
		this.name = variableName;
		this.type = variableType;
		this.NewValue = value;
	}
	
	private String name;
	private Type type;
	private Expression<Type> NewValue;
	

	@Override
	public void execute(){
		getProgram().getGlobalVariables().put(name, (Type)NewValue.evaluate());
	}
}
