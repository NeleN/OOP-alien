package expression;

public class GreaterThanOrEquals extends BinaryOperator<Boolean> {
	
	public GreaterThanOrEquals (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((double)getLeftOperand().evaluate() >= (double)getRightOperand().evaluate() );
	}

}
