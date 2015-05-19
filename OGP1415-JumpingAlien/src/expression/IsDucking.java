package expression;

import jumpingalien.model.*;

public class IsDucking extends Boolean <Boolean> {
	public IsDucking(Expression<Creature> expression){
		this.creature = (Mazub) expression.evaluate();
	}
	
	@Override
	public Object evaluate() {
		return creature.isDucking();
	}
	
	private Mazub creature;

}
