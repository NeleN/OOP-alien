package expression;

import jumpingalien.model.Creature;

public class IsMoving extends Bool {
	public IsMoving(Expression<Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Boolean evaluate() {
		return (creature.isMovingX() || (creature.getSpeedY() != 0));
	}
	
	private Creature creature;

}
