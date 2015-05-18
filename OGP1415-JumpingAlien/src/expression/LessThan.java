package expression;

public class LessThan extends BinaryOperator<Boolean> {
	
	public LessThan (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((double)getLeftOperand().evaluate() < (double)getRightOperand().evaluate() );
	}
	
	

}
