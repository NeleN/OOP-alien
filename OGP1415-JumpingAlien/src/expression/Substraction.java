package expression;

public class Substraction extends BinaryOperator<Double, Double> {
	
	public Substraction (Expression<Double> left, Expression<Double> right){
		super(left, right);
	}
	
	public Double evaluate(){
		return (getLeftOperand().evaluate() - getRightOperand().evaluate() );
	}
	
	

}
