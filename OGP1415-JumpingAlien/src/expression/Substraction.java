package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Substraction extends BinaryOperator<Double, Double> {
	
	public Substraction (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Double evaluate(){
		return (getLeftOperand().evaluate() - getRightOperand().evaluate() );
	}
	
	

}
