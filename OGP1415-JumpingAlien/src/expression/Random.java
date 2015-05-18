package expression;

public class Random extends Number<Double> {
	
	public Random(Expression value){
		this.maxValue = (double)value.evaluate();
		this.value = calculateRandom();
	}

	private Double maxValue;
	
	public double calculateRandom() {
		double value = (double)Math.random()*maxValue;
		if (value != maxValue){
			return value;}
		else {
			return calculateRandom();}
	}

}
