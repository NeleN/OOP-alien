package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Null extends ValueExpression<Null> {
	
	public Null(){
	}

	@Override
	public Null evaluate() {
		return null;
	}

}
