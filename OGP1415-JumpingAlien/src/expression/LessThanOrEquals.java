package expression;

public class LessThanOrEquals extends BinaryOperator<Boolean, Double> {
	
	public LessThanOrEquals (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() <= getRightOperand().evaluate() );
	}
	
	

}
