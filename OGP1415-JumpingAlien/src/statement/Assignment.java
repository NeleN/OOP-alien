package statement;

import type.Type;
import expression.Expression;

/**
 * A statement that changes the value of the variable with the given name
 * and declared type to the value obtained from the given expression
 */
public class Assignment extends Statement{
	
	public Assignment(String variableName, Type variableType, Expression<?> value){
		this.name = variableName;
		this.type = variableType;
		this.NewValue = value;
	}
	
	private Object value;
	private String name;
	private Type type;
	private Expression<?> NewValue;
	
	public Object getValue(String name){
		value = String.valueOf(name);
		return value;
	}
	
	public void setValue(Expression<?> value){
		this.value = value;
		
	}
	@Override
	public void execute(){
		setValue(NewValue);
		
		
	}
}
