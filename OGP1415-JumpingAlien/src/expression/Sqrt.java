package expression;

public class Sqrt extends UnaryOperator {

	public Sqrt(E operand) {
		super(operand);
	}
	
	public E evaluate(){
		return (getOperand()*getOperand());
	}

}
