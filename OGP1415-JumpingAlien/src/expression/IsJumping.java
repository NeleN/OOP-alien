package expression;

import jumpingalien.model.Creature;

public class IsJumping extends Boolean <Boolean> {
	public IsJumping(Expression<Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Object evaluate() {
		return creature.isJumping();
	}
	
	private Creature creature;

}
