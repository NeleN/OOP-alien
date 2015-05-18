package expression;

public class LessThanOrEquals extends BinaryOperator<Boolean> {
	
	public LessThanOrEquals (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((double)getLeftOperand().evaluate() <= (double)getRightOperand().evaluate() );
	}
	
	

}
