package expression;

import jumpingalien.model.Creature;

public class GetHeight extends Number<Double> {
	
	public GetHeight (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Object evaluate() {
		return creature.getHeightSprite();
	}
	
	private Creature creature;

}
