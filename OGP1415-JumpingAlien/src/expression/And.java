package expression;

public class And extends BinaryOperator<Boolean> {
	
	public And (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((boolean)getLeftOperand().evaluate() && (boolean)getRightOperand().evaluate() );
	}
	
	
}
