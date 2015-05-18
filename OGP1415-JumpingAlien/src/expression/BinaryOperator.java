package expression;

public abstract class BinaryOperator<T> extends Operator<T> {
	
	public BinaryOperator(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeftOperand(){
		return this.left;
	}
	
	public Expression getRightOperand(){
		return this.right;
	}
	
	protected Expression left;
	protected Expression right;
}
