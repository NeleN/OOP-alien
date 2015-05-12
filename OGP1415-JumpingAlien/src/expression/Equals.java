package expression;

public class Equals extends BinaryOperator {
	
	public Equals (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() == getRightOperand().evaluate() );
	}

}
