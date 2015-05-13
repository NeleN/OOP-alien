package expression;

public class Addition extends E<Double> {
	
	public Addition (E left, E right){
		this.left = left;
		this.right = right;
	}
	

	
	private E <Double> left;
	private E <Double> right;
	
	@Override
	public Double evaluate(){
		return (left.evaluate() + right.evaluate());
	}
}
