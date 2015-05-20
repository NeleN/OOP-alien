package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class FalseExpression extends Bool {
	public FalseExpression() {
		this.value = false;
	}
	
	boolean value; 
	
	@Override
	public Boolean evaluate() {
		return value;
	}

}
