package expression;

import jumpingalien.model.Creature;

public class IsMoving extends Boolean <Boolean> {
	public IsMoving(Expression<Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Object evaluate() {
		return (creature.isMovingX() || (creature.getSpeedY() != 0));
	}
	
	private Creature creature;

}
