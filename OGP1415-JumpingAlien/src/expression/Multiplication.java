package expression;

public class Multiplication extends BinaryOperator<Double> {

	public Multiplication (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Double evaluate(){
		return ((double)getLeftOperand().evaluate() * (double)getRightOperand().evaluate() );
	}
	
	
	
}
