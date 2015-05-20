package expression;

public class Null extends Value<Null> {
	
	public Null(){
	}

	@Override
	public Null evaluate() {
		return null;
	}

}
