package expression;

public class Value<T> extends Expression<T> {
	
	public Object evaluate() {
		return value;
	}
	
	Object value;
}
