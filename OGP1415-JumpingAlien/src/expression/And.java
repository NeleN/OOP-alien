package expression;

public class And extends BinaryOperator<Boolean,Boolean> {
	
	public And (Expression<Boolean> left, Expression<Boolean> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() && getRightOperand().evaluate());
	}
	
	
}
