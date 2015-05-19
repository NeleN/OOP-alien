package expression;

public class IsMagma extends Boolean<Boolean> {
	
	public IsMagma(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Object evaluate(){
		return this.geologicalFeature == 3;
	}
	
	private int geologicalFeature;

}
