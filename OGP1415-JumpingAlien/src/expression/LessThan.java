package expression;

public class LessThan extends BinaryOperator<Boolean, Double> {
	
	public LessThan (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() < getRightOperand().evaluate() );
	}
	
	

}
