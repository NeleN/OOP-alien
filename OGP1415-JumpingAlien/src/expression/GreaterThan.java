package expression;

public class GreaterThan extends BinaryOperator<Boolean> {

	public GreaterThan (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((double)getLeftOperand().evaluate() > (double)getRightOperand().evaluate() );
	}
	
}
