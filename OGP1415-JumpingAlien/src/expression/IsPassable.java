package expression;

public class IsPassable extends Bool {
	
	public IsPassable(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Boolean evaluate(){
		return this.geologicalFeature != 1;
	}
	
	private int geologicalFeature;

}
