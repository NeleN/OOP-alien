package expression;

public class IsTerrain extends Boolean<Boolean> {
	
	public IsTerrain(Expression<Integer> expression){
		this.expression = (int)expression.evaluate();
	}
	
	@Override
	public Object evaluate(){
		return (this.expression == 0 || this.expression == 1 || this.expression == 2 || this.expression == 3);
	}
	
	private int expression;

}
