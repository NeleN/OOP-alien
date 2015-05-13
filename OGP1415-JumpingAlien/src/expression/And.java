package expression;

public class And extends BinaryOperator {
	
	public And (E left, E right){
		super(left, right);
	}
	
	public <T> T evaluate(){
		return (getLeftOperand().evaluate() && getRightOperand().evaluate() );
	}
	
	
}
