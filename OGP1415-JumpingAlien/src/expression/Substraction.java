package expression;

public class Substraction extends BinaryOperator<Double> {
	
	public Substraction (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return ((double)getLeftOperand().evaluate() - (double)getRightOperand().evaluate() );
	}
	
	

}
