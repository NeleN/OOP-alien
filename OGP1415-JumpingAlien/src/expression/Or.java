package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Or extends BinaryOperator<Boolean, Boolean> {
	
	public Or (Expression<Boolean> left, Expression<Boolean> right){
		super(left, right);
	}
	
	public Boolean evaluate(){
		return (getLeftOperand().evaluate() || getRightOperand().evaluate() );
	}
	
	

}
