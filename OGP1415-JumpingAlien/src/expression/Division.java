package expression;

public class Division extends BinaryOperator<Double,Double> {
	
	public Division (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Double evaluate(){
		return ((double)getLeftOperand().evaluate() / (double)getRightOperand().evaluate() );
	}

}
