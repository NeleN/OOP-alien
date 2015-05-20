package expression;

public class Equals extends BinaryOperator<Boolean> {
	
	public Equals (Expression<Boolean> left, Expression<Boolean> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() == getRightOperand().evaluate() );
	}

}
