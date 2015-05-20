package expression;

public class Substraction extends BinaryOperator<Double> {
	
	public Substraction (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Double evaluate(){
		return ((double)getLeftOperand().evaluate() - (double)getRightOperand().evaluate() );
	}
	
	

}
