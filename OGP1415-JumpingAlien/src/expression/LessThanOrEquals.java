package expression;

public class LessThanOrEquals extends BinaryOperator<Boolean> {
	
	public LessThanOrEquals (Expression left, Expression right){
		super(left, right);
	}
	
	public E evaluate(){
		return ((getLeftOperand().evaluate() <= getRightOperand().evaluate() );
	}
	
	

}
