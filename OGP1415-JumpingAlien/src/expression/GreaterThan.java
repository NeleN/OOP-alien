package expression;

public class GreaterThan extends BinaryOperator {

	public GreaterThan (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() > getRightOperand().evaluate() );
	}
	
}
