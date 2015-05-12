package expression;

public class Substraction extends BinaryOperator {
	
	public Substraction (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() - getRightOperand().evaluate() );
	}
	
	

}
