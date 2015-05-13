package expression;

public class DoubleConstant extends Number {
	
	public E DoubleConstant(double value){
		this.value = value;
	}
	
	private double value;
	
	@Override
	public E evaluate() {
		return value;
	}

}
