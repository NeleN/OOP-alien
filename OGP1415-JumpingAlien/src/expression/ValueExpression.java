package expression;

public abstract class ValueExpression<T> extends Expression<T> {
	
	public abstract T evaluate();
	
	Object value;
}
