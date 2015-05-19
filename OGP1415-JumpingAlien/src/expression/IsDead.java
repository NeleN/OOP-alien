package expression;

import jumpingalien.model.Creature;

public class IsDead extends Boolean <Boolean> {
	public IsDead(Expression<Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Object evaluate() {
		return ! creature.isAlive();
	}
	
	private Creature creature;

}
