package expression;

public class Equals extends BinaryOperator<Boolean,Double> {
	
	public Equals (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() == getRightOperand().evaluate() );
	}

}
