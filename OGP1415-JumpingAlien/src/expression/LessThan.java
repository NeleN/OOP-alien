package expression;

public class LessThan extends BinaryOperator {
	
	public LessThan (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() < getRightOperand().evaluate() );
	}
	
	

}
