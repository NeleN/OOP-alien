package expression;

public class TrueExpression extends Bool{
	
	public TrueExpression() {
		this.value = true;
	}
	
	boolean value;
	
	@Override
	public Boolean evaluate() {
		return this.value;
	}

}
