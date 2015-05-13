package expression;

public class Random extends Number {
	
	public Random(E value){
		this.maxValue = value;
	}

	private E maxValue;
	
	@Override
	public E evaluate() {
		E value = Math.random()*maxValue;
		if (value != maxValue)
			return value;
		else 
			evaluate();
	}

}
