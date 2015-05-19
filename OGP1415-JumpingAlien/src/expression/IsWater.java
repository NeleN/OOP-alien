package expression;

public class IsWater extends Boolean<Boolean> {
	
	public IsWater(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Object evaluate(){
		return this.geologicalFeature == 2;
	}
	
	private int geologicalFeature;

}
