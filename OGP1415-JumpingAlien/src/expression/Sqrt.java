package expression;

public class Sqrt extends UnaryOperator<Double> {

	public Sqrt(Expression operand) {
		super(operand);
	}
	
	public Object evaluate(){
		return (Math.sqrt((double)getOperand().evaluate()));
	}

}
