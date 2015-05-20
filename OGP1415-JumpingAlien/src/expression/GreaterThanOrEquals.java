package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class GreaterThanOrEquals extends BinaryOperator<Boolean, Double> {
	
	public GreaterThanOrEquals (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() >= getRightOperand().evaluate() );
	}

}
