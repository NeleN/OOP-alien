package expression;

public class Or extends BinaryOperator<Boolean> {
	
	public Or (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((boolean)getLeftOperand().evaluate() || (boolean)getRightOperand().evaluate() );
	}
	
	

}
