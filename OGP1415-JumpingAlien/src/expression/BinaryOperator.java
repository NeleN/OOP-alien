package expression;

public abstract class BinaryOperator<T> extends Operator<T> {
	
	public BinaryOperator(Expression<T> left, Expression<T> right){
		this.left = left;
		this.right = right;
	}
	
	public Expression<T> getLeftOperand(){
		return this.left;
	}
	
	public Expression<T> getRightOperand(){
		return this.right;
	}
	
	protected Expression<T> left;
	protected Expression<T> right;
}
