package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Division extends BinaryOperator<Double,Double> {
	
	public Division (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Double evaluate(){
		return ((double)getLeftOperand().evaluate() / (double)getRightOperand().evaluate() );
	}

}
