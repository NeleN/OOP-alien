package expression;

public class Division extends BinaryOperator {
	
	public Division (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() / getRightOperand().evaluate() );
	}
	
	
	
	

}
