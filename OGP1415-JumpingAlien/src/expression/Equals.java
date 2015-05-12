package expression;

import Type.T;

public class Equals extends BinaryOperator {
	
	public Equals (E left, E right){
		super(left, right);
	}
	
	public T evaluate(){
		return (getLeftOperand().evaluate() == getRightOperand().evaluate() );
	}

}
