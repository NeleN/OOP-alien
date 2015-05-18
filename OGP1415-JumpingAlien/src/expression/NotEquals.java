package expression;

public class NotEquals extends BinaryOperator<Boolean> {

	public NotEquals(Expression left, Expression right) {
		super(left, right);
	}
	
	public Object evaluate(){
		return (getLeftOperand().evaluate() != getRightOperand().evaluate() );
	}
	


}
