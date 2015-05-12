package expression;

public class Addition extends BinaryOperator {
	
	public Addition (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() + getRightOperand().evaluate());
	}
	

}
