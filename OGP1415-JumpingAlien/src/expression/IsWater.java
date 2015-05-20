package expression;

public class IsWater extends Bool {
	
	public IsWater(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Boolean evaluate(){
		return this.geologicalFeature == 2;
	}
	
	private int geologicalFeature;

}
