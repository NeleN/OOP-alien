package expression;

public class LessThanOrEquals extends BinaryOperator {
	
	public LessThanOrEquals (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() <= getRightOperand().evaluate() );
	}
	
	

}
