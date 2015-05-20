package expression;

public abstract class Value<T> extends Expression<T> {
	
	public abstract T evaluate();
	
	Object value;
}
