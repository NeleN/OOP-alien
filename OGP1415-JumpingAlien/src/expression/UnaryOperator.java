package expression;

public abstract class UnaryOperator<T> extends Operator<T> {
	
	public UnaryOperator(Expression operand){
		this.operand = operand;
	}
	
	public Expression getOperand(){
		return this.operand;
	}

	
	private Expression operand;


}
