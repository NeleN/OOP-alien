package expression;

public class GreaterThan extends BinaryOperator<Boolean,Double> {

	public GreaterThan (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() > getRightOperand().evaluate() );
	}
	
}
