package expression;

public class Not extends UnaryOperator<Boolean> {

	public Not(Expression operand) {
		super(operand);
	}
	
	public Object evaluate(){
		this.value = ! (boolean)getOperand().evaluate();
		return this.value;
	}
	
	private Object value;

}
