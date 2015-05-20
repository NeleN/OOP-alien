package expression;

import jumpingalien.model.Creature;

public class GetWidth extends Number<Integer> {
	
	public GetWidth (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Integer evaluate() {
		return creature.getWidthSprite();
	}
	
	private Creature creature;

}
