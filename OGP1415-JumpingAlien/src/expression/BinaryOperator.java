package expression;

public class BinaryOperator extends Operator {
	
	public BinaryOperator(E left, E right){
		this.left = left;
		this.right = right;
	}
	
	public E getLeftOperand(){
		return this.left;
	}
	
	public E getRightOperand(){
		return this.right;
	}
	
	private E left;
	private E right;
}
