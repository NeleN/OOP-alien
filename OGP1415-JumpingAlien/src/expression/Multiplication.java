package expression;

public class Multiplication extends BinaryOperator<Double,Double> {

	public Multiplication (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Double evaluate(){
		return (getLeftOperand().evaluate() * getRightOperand().evaluate() );
	}
	
	
	
}
