package expression;

public class And extends BinaryOperator {
	
	public And (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() && getRightOperand().evaluate() );
	}
	
	
}
