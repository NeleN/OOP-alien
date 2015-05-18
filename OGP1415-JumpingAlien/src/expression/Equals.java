package expression;

public class Equals extends BinaryOperator<Boolean> {
	
	public Equals (Expression left, Expression right){
		super(left, right);
	}
	
	public Object evaluate(){
		return (getLeftOperand().evaluate() == getRightOperand().evaluate() );
	}

}
