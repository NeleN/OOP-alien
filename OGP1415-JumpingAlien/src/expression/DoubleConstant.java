package expression;

public class DoubleConstant extends Number<Double> {
	
	public DoubleConstant(double value){
		this.value = value;
	}

	@Override
	public Double evaluate() {
		return value;
	}
	
	double value;

}
