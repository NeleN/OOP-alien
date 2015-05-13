package expression;

public class Addition extends BinaryOperator {
	
	public Addition (E left, E right){
		super(left, right);
	}
	
	@Override
	public <T> T evaluate(){
		return (getLeftOperand().evaluate() + getRightOperand().evaluate());
	}
	

}
