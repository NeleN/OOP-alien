package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class And extends BinaryOperator<Boolean,Boolean> {
	
	public And (Expression<Boolean> left, Expression<Boolean> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() && getRightOperand().evaluate());
	}
	
	
}
