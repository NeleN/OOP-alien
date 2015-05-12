package expression;

public class UnaryOperator extends Operator {
	
	public UnaryOperator(E operand){
		this.operand = operand;
	}
	
	public E getOperand(){
		return this.operand;
	}

	
	private E operand;

	@Override
	public E evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

}
