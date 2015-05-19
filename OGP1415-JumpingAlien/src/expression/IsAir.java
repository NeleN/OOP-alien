package expression;

public class IsAir extends Boolean<Boolean> {
	
	public IsAir(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Object evaluate(){
		return this.geologicalFeature == 0;
	}
	
	private int geologicalFeature;

}
