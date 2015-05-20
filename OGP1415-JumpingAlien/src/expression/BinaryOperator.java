package expression;

public abstract class BinaryOperator<T,S> extends Operator<T> {
	
	public BinaryOperator(Expression<S> left, Expression<S> right){
		this.left = left;
		this.right = right;
	}
	
	public Expression<S> getLeftOperand(){
		return this.left;
	}
	
	public Expression<S> getRightOperand(){
		return this.right;
	}
	
	protected Expression<S> left;
	protected Expression<S> right;
}
