package expression;

public class Multiplication extends BinaryOperator {

	public Multiplication (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() * getRightOperand().evaluate() );
	}
	
	
	
}
