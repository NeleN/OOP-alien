package expression;

public class Addition extends BinaryOperator<Double> {
	
	public Addition (Expression left, Expression right){
		super(left, right);
		//evaluate();
	}
	
	@Override
	public Object evaluate(){
		double result = ((double)left.evaluate() + (double)right.evaluate());
		return result;
	}

}