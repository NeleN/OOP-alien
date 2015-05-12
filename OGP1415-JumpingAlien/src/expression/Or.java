package expression;

public class Or extends BinaryOperator {
	
	public Or (E left, E right){
		super(left, right);
	}
	
	public E evaluate(){
		return (getLeftOperand().evaluate() || getRightOperand().evaluate() );
	}
	
	

}
