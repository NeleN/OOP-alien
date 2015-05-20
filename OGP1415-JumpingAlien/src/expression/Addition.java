package expression;

public class Addition extends BinaryOperator<Double> {
	
	public Addition (Expression<Double> left, Expression<Double> right){
		super(left, right);
		//evaluate();
	}
	
	@Override
	public Double evaluate(){
		double result = ((double)left.evaluate() + (double)right.evaluate());
		return result;
	}

}