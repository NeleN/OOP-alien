package expression;

public class IsPassable extends Boolean<Boolean> {
	
	public IsPassable(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Object evaluate(){
		return this.geologicalFeature != 1;
	}
	
	private int geologicalFeature;

}
