package expression;

public class NotEquals extends BinaryOperator<Boolean, Double> {

	public NotEquals(Expression<Double> left, Expression<Double> right) {
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() != getRightOperand().evaluate() );
	}
	


}
