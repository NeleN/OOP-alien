package expression;

public class Multiplication extends BinaryOperator<Double> {

	public Multiplication (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((double)getLeftOperand().evaluate() * (double)getRightOperand().evaluate() );
	}
	
	
	
}
