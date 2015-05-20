package expression;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class IsMagma extends Bool {
	
	public IsMagma(Expression<Integer> expression){
		this.geologicalFeature = (int)expression.evaluate();
	}
	
	@Override
	public Boolean evaluate(){
		return this.geologicalFeature == 3;
	}
	
	private int geologicalFeature;

}
