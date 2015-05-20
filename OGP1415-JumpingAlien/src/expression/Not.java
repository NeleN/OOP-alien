package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Not extends UnaryOperator<Boolean> {

	public Not(Expression operand) {
		super(operand);
	}
	
	public Boolean evaluate(){
		this.value = ! (boolean)getOperand().evaluate();
		return this.value;
	}
	
	private Boolean value;

}
