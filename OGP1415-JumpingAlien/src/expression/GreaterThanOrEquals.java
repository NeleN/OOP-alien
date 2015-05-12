package expression;

public class GreaterThanOrEquals extends BinaryOperator {
	
	public GreaterThanOrEquals (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() >= getRightOperand().evaluate() );
	}

}
