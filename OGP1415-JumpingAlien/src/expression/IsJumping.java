package expression;

import jumpingalien.model.Creature;

public class IsJumping extends Bool {
	public IsJumping(Expression<Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Boolean evaluate() {
		return creature.isJumping();
	}
	
	private Creature creature;

}
