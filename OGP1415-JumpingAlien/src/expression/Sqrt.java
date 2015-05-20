package expression;

public class Sqrt extends UnaryOperator<Double> {

	public Sqrt(Expression<Double> operand) {
		super(operand);
	}
	
	public Double evaluate(){
		return (Math.sqrt((double)getOperand().evaluate()));
	}

}
