package expression;

public class Division extends BinaryOperator<Double> {
	
	public Division (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((double)getLeftOperand().evaluate() / (double)getRightOperand().evaluate() );
	}
	
	
	
	

}
