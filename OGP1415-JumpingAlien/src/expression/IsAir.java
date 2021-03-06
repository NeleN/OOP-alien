package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class IsAir extends Bool {
	
	public IsAir(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Boolean evaluate(){
		return this.geologicalFeature == 0;
	}
	
	private int geologicalFeature;

}
