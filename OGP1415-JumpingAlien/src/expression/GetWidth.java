package expression;

import jumpingalien.model.Creature;

public class GetWidth extends Number<Double> {
	
	public GetWidth (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Object evaluate() {
		return creature.getWidthSprite();
	}
	
	private Creature creature;

}
