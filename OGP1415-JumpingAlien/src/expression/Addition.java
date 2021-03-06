package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Addition extends BinaryOperator<Double,Double> {
	
	public Addition (Expression<Double> left, Expression<Double> right){
		super(left, right);
		//evaluate();
	}
	
	@Override
	public Double evaluate(){
		double result = ((double)getLeftOperand().evaluate() + (double)getRightOperand().evaluate());
		return result;
	}

}