package expression;

import be.kuleuven.cs.som.annotate.Value;


@Value
public class IsTerrain extends Bool {
	
	public IsTerrain(Expression<Integer> expression){
		this.expression = (int)expression.evaluate();
	}
	
	@Override
	public Boolean evaluate(){
		return (this.expression == 0 || this.expression == 1 || this.expression == 2 || this.expression == 3);
	}
	
	private int expression;

}
