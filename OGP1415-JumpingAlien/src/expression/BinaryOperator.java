package expression;

public abstract class BinaryOperator extends Operator {
	
	public BinaryOperator(E left, E right){
		this.left = left;
		this.right = right;
	}
	
	public <T> E getLeftOperand(){
		return this.left;
	}
	
	public <T> E getRightOperand(){
		return this.right;
	}
	
	private E left;
	private E right;
}
