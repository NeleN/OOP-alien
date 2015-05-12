package expression;

public class NotEquals extends BinaryOperator {

	public NotEquals(E left, E right) {
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() != getRightOperand().evaluate() );
	}
	


}
