package expression;

public abstract class UnaryOperator<T> extends Operator<T> {
	
	public UnaryOperator(Expression<T> operand){
		this.operand = operand;
	}
	
	public Expression<T> getOperand(){
		return this.operand;
	}

	
	private Expression<T> operand;


}
