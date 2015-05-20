package expression;

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
